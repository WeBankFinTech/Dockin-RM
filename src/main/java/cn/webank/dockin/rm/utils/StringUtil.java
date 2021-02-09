package cn.webank.dockin.rm.utils;

public class StringUtil {

    public static String substring(String s, int len) {
        if (s == null) {
            return null;
        }

        if (s.length() <= len) {
            return s;
        } else {
            return s.substring(0, len);
        }
    }
}
