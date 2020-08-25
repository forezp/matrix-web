package io.githib.forezp.vue.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader( String classPath) {

        this.classPath = classPath;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes;
        Class<?> clazz;

        try {
            //获取.class 文件的二进制字节
            bytes = getClassByte(name);
            //将二进制字节转化为Class对象
            clazz = defineClass(name,bytes,0,bytes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return super.findClass(name);
    }


    private byte[] getClassByte(String name) throws IOException {

        String classFile = classPath + File.separator + name.replace(".", File.separator)+".class";
        System.out.println(classFile);

        File file = new File(classFile);

        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int i;
        while (true) {
            i = fileChannel.read(byteBuffer);
            if (i == 0 || i == -1) {
                break;
            }
            byteBuffer.flip();
            writableByteChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        writableByteChannel.close();
        fileChannel.close();
        fileInputStream.close();

        return byteArrayOutputStream.toByteArray();

    }
}

