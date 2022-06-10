package cn.lvhaosir.utils;

import java.io.UnsupportedEncodingException;


public class CommonUtils {

    public static String changeCharset(String name) throws UnsupportedEncodingException {
        return new String(name.getBytes("iso8859-1"),"UTF-8");
    }
}
