package com.weilai.minio.utils;

import com.google.common.collect.HashMultimap;
import com.weilai.minio.constant.MinioConstant;

import java.time.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.weilai.minio.constant.MinioConstant.UNDERLINE_PATH;

/**
 * 类 CustomUtil 功能描述：<br/>
 * 自定义工具类，提供MinIO文件操作相关的各种辅助方法
 *
 * @author weilai hy
 * @version 0.0.1
 * @date 2023/4/11 12:22
 */
public class CustomUtil {

    /**
     * 私有构造函数，防止实例化工具类
     */
    private CustomUtil() {
    }

    /**
     * 根据Duration对象创建未来时间点的Date对象
     *
     * @param duration 持续时间
     * @return 计算后的日期对象
     */
    public static Date formDuration(Duration duration) {
        return Date.from(Instant.now().plus(duration));
    }

    /**
     * 根据时间和时间单位创建未来时间点的Date对象
     *
     * @param time     时间数值
     * @param timeUnit 时间单位
     * @return 计算后的日期对象
     */
    public static Date formDuration(Integer time, TimeUnit timeUnit) {
        return Date.from(Instant.now().plus(getDuration(time, timeUnit)));
    }

    /**
     * 根据时间和时间单位创建本地日期时间的Date对象
     *
     * @param time     时间数值
     * @param timeUnit 时间单位
     * @return 计算后的日期对象
     */
    public static Date formLocalDateTime(Integer time, TimeUnit timeUnit) {
        return Date.from(getLocalDateTime(time, timeUnit).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据时间和时间单位创建未来的Duration对象
     *
     * @param time     时间数值
     * @param timeUnit 时间单位
     * @return Duration对象
     */
    public static Duration getDuration(Integer time, TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return Duration.ofDays(time);
            case HOURS:
                return Duration.ofHours(time);
            case MINUTES:
                return Duration.ofMinutes(time);
            case SECONDS:
                return Duration.ofSeconds(time);
            case MILLISECONDS:
                return Duration.ofMillis(time);
            case NANOSECONDS:
                return Duration.ofNanos(time);
            default:
                throw new UnsupportedOperationException("Man, use a real TimeUnit unit");
        }
    }

    /**
     * 根据时间和时间单位创建LocalDateTime对象
     *
     * @param time     时间数值
     * @param timeUnit 时间单位
     * @return LocalDateTime对象
     */
    public static LocalDateTime getLocalDateTime(Integer time, TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return LocalDateTime.now().plusDays(time);
            case HOURS:
                return LocalDateTime.now().plusHours(time);
            case MINUTES:
                return LocalDateTime.now().plusMinutes(time);
            case SECONDS:
                return LocalDateTime.now().plusSeconds(time);
            default:
                throw new UnsupportedOperationException("Man, use a real TimeUnit unit");
        }
    }

    /**
     * 获取当前年月日字符串数组，格式为[2025,08,16]
     * 用于构建文件存储的日期路径结构
     *
     * @return 包含年月日的字符串数组
     */
    public static String[] getDateFolder() {
        String[] retVal = new String[3];

        LocalDate localDate = LocalDate.now();
        retVal[0] = localDate.getYear() + "";

        int month = localDate.getMonthValue();
        retVal[1] = month < 10 ? "0" + month : month + "";

        int day = localDate.getDayOfMonth();
        retVal[2] = day < 10 ? "0" + day : day + "";

        return retVal;
    }

    /**
     * 处理对象名称，移除开头的斜杠分隔符
     * 例如: "/test/file.txt" -> "test/file.txt"
     *
     * @param objectName 对象名称
     * @return 处理后的对象名称
     */
    public static String getObjectName(String objectName) {
        return objectName.length() > 1 && objectName.startsWith(com.weilai.minio.constant.MinioConstant.URI_DELIMITER) ? objectName.substring(1) : objectName;
    }

    /**
     * 标准化路径格式
     * 1. 如果路径为空则返回空字符串
     * 2. 移除开头的斜杠分隔符
     * 3. 确保路径以斜杠结尾
     *
     * @param path 原始路径  /text/file
     * @return 标准化后的路径 text/file/
     */
    public static String getPath(String path) {
        if (path == null || "".equals(path)) {
            return "";
        }
        path = path.startsWith(com.weilai.minio.constant.MinioConstant.URI_DELIMITER) ? path.substring(path.indexOf(com.weilai.minio.constant.MinioConstant.URI_DELIMITER) + 1) : path;
        path = path.endsWith(com.weilai.minio.constant.MinioConstant.URI_DELIMITER) ? path : path + MinioConstant.URI_DELIMITER;
        return path;
    }

    /**
     * 创建包含Content-Type的HTTP头部信息
     *
     * @param contentType 内容类型
     * @return HashMultimap格式的头部信息
     */
    public static HashMultimap<String, String> getHeader(String contentType) {
        if (contentType == null || "".equals(contentType)) {
            contentType = "application/octet-stream";
        }
        HashMultimap<String, String> headers = HashMultimap.create();
        headers.put("Content-Type", contentType);
        return headers;
    }

    /**
     * 获取内容类型，如果为空则返回默认值
     *
     * @param contentType 内容类型
     * @return 处理后的内容类型，默认为"application/octet-stream"
     */
    public static String getContentType(String contentType) {
        if (contentType == null || "".equals(contentType)) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }


    /**
     * 获取文件上传的文件名  a.jpg -> 2025/11/02/a.png
     * @param fileOriginalName  原始文件名
     * @return
     */
    public static String getObjectUploadName(String fileOriginalName){
        return String.join(com.weilai.minio.constant.MinioConstant.URI_DELIMITER, getDateFolder()) + com.weilai.minio.constant.MinioConstant.URI_DELIMITER + getFileUploadName(fileOriginalName);
    }

    /**
     * 根据文件原始的名字，获取文件上传的文件名  a.jpg -> 1758589498520_1898a.png
     * @param fileOriginalName  原始文件名名
     * @return
     */
    public static String getFileUploadName(String fileOriginalName){
        // 获取当前时间戳
        String timestamp = Long.toString(System.currentTimeMillis());
        // 生成随机数
        String random = Integer.toString(new Random().nextInt(9999));
        // 拼接文件名：时间戳 + 随机数 + 扩展名
        return timestamp + UNDERLINE_PATH + random + fileOriginalName;
    }


    /**
     * 获取文件的后缀名
     * @param fileName 文件名
     * @return 文件后缀名（不包含点），如果无后缀名则返回空字符串
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == fileName.length() - 1) {
            // 文件名中没有点，或点在最后一位（如 "file."）
            return null;
        }
        return fileName.substring(lastIndexOfDot + 1);
    }





}
