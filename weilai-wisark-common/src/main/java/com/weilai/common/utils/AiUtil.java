package com.weilai.common.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class AiUtil {

    /**
     * 简单标题生成 - 基于问题类型识别
     * @param content 用户提问内容
     * @return 生成的标题
     */
    public String generateSimpleTitle(String content) {
        if (content == null || content.isEmpty()) {
            return "新会话";
        }

        // 移除多余空格和特殊字符
        String cleanContent = content.trim().replaceAll("[\\r\\n\\t]+", " ");

        // 如果是问题，提取问句部分
        if (cleanContent.contains("？") || cleanContent.contains("?")) {
            int questionMarkIndex = Math.max(cleanContent.indexOf("？"), cleanContent.indexOf("?"));
            cleanContent = cleanContent.substring(0, questionMarkIndex + 1);
        }

        // 限制长度
        return cleanContent.length() > 20 ? cleanContent.substring(0, 20) + "..." : cleanContent;
    }


//    /**
//     * 基于NLP提取关键词生成标题
//     * @param content 用户提问内容
//     * @return 生成的标题
//     */
//    public String generateTitleFromContent(String content) {
//        try {
//            // 配置CoreNLP管道
//            Properties props = new Properties();
//            props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
//            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//            // 处理文本
//            Annotation document = new Annotation(content);
//            pipeline.annotate(document);
//
//            // 提取关键名词和动词作为标题关键词
//            StringBuilder titleBuilder = new StringBuilder();
//            document.get(CoreAnnotations.TokensAnnotation.class).stream()
//                    .filter(token -> {
//                        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//                        // 提取名词(NN*)和动词(VB*)作为关键词
//                        return pos.startsWith("NN") || pos.startsWith("VB");
//                    })
//                    .limit(5) // 限制关键词数量
//                    .forEach(token -> {
//                        if (!titleBuilder.isEmpty()) titleBuilder.append(" ");
//                        titleBuilder.append(token.get(CoreAnnotations.TextAnnotation.class));
//                    });
//
//            String title = titleBuilder.toString();
//            // 限制标题长度
//            return title.length() > 20 ? title.substring(0, 20) + "..." : title;
//        } catch (Exception e) {
//            log.warn("标题生成失败，使用默认方式: {}", e.getMessage());
//            // 备用方案：直接截取内容
//            return content.length() > 15 ? content.substring(0, 15) + "..." : content;
//        }
//    }




}
