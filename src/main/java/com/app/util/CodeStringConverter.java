package com.app.util;


import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeStringConverter {

    /**
     * 主转换方法，对应原JavaScript的codeString和setCodeString函数
     * @param codeString 输入的中文描述字符串
     * @return 转换后的号码字符串
     */
    public static String convert(String codeString) {
        if (codeString == null || codeString.trim().isEmpty()) {
            return "";
        }
        codeString = replaceAndConvert(codeString);
        // 生肖对应的值映射
        Map<String, String> shangxiaoValue = getShangxiaoValue();
        // 1. 替换中文描述为对应的号码集合
        String result = codeString.trim()
                .replaceAll("单", "01,03,05,07,09,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,")
                .replaceAll("双", "02,04,06,08,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,")
                .replaceAll("红波", "01,02,07,08,12,13,18,19,23,24,29,30,34,35,40,45,46,")
                .replaceAll("红", "01,02,07,08,12,13,18,19,23,24,29,30,34,35,40,45,46,")
                .replaceAll("蓝波", "03,04,09,10,14,15,20,25,26,31,36,37,41,42,47,48,")
                .replaceAll("蓝", "03,04,09,10,14,15,20,25,26,31,36,37,41,42,47,48,")
                .replaceAll("绿波", "05,06,11,16,17,21,22,27,28,32,33,38,39,43,44,49,")
                .replaceAll("绿波", "05,06,11,16,17,21,22,27,28,32,33,38,39,43,44,49,")
                .replaceAll("金", "01,02,09,10,23,24,31,32,39,40,")
                .replaceAll("木", "05,06,13,14,21,22,35,36,43,44,")
                .replaceAll("水", "11,12,19,20,27,28,41,42,49,")
                .replaceAll("火", "07,08,15,16,29,30,37,38,45,46,")
                .replaceAll("土", "03,04,17,18,25,26,33,34,47,48,")
                .replaceAll("0头", "01,02,03,04,05,06,07,08,09,")
                .replaceAll("零头", "01,02,03,04,05,06,07,08,09,")
                .replaceAll("1头", "10,11,12,13,14,15,16,17,18,19,")
                .replaceAll("一头", "10,11,12,13,14,15,16,17,18,19,")
                .replaceAll("壹头", "10,11,12,13,14,15,16,17,18,19,")
                .replaceAll("2头", "20,21,22,23,24,25,26,27,28,29,")
                .replaceAll("二头", "20,21,22,23,24,25,26,27,28,29,")
                .replaceAll("贰头", "20,21,22,23,24,25,26,27,28,29,")
                .replaceAll("3头", "30,31,32,33,34,35,36,37,38,39,")
                .replaceAll("三头", "30,31,32,33,34,35,36,37,38,39,")
                .replaceAll("叁头", "30,31,32,33,34,35,36,37,38,39,")
                .replaceAll("4头", "40,41,42,43,44,45,46,47,48,49,")
                .replaceAll("四头", "40,41,42,43,44,45,46,47,48,49,")
                .replaceAll("肆头", "40,41,42,43,44,45,46,47,48,49,")
                .replaceAll("0尾", "10,20,30,40,")
                .replaceAll("0尾", "10,20,30,40,")
                .replaceAll("1尾", "01,11,21,31,41,")
                .replaceAll("一尾", "01,11,21,31,41,")
                .replaceAll("壹尾", "01,11,21,31,41,")
                .replaceAll("2尾", "02,12,22,32,42,")
                .replaceAll("二尾", "02,12,22,32,42,")
                .replaceAll("贰尾", "02,12,22,32,42,")
                .replaceAll("3尾", "03,13,23,33,43,")
                .replaceAll("三尾", "03,13,23,33,43,")
                .replaceAll("叁尾", "03,13,23,33,43,")
                .replaceAll("4尾", "04,14,24,34,44,")
                .replaceAll("四尾", "04,14,24,34,44,")
                .replaceAll("肆尾", "04,14,24,34,44,")
                .replaceAll("5尾", "05,15,25,35,45,")
                .replaceAll("五尾", "05,15,25,35,45,")
                .replaceAll("伍尾", "05,15,25,35,45,")
                .replaceAll("6尾", "06,16,26,36,46,")
                .replaceAll("六尾", "06,16,26,36,46,")
                .replaceAll("陆尾", "06,16,26,36,46,")
                .replaceAll("7尾", "07,17,27,37,47,")
                .replaceAll("七尾", "07,17,27,37,47,")
                .replaceAll("柒尾", "07,17,27,37,47,")
                .replaceAll("8尾", "08,18,28,38,48,")
                .replaceAll("八尾", "08,18,28,38,48,")
                .replaceAll("捌尾", "08,18,28,38,48,")
                .replaceAll("9尾", "09,19,29,39,49,")
                .replaceAll("九尾", "09,19,29,39,49,")
                .replaceAll("玖尾", "09,19,29,39,49,");

        // 2. 处理生肖替换（对应原JavaScript的$.each(shangxiaoValue)）
        for (Map.Entry<String, String> entry : shangxiaoValue.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 替换 "生肖, " 格式
            result = result.replaceAll(Pattern.quote(key) + ",\\s*", value + ",");
            // 替换 "生肖、" 格式
            result = result.replaceAll(Pattern.quote(key) + "、", value + ",");
            // 替换 "生肖，" 格式
            result = result.replaceAll(Pattern.quote(key) + "，", value + ",");
            // 替换单独的生肖
            result = result.replaceAll(Pattern.quote(key), value + ",");
        }

        // 3. 统一分隔符格式
        result = result.replaceAll("，", ",")
                .replaceAll("米", ";")
                .replaceAll("-", ",")
                .replaceAll("\\n", ";")
                .replaceAll("=", ":")
                .replaceAll("、", ",")
                .replaceAll("；", ";")
                .replaceAll("。", ",")
                .replaceAll("\\s+", ":")  // 空白字符替换为冒号
                .replaceAll("元", ";")
                .replaceAll("各:", ":")
                .replaceAll(":各", ":")
                .replaceAll("各", ":")
                .replaceAll("::", ":")  // 合并连续的冒号
                .replaceAll(",,", ",")
                .replaceAll("\\.\\.\\.", ":")
                .replaceAll("\\.\\.", ":")
                .replaceAll("\\.", ",")
        ;  // 合并连续的逗号

        return result;
    }

    /**
     * 核心方法：截取→转换→替换
     * @param input 原字符串
     * @return 转换后替换回原位置的字符串
     */
    public static String replaceAndConvert(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        Pattern HEAD_TAIL_PATTERN = Pattern.compile("\\d+[头尾]");
        // 1. 匹配所有 "数字+尾/头" 格式子串
        Matcher matcher = HEAD_TAIL_PATTERN.matcher(input);
        StringBuffer resultBuffer = new StringBuffer();

        while (matcher.find()) {
            String matchedStr = matcher.group(); // 匹配到的子串（如 "034头"、"1258尾"）
            // 2. 转换为单个尾/头（如 "034头" → "0头,3头,4头"）
            String convertedStr = convertToSingle(matchedStr);
            // 3. 替换回原位置（appendReplacement 会自动处理匹配前后的内容）
            matcher.appendReplacement(resultBuffer, convertedStr);
        }

        // 4. 将最后一次匹配后的剩余内容追加到结果中
        matcher.appendTail(resultBuffer);

        return resultBuffer.toString();
    }

    /**
     * 辅助方法：将 "数字+尾/头" 转换为单个尾/头（如 "1258尾" → "1尾,2尾,5尾,8尾"）
     * @param matchedStr 匹配到的子串（如 "034头"）
     * @return 转换后的单个尾/头字符串（如 "0头,3头,4头"）
     */
    private static String convertToSingle(String matchedStr) {
        if (matchedStr == null || matchedStr.isEmpty()) {
            return matchedStr;
        }

        // 提取数字部分和后缀（尾/头）
        String suffix = matchedStr.substring(matchedStr.length() - 1); // 最后一个字符："尾" 或 "头"
        String numberPart = matchedStr.substring(0, matchedStr.length() - 1); // 前面的数字部分

        // 遍历数字，生成单个尾/头（去重）
        List<String> singleList = new ArrayList<>();
        for (int i = 0; i < numberPart.length(); i++) {
            char digit = numberPart.charAt(i);
            String single = digit + suffix;
            if (!singleList.contains(single)) { // 去重
                singleList.add(single);
            }
        }

        // 拼接为逗号分隔字符串
        return String.join("", singleList);
    }

    /**
     * 获取生肖数组（对应原JavaScript的getShangxiao函数）
     * @return 生肖数组
     */
    private static String[] getShangxiao() {
        String jinnian = getCurrentZodiac();  // 修正索引计算
        String sx = ",猪,狗,鸡,猴,羊,马,蛇,龙,兔,虎,牛,鼠";
        int index = sx.indexOf(jinnian);
        String zb = sx.substring(index);
        String yb = sx.substring(0, index);
        String nsx = zb + yb;

        return nsx.split(",");
    }

    /**
     * 生成一个从数字（字符串形式）到生肖的映射。
     * 数字范围从 "01" 到 "49"。
     * 生肖循环使用，顺序为：兔, 虎, 牛, 鼠, 猪, 狗, 鸡, 猴, 羊, 马, 蛇, 龙。
     *
     * @return 一个包含数字到生肖映射的 Map 对象。
     */
    public static Map<String, String> getShangxiaoValueJson() {
        // 1. 定义生肖数组，顺序与 JavaScript 版本一致
        String[] sxarr = getShangxiao();

        // 2. 创建一个 Map 对象来存储键值对
        Map<String, String> sxJson = new HashMap<>();

        // 3. 遍历数字 1 到 49
        for (int i = 1; i < 50; i++) {
            // 4. 格式化数字为两位数的字符串（如 "01", "02"）
            String key = String.format("%02d", i);

            // 5. 计算应该使用的生肖在数组中的索引
            //    j 从 0 开始，每 12 个数循环一次
            int j = (i - 1) % sxarr.length;

            // 6. 将键值对放入 Map
            sxJson.put(key, sxarr[j]);
        }

        return sxJson;
    }

    public static String getShangxiaoValueJson(String key) {
        Map<String, String> stringStringMap = getShangxiaoValueJson();
        return stringStringMap.get(key);
    }

    /**
     * 获取当前年份生肖
     */
    public static String getCurrentZodiac() {
        int year = LocalDate.now().getYear();
        if (year < 0) {
            throw new IllegalArgumentException("年份不能为负数");
        }

        // 核心逻辑：年份差取模12，直接对应生肖数组索引
        int yearDiff = year - 2008;
        // 处理年份小于基准年的情况（如2007年：2007-2008=-1 → (-1 % 12) = 11 → 猪）
        int zodiacIndex = (yearDiff % 12 + 12) % 12;
        String[] ZODIAC_ARRAY = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        return ZODIAC_ARRAY[zodiacIndex];
    }

    /**
     * 获取生肖对应的值映射（对应原JavaScript的getShangxiaoValue函数）
     * @return 生肖值映射
     */
    private static Map<String, String> getShangxiaoValue() {
        String[] sxarr = getShangxiao();
        Map<String, String> sxJson = new HashMap<>();

        for (int i = 0; i < sxarr.length; i++) {
            String val = sxarr[i];
            if (val == null || val.isEmpty()) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                int value = j * 12 + i + 1;
                if (value < 50) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    // 保证两位数格式
                    sb.append(String.format("%02d", value));
                }
            }
            sxJson.put(val, sb.toString());
        }

        return sxJson;
    }


    /**
     * 解析并验证投注字符串。
     *
     * @param tring 格式如 "12:34;23,33:5;05:10" 的投注字符串。
     * @return 一个包含所有有效投注信息的 List，每个元素是一个长度为2的 String 数组 [号码, 价格]。
     */
    public static List<String[]> processCodeString(String tring) {
        String codeString = CodeStringConverter.convert(tring);
        // 使用一个 List 来存储最终解析出的有效 (号码, 价格) 对
        List<String[]> validBets = new ArrayList<>();
        // 1. 检查输入是否为空
        if (codeString == null || codeString.trim().isEmpty()) {
            return validBets;
        }
        codeString = codeString.replaceAll("[\\u4e00-\\u9fa5]", "");

        // 2. 以分号 ";" 分割字符串
        String[] arr = codeString.split(";");

        // 3. 遍历分割后的每个子字符串
        for (String obj : arr) {
            // 检查子字符串是否为空或不包含冒号
            if (obj == null || obj.isEmpty() || !obj.contains(":")) {
                continue; // 跳过无效格式的条目
            }

            // 4. 提取价格部分
            int colonIndex = obj.indexOf(':');
            String priceStr = obj.substring(colonIndex + 1);

            // 5. 验证价格是否为正整数 (使用正则表达式)
            if (!isPositiveInteger(priceStr)) {
                continue; // 跳过价格无效的条目
            }
            // 如果验证通过，可以将价格字符串转为整数
            int price = Integer.parseInt(priceStr);

            // 6. 提取号码部分并以逗号 "," 分割
            String codesPart = obj.substring(0, colonIndex);
            String[] arr2 = codesPart.split(",");

            // 7. 遍历每个号码
            for (String code : arr2) {
                // 8. 验证号码是否为 1-49 之间的正整数
                if (isPositiveInteger(code)) {
                    int codeNum = Integer.parseInt(code);
                    if (codeNum > 0 && codeNum < 50) {
                        // 9. 如果是个位数，则补零
                        String formattedCode = String.format("%02d", codeNum);

                        // 10. 将格式化后的号码和价格存入结果列表
                        validBets.add(new String[]{formattedCode, String.valueOf(price)});
                    }
                }
                // 无效的号码将被静默跳过
            }
        }

        return validBets;
    }

    /**
     * 辅助方法：使用正则表达式检查一个字符串是否为正整数。
     *
     * @param str 需要检查的字符串。
     * @return 如果是正整数则返回 true，否则返回 false。
     */
    private static boolean isPositiveInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // 正则表达式：^ 表示开头，$ 表示结尾，确保整个字符串完全匹配
        // [1-9] 表示第一个字符是1-9的非零数字
        // [0-9]* 表示后面可以跟任意个0-9的数字
        Pattern pattern = Pattern.compile("^[0-9][0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    // 测试用例
    public static void main(String[] args) {
        // 测试输入字符串
        String testInput = "1258尾各5元";
        System.out.println("原始输入: " + testInput);
        testInput = replaceAndConvert(testInput);
        // 调用方法进行处理
        List<String[]> results = processCodeString(testInput);

        // 打印结果
        System.out.println("\n解析出的有效投注:");
        for (String[] bet : results) {
            System.out.println("号码: " + bet[0] + ", 价格: " + bet[1]);
        }
    }

//    // 测试方法
//    public static void main(String[] args) {
//
//        // 测试用例
//        String test1 = "单5元;红波5元;金5元;1头5元;1尾5元猴5元";
//        String test2 = "01-03-10各5元02-04各10元;01,03,10 5;02,04 10";
//
//        System.out.println("测试1输入: " + test1);
//        System.out.println("测试1输出: " + CodeStringConverter.convert(test1));
//
//        System.out.println("\n测试2输入: " + test2);
//        System.out.println("测试2输出: " + CodeStringConverter.convert(test2));
//    }
}