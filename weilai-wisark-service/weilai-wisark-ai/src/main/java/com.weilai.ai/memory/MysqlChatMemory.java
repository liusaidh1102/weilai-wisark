package com.weilai.ai.memory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weilai.ai.mapper.ChatMessageMapper;
import com.weilai.ai.model.ChatMessage;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
// 自定义ChatMemory
public class MysqlChatMemory implements ChatMemory {

    @Resource
    private ChatMessageMapper chatMessageMapper;

    private static final Integer IS_DELETED = 1;
    private static final Integer NO_DELETED = 0;

    @Override
    public void add(String cid, List<Message> messages) {
        // 信息存入数据库
        List<ChatMessage> chatMessages = messages.stream().map(message -> {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setCid(cid);
            chatMessage.setContent(message.getContent());
            chatMessage.setMessageType(message.getMessageType().name());
            // 其他字段可根据需要补充
            return chatMessage;
        }).toList();
        // 插入数据库
        for (ChatMessage chatMessage : chatMessages){
            chatMessageMapper.insert(chatMessage);
        }
    }

    /**
     *
     * @param conversationId
     * @param lastN 获取最近的n条信息
     * @return
     */
    @Override
    public List<Message> get(String conversationId, int lastN) {
        // 从数据库查最近的n条信息
        List<ChatMessage> chatMessages = chatMessageMapper.selectList(
                new LambdaQueryWrapper<ChatMessage>()
                        .eq(ChatMessage::getCid, conversationId)
                        .eq(ChatMessage::getIsDeleted, NO_DELETED) // 只查询未删除的记录
                        .orderByDesc(ChatMessage::getCreateTime)
                        .last("LIMIT " + lastN)
        );
        // 转换为Message类型列表
        return chatMessages.stream()
                .map(chatMsg -> {
                    // 根据消息角色转换为对应的Message实现类
                    // 假设ChatMessage有getRole()方法，返回"USER"或"ASSISTANT"
                    if ("USER".equals(chatMsg.getMessageType())) {
                        return new UserMessage(chatMsg.getContent());
                    } else if ("ASSISTANT".equals(chatMsg.getMessageType())) {
                        return new AssistantMessage(chatMsg.getContent());
                    }
                    // 处理其他可能的角色类型，如系统消息
                    else if ("SYSTEM".equals(chatMsg.getMessageType())) {
                        return new SystemMessage(chatMsg.getContent());
                    }
                    // 默认为未知消息类型
                    else {
                        return new UserMessage(chatMsg.getContent());
                    }
                })
                // 由于查询时用了降序，这里需要反转列表恢复时间顺序
                .sorted(Comparator.comparingInt(m ->
                        chatMessages.indexOf(m) // 保持原始时间顺序
                ))
                .collect(Collectors.toList());
    }


    @Override
    public void clear(String conversationId) {
        // 软删除信息
        ChatMessage updateEntity = new ChatMessage();
        updateEntity.setIsDeleted(1); // 设置删除标志为1表示已删除

        chatMessageMapper.update(updateEntity,
                new LambdaQueryWrapper<ChatMessage>()
                        .eq(ChatMessage::getCid, conversationId)
                        .eq(ChatMessage::getIsDeleted, IS_DELETED) // 只更新未删除的记录
        );
    }
}