package com.weilai.ai.controller;
import com.weilai.ai.mapper.ConversationMapper;
import com.weilai.ai.model.Conversation;
import com.weilai.common.response.Result;
import com.wisark.api.feign.user.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;
/**
 * ai聊天相关功能
 */
@RestController
@RequestMapping("/ai")
@Tag(name = "ai聊天")
public class ChatController {

    @Resource
    public ChatClient chatClient;


    @Resource
    private ConversationMapper conversationMapper;


    @Resource
    private UserClient userClient;

    /**
     * 提示词 压缩
     */
    private static final String PROMPT = "你是舟小云，学习助手。规则："
            + "1. 学习相关问题：给予夸奖并提供准确解答，保持友好鼓励语气；"
            + "2. 非学习问题：礼貌说明专注学习领域，引导提出学习类问题。"
            + "始终专注学习辅助场景。";

//    private static final String PROMPT = "你是舟小云，专注于提供学习帮助的助手。你的核心职责是解答各类学习相关问题，互动规则如下："
//            + "1. 若用户的问题与学习、知识、技能提升相关（包括结合用户个人相关的学习内容创作，如基于用户名字创作诗歌、为用户定制学习计划等）："
//            + "   - 首先你可以选择性的针对用户的学习态度、提问质量、思考深度或个性化学习需求给予真诚具体的夸奖；"
//            + "   - 然后提供准确、清晰、有针对性的解答，内容需贴合用户个性化需求并有助于其学习成长；"
//            + "   - 保持亲切友好的语气，传递鼓励与支持，激发学习动力。"
//            + "2. 若用户的问题与学习无关（如闲聊、娱乐、无关琐事等）："
//            + "   - 礼貌说明自己专注于学习领域，无法提供相关帮助，引导用户提出学习类问题（包括个性化学习内容需求）。"
//            + "始终专注于学习辅助场景，不回应任何与学习无关的内容。";
//    private static final String PROMPT = "你是舟小云，专注于提供学习帮助的助手。你的核心职责是解答各类学习相关问题，互动规则如下："
//            + "1. 交互启动时："
//            + "   - 根据场景需要（如用户首次提问、主动分享学习进展、提出高质量问题或表现出积极学习态度时），可自然使用亲切问候或具体夸奖开启对话；"
//            + "   - 保持语气友好温暖，体现对用户学习状态的关注。"
//            + "2. 若用户的问题与学习、知识、技能提升相关（包括个性化学习需求，如定制计划、创作学习诗歌等）："
//            + "   - 提供专业清晰、有针对性的解答，内容需贴合用户个性化需求；"
//            + "   - 在解答中传递鼓励与支持，增强用户学习动力；"
//            + "   - 可适时肯定用户的思考深度或进步表现。"
//            + "3. 若用户的问题与学习无关（如闲聊、娱乐等）："
//            + "   - 礼貌说明自己专注于学习辅助，并友好引导用户提出学习类问题；"
//            + "   - 不展开讨论非学习相关话题。"
//            + "4. 每次交互结尾："
//            + "   - 根据上下文自然收尾，并可表达对用户继续探索的期待。"
//            + "始终围绕学习成长场景提供支持，保持回应专注、积极、有建设性。";

    @PostMapping(value = "/chat/text",produces="text/html;charset=UTF-8")
    @Operation(summary = "聊天流式接口")
    public Flux<String> chatStream(@RequestParam(value = "msg") String msg,@RequestParam("cid") String cid) {
        // 检查会话是否存在，不存在则自动创建
        Conversation existingConversation = conversationMapper.selectById(cid);
        if (existingConversation == null) {
            Conversation conversation = new Conversation();
            conversation.setCid(cid);
            // 远程调用获取用户信息
            conversation.setUid(userClient.getUserInfo().getData().getId());
            conversationMapper.insert(conversation);
        }
        // 会话记忆三步：
        // 1.定义会话存储方式（定义为bean）
        // 2.配置会话记忆Advisor （配置到chatClient这个Bean中去）
        // 3.添加会话id
        return chatClient
                // 设置提示词
                .prompt(PROMPT)
                .user(msg)
                .advisors(a -> a
                        // 必须使用指定的id传输值
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, cid)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY,10))
                .stream()
                .content();
    }



    @PostMapping(value = "/chat/create/{cid}")
    @Operation(summary = "创建会话")
    public Result<String> createChat(@PathVariable(value = "cid") String cid) {
        Conversation conversation = new Conversation();
        conversation.setCid(cid);
        // 远程调用获取用户信息
        conversation.setUid(userClient.getUserInfo().getData().getId());
        int insert = conversationMapper.insert(conversation);
        return insert > 0 ? Result.ok(conversation.getCid()) : Result.fail("创建会话失败");
    }

}