package com.github.sd4324530.fastweixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 套接字工具类
 *
 * @author peiyu
 */
public final class StreamUtil {

    private StreamUtil() {
    }

    /**
     * 将输入流的内容复制到输出流里
     *
     * @param in  输入流
     * @param out 输出流
     * @return 复制的数据字节数
     * @throws IOException IO异常
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        BeanUtil.requireNonNull(in, "No InputStream specified");
        BeanUtil.requireNonNull(out, "No OutputStream specified");
        int byteCount = 0;
        byte[] buffer = new byte[4096];
        int bytesRead1;
        for (; (bytesRead1 = in.read(buffer)) != -1; byteCount += bytesRead1) {
            out.write(buffer, 0, bytesRead1);
        }
        out.flush();
        return byteCount;
    }
}
