package com.weilai.minio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.minio.entity.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapper<File> {
}
