package com.wxcm.vtvi.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * @author GZH
 */
public class FileUtil {

    /**
     * 获取文件编码格式
     * @param path 文件绝对路径
     * @return 编码格式
     * @throws Exception
     */
    public static String getFileEncoding(String path) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(path));
        int p = (bin.read() << 8) + bin.read();
        String code;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        System.out.println(path + "格式为：" + code);
        return code;
    }
}
