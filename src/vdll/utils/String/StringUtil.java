package vdll.utils.String;


/**
 * @author gaobin
 */
public class StringUtil {
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }


    public static int getChineseCharacterCount(String str) {
        int ccCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isChinese(str.charAt(i))) {
                ccCount++;
            }
        }

        return ccCount;
    }

    public static String toBytesStringTag(String in, String tag) {
        byte[] bytes = in.getBytes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i]);
            sb.append(tag);
        }
        if (sb.length() > tag.length()) {
            sb.delete(sb.length() - tag.length(), sb.length());
        }
        return sb.toString();
    }

    public static String toString(byte[] bs) {
        return new String(bs);
    }


    public static boolean isNoEmpty(Object text) {
        return (text != null && !text.equals(""));
    }

    public static boolean isEmpty(Object text) {
        return !isNoEmpty(text);
    }

    public static String toNumber(Object text){
        StringBuilder sb = new StringBuilder();
        String s = text.toString();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append((short)c);
        }
        return  sb.toString();
    }
    public static String autoGenericCode(Object  code, int num) {
        String result = "";
        // 保留num的位数
 // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", code);
        return result;
    }
}
