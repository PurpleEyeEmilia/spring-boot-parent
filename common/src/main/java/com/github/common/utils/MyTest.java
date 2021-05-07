package com.github.common.utils;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class MyTest {

    public static void main(String[] args) {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("D:\\mytest1.txt", "rw");
            RandomAccessFile toFile = new RandomAccessFile("D:\\mytest2.txt", "rw");

            FileChannel fromFileChannel = fromFile.getChannel();
            FileChannel toFileChannel = toFile.getChannel();

            int byteSize = 1024 * 4;

            ByteBuffer byteBuffer = ByteBuffer.allocate(byteSize);

            int b = 0;
            while ((b = fromFileChannel.read(byteBuffer)) != -1){
                //切换到读模式
                byteBuffer.flip();

                //读取缓冲区数据写到目标channel
                toFileChannel.write(byteBuffer);

                byteBuffer.clear();
            }

            fromFileChannel.close();
            toFileChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
