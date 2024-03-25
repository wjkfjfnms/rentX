package com.example.user.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class StringUtil {

    /**
     * 邮箱校验
     *
     * @param email 邮箱
     * @return true or false
     */
    public static boolean checkEmail(String email) {
        String check = "^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$";
        Pattern regex = compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 密码校验（长度 6-18，至少包含1个字母）
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        String check = "(?=.*[a-zA-Z])[a-zA-Z0-9]{6,18}";
        Pattern regex = compile(check);
        Matcher matcher = regex.matcher(password);
        return matcher.matches();
    }

    /**
     * 随机生成六位数字验证码
     */
    public static String randomSixCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    /**
     * 随机生成加密盐（4位随机字母 + 4位固定特殊字符）
     */
    public static String randomEncryptedSalt() {
        return RandomStringUtils.randomAlphabetic(4) + "#!$@";
    }

    /**
     * 对象为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String && "".equals(((String) object).trim())) {
            return true;
        }
        if (object instanceof List && ((List) object).size() == 0) {
            return true;
        }
        if (object instanceof Map && ((Map) object).isEmpty()) {
            return true;
        }
        if (object instanceof CharSequence && ((CharSequence) object).length() == 0) {
            return true;
        }
        if (object instanceof Arrays && (Array.getLength(object) == 0)) {
            return true;
        }
        return false;
    }

    /**
     * 对象不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 查询字符串中某个字符首次出现的位置 从1计数
     *
     * @param string 字符串
     * @param c
     * @return
     */
    public static int strFirstIndex(String c, String string) {
        Matcher matcher = compile(c).matcher(string);
        if (matcher.find()) {
            return matcher.start() + 1;
        } else {
            return -1;
        }
    }

    /**
     * 两个对象是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 instanceof String && obj2 instanceof String) {
            obj1 = ((String) obj1).replace("\\*", "");
            obj2 = ((String) obj2).replaceAll("\\*", "");
            if (obj1.equals(obj2) || obj1 == obj2) {
                return true;
            }
        }
        if (obj1.equals(obj2) || obj1 == obj2) {
            return true;
        }
        return false;
    }

    /**
     * 根据字节截取内容
     *
     * @param bytes   自定义字节数组
     * @param content 需要截取的内容
     * @return
     */
    public static String[] separatorByBytes(double[] bytes, String content) {
        String[] contentArray = new String[bytes.length];
        double[] array = new double[bytes.length + 1];
        array[0] = 0;
        //复制数组
        System.arraycopy(bytes, 0, array, 1, bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            content = content.substring((int) (array[i] * 2));
            contentArray[i] = content;
        }
        String[] strings = new String[bytes.length];
        for (int i = 0; i < contentArray.length; i++) {
            strings[i] = contentArray[i].substring(0, (int) (bytes[i] * 2));
        }
        return strings;
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }


    /**
     * 将字符串str每隔2个分割存入数组
     *
     * @param str
     * @return
     */
    public static String[] setStr(String str) {
        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strings = new String[m];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                //每隔两个
                strings[j] = "" + str.charAt(i);
            } else {
                strings[j] = strings[j] + str.charAt(i);
                j++;
            }
        }
        return strings;
    }


    /**
     * 定义一个StringBuffer，利用StringBuffer类中的reverse()方法直接倒序输出
     * 倒叙字符串
     *
     * @param s
     */
    public static String reverseString2(String s) {
        if (s.length() > 0) {
            StringBuffer buffer = new StringBuffer(s);
            return buffer.reverse().toString();
        } else {
            return "";
        }
    }

    /**
     * 截取字符串中的所有日期时间
     *
     * @param str
     * @return
     */
    public static List<String> dateTimeSubAll(String str) {
        try {
            List<String> dateTimeStrList = new ArrayList<>();
            String regex = "[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}";
            Pattern pattern = compile(regex);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                dateTimeStrList.add(group);
            }
            return dateTimeStrList;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    /**
     * 截取字符串中的所有日期
     *
     * @param str
     * @return
     */
    public static List<String> dateSubAll(String str) {
        try {
            List<String> dateStrList = new ArrayList<>();
            Pattern pattern = compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                dateStrList.add(group);
            }
            return dateStrList;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }



}
