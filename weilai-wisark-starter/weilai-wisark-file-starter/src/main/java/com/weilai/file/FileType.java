package com.weilai.file;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.mime.MediaType;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.tika.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
@Slf4j
public class FileType {

    /**
     * 获取文件的 mine type类型
     * @param file
     * @return
     */
    public static String getMimeType(File file) {
        //创建自动检测解析器
        AutoDetectParser parser = new AutoDetectParser();
        //设置解析器
        parser.setParsers(new HashMap<MediaType, Parser>());
        //创建元数据
        Metadata metadata = new Metadata();
        //添加文件名
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());
        //因为tika判断的是file，所以要将multipartFile转化成输入流
        try (InputStream stream = Files.newInputStream(file.toPath())) {
            // 解析文件流，获取文件的元数据信息
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        } catch (IOException e) {
            // 捕获IO异常并记录错误日志
            log.error("文件类型识别失败 - IO异常", e);
        } catch (TikaException e) {
            // 捕获Tika异常并记录错误日志，然后抛出运行时异常
            log.error("文件类型识别失败 - Tika异常", e);
        } catch (SAXException e) {
            // 捕获SAX异常并记录错误日志，然后抛出运行时异常
            log.error("文件类型识别失败 - SAX异常", e);
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }
}