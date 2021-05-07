package com.github.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class ZipUtils {

    public static final int BUFFER_SIZE = 1024;

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    /*
     * 压缩整个文件夹中的所有文件，可以实现边压缩边下载
     * @param filepath 文件所在目录
     * @param outputStream 输出流
     * @param dirFlag zip文件中是否包含多级文件夹，true包含 false没有
     * 2015年6月9日
     */
    public static void zipMultiFile(String filepath, OutputStream outputStream, boolean dirFlag) throws Exception {
        long start = System.currentTimeMillis();
        try {
            File file = new File(filepath);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File fileSec : files) {
                    if (dirFlag) {
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    } else {
                        recursionZip(zipOut, fileSec, "");
                    }
                }
            }

            zipOut.close();
            LOGGER.info("········································》压缩耗时：{}", (System.currentTimeMillis() - start));
        } catch (Exception e) {
            LOGGER.error("压缩文件异常！", e);
            throw e;
        }
    }

    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileSec : files) {
                recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
            }
        } else {
            byte[] buf = new byte[BUFFER_SIZE];
            InputStream inputStream = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                zipOut.write(buf, 0, len);
            }
            inputStream.close();
            zipOut.closeEntry();
        }

    }

}
