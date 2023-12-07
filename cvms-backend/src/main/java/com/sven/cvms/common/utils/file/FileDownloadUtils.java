package com.sven.cvms.common.utils.file;

import com.sven.cvms.common.constant.Constants;
import com.sven.cvms.common.utils.StringUtils;
import com.sven.cvms.framework.config.CvmsConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author shiwei
 * @date 2023/12/07 上午 09:51
 * @email shiwei@rmitec.cn
 */
public class FileDownloadUtils {

    /**
     * 上传路径
     */
    private static final String profile = CvmsConfig.getProfile();


    /**
     * 下载
     * 下载文件直接将文件写入输出流
     *
     * @param response httpResponse
     * @param filePath 文件路径 完整路径包括文件名，前缀需要包含 {@link Constants} RESOURCE_PREFIX
     * @param fileName 文件名 下载到前端的文件名
     */
    public static void downloadToHttp(HttpServletRequest request, HttpServletResponse response, String filePath,
                                      String fileName) throws IOException {
        // 数据库资源地址
        String downloadPath = profile + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX);
        // 下载名称
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        fileName = FileUtils.setFileDownloadHeader(request, fileName);
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setHeader("download-filename", fileName);
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

}
