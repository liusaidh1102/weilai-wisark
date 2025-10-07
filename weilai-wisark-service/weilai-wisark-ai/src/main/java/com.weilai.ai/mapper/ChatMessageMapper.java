package com.weilai.ai.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.ai.model.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
}