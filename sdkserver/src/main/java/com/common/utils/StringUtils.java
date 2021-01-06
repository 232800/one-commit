package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";


    /**
     * 获取字符32位字符串的UUID
     *
     * @return the UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * splitStringComma:以逗号分隔转为数组
     * 描述这个方法的执行流程 – 可选
     * 描述这个方法的注意事项 – 可选
     *
     * @param comma
     * @return String[]
     * @throws
     * @since Ver 1.0
     */
    public static String[] splitStringComma(String comma) {
        if (isEmpty(comma)) {
            return null;
        }
        return comma.split(",");
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为Boolean类型
     * 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 如果对象为空，则使用defaultVal值
     * see: ObjectUtils.toString(obj, defaultVal)
     *
     * @param obj
     * @param defaultVal
     * @return
     */
    public static String toString(final Object obj, final String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

//	/**
//	 * 获得用户远程地址
//	 */
//	public static String getRemoteAddr(HttpServletRequest request){
//		String remoteAddr = request.getHeader("X-Real-IP");
//        if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("X-Forwarded-For");
//        }else if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("Proxy-Client-IP");
//        }else if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
//        }
//        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
//	}

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串
     *                     例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }


    public static boolean notNull(String str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    public static boolean isNull(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }


    /**
     * 去除字符串前后的空格和单引号和双引号
     *
     * @param str
     * @return
     */
    public static String trimQuotes(String str) {
        //这个正则有待测试
//        String regex = "(\\s|\"|\'*)(.*)(\\s|\"|\'*)";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//        str = matcher.group(2);
//        return str;
        //还可以直接操作StringBuffer或者StringBuilder,有空再写吧
        while (true) { //将字符串前面的空格单引号双引号去掉
            if (str.startsWith("\"")
                    || str.startsWith("\'")
                    || str.startsWith(SPACE)
                    || str.startsWith(LF)
                    || str.startsWith(CR)) {
                str = str.substring(1);
                continue;
            }
            break;
        }
        while (true) { //将字符串后面的空格单引号双引号去掉
            if (str.endsWith("\"")
                    || str.endsWith("\'")
                    || str.startsWith(SPACE)
                    || str.startsWith(LF)
                    || str.startsWith(CR)) {
                str = str.substring(0, str.length() - 1);
                continue;
            }
            break;
        }
        return str;
    }

    /**
     * 根据字典code和字典map获取字典描述
     *
     * @param code
     * @param map
     * @return String
     * @exception/throws
     * @since Ver 1.0
     */
    public static String getDicNameByDicIdAndDicMap(String code, Map<String, String> map) {
        String str = "";
        if (StringUtils.isNotEmpty(code)) {
            String[] splits = code.split(",");
            String dicName = null;
            for (String split : splits) {
                if (StringUtils.isNotEmpty(split)) {
                    dicName = map.get(split) == null ? null : map.get(split);
                    if (StringUtils.isNotEmpty(dicName)) {
                        str += dicName + ",";
                    }
                }
            }
            if (StringUtils.isNotEmpty(str)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 根据typeId\字典code和字典map获取字典描述
     *
     * @param code
     * @param map
     * @return String
     * @exception/throws
     * @since Ver 1.0
     */
    public static String getDicNameByDicIdAndDicMap(String code, String typeId, Map<String, String> map) {
        String str = "";
        if (StringUtils.isNotEmpty(code)) {
            String[] splits = code.split(",");
            String dicName = null;
            for (String split : splits) {
                if (StringUtils.isNotEmpty(split)) {
                    String key = typeId + "_" + split;
                    dicName = map.get(key) == null ? null : map.get(key);
                    if (StringUtils.isNotEmpty(dicName)) {
                        str += dicName + ",";
                    }
                }
            }
            if (StringUtils.isNotEmpty(str)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * formatToPojoField:将数据库字段转化为pojo字段(开头小写)：比如：org_name 转为 orgName
     *
     * @param field
     * @return String
     * @exception/throws
     * @since Ver 1.0
     */
    public static String formatToPojoField(String field) {
        String[] fieldStrs = field.split("_");
        if (fieldStrs.length <= 1) {
            return field;
        }
        String pojoField = "";
        String c = "";
        String d = "";
        for (int i = 0; i < fieldStrs.length; i++) {
            c = fieldStrs[i].substring(0, 1);
            d = fieldStrs[i].substring(1, fieldStrs[i].length()).toLowerCase();
            if (i != 0) {
                c = c.toUpperCase();
            } else {
                c = c.toLowerCase();
            }
            pojoField = pojoField + c + d;
        }
        return pojoField;
    }

    /**
     * upperCaseFirstLatter:首字母大写
     *
     * @param str
     * @return String
     * @exception/throws
     * @since Ver 1.0
     */
    public static String upperCaseFirstLatter(String str) {
        char[] charArr = str.toCharArray();
        int code = Integer.valueOf(charArr[0]);
        if (code >= 97 && code <= 122) {
            charArr[0] -= 32;
        }
        return String.valueOf(charArr);
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    public static void main(String[] args) {
        //System.out.println(StringUtils.formatToPojoField("cai_gou_shen_pi_biao"));
        System.out.println(StringUtils.upperCaseFirstLatter("z"));
    }

    public static String getString(String s) {
        return "\'" + s + "\'";
    }

}
