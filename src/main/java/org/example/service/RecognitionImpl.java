package org.example.service;

import org.example.base.Result;
import org.example.util.OcrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecognitionImpl implements Recognition{
    Logger logger = LoggerFactory.getLogger(RecognitionImpl.class);
    @Override
    public Result RecognizeImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.fail( "文件为空");
        }
        // 限制文件大小（最大 5MB）
        long maxFileSize = 5 * 1024 * 1024; // 5MB
        if (multipartFile.getSize() > maxFileSize) {
            return Result.fail("文件过大，请上传小于 5MB 的图片");
        }
        // 校验文件类型
        String contentType = multipartFile.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            return Result.fail("请上传有效的图片文件");
        }
        File tempFile = null;
        try {
            // 1. 创建临时文件
            String originalFilename = multipartFile.getOriginalFilename();
            tempFile = File.createTempFile("upload_", originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".tmp");
            multipartFile.transferTo(tempFile);
            // 2. 传入你的工具类进行识别
            String result = OcrUtil.ocrByImage(tempFile);
            // 清理识别结果：去除首尾空格、多余空行、行内多余空格等
            String cleanedResult = Arrays.stream(result.split("\n"))
                    .map(String::trim)                       // 每行去除首尾空格
                    .filter(line -> !line.isEmpty())          // 去除空行
                    .map(RecognitionImpl::removeSpaceBetweenChinese) // 去除汉字之间的空格
                    .collect(Collectors.joining("\n"))        // 重新组合成字符串
                    .trim();
            Map<String, String> response = new HashMap<>();
            response.put("text", cleanedResult);
            return Result.ok(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.fail("失败"+e);
        } finally {
            // 3. 删除临时文件
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
    /**
     * 去除两个汉字之间的空格
     * @param line 原始行
     * @return 处理后的行
     */
    private static String removeSpaceBetweenChinese(String line) {
        if (line == null) {
            return "";
        }
        return line.replaceAll("(?<=[\\u4e00-\\u9fa5])\\s+(?=[\\u4e00-\\u9fa5])", "");
    }
}
