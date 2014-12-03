package com.github.sd4324530.fastweixin.api.response;

import com.github.sd4324530.fastweixin.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 下载多媒体资源响应对象
 *
 * @author peiyu
 */
public class DownloadMediaResponse extends BaseResponse {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadMediaResponse.class);

    private String fileName;
    private byte[] content;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(InputStream content, Integer length) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        try {
            StreamUtil.copy(content, byteOutputStream);
            byte[] temp = byteOutputStream.toByteArray();
            if (temp.length > length) {
                this.content = new byte[length];
                for (int i = 0; i < length; i++) {
                    this.content[i] = temp[i];
                }
            } else {
                this.content = temp;
            }
        } catch (IOException e) {
            LOG.error("异常", e);
        }
    }

    /**
     * 如果成功，则可以靠这个方法将数据输出
     *
     * @param out 调用者给的输出流
     * @throws IOException 写流出现异常
     */
    public void writeTo(OutputStream out) throws IOException {
        out.write(this.content);
        out.flush();
        out.close();
    }
}
