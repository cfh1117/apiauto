package com.course.utils;

import java.util.*;
public class CardUtil {

    /**
     * 保存区号
     */
    private static Map<String, Integer> registerLocation = new HashMap<String, Integer>();

    /**
     * 启动方法
     *
     * @param args 入参
     */
    public static void main(String[] args) {
        //随机获取10个
        System.out.println(getRandomUserCardNoList(10));

        //生成生日为19901010
        System.out.println(getOneCardNo("19901010"));
    }

    /**
     * 随机获取多个用户身份证号
     *
     * @param count 随机获取个数
     * @return 身份证号列表
     */
    public static List<String> getRandomUserCardNoList(int count) {
        List<String> userCardNoList = new ArrayList<String>();
        for (int index = 0; index < count; index++) {
            userCardNoList.add(getOneCardNo(null));
        }

        return userCardNoList;
    }

    /**
     * 获取一个用户的身份证号
     * 如果用户生日为空，则随机生成 | 如果生日不为空，则生成制定生日的身份证号
     *
     * @param birthday 生日  yyyymmdd格式
     * @return
     */
    public static String getOneCardNo(String birthday) {
        StringBuilder cardNo = new StringBuilder();

        //设置区号
        cardNo.append(randomLocationCode( ));

        //设置生日
        if (birthday != null && !birthday.isEmpty()) {
            cardNo.append(birthday);
        } else {
            cardNo.append(randomBirthday());
        }

        //随机三位数
        cardNo.append(randomCode());

        //设置最后一位
        cardNo.append(getLastBit(cardNo.toString()));

        return cardNo.toString();
    }


    /**
     * 随机获取区号
     * @return 区号
     */
    public static String randomLocationCode() {
        int index = (int) (Math.random() * registerLocation.size());
        Collection<Integer> values = registerLocation.values();
        return String.valueOf(new ArrayList<Integer>(values).get(index));
    }

    /**
     * 随机生成出生日期
     *
     * @return 出生日期
     */
    public static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 40) + 1960);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * 获取最后一位
     *
     * @param str 前17位
     * @return
     */
    public static String getLastBit(String str) {
        char[] chars = str.toCharArray();
        if (chars.length < 17) {
            return " ";
        }
        // 前十七位分别对应的系数
        int[] coefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        // 最后应该取得的第十八位的验证码
        char[] resultChar = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] numberArr = new int[17];
        int result = 0;
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < numberArr.length; i++) {
            result += coefficient[i] * numberArr[i];
        }
        return String.valueOf(resultChar[result % 11]);
    }


    /**
     * 随机获取落户派出所代码（第15、16位） + 性别代码（第17位）
     *
     * @return
     */
    public static String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }


    static {
        registerLocation.put("北京市", 110000);
        registerLocation.put("市辖区", 110100);
        registerLocation.put("东城区", 110101);
        registerLocation.put("西城区", 110102);
        registerLocation.put("崇文区", 110103);
        registerLocation.put("宣武区", 110104);
        registerLocation.put("朝阳区", 110105);
        registerLocation.put("丰台区", 110106);
        registerLocation.put("石景山区", 110107);
        registerLocation.put("海淀区", 110108);
        registerLocation.put("门头沟区", 110109);
        registerLocation.put("房山区", 110111);
        registerLocation.put("通州区", 110112);
        registerLocation.put("顺义区", 110113);
        registerLocation.put("昌平区", 110114);
        registerLocation.put("大兴区", 110115);
        registerLocation.put("怀柔区", 110116);
        registerLocation.put("平谷区", 110117);
        registerLocation.put("密云县", 110228);
        registerLocation.put("延庆县", 110229);
        registerLocation.put("天津市", 120000);
        registerLocation.put("市辖区", 120100);
        registerLocation.put("和平区", 120101);
        registerLocation.put("河东区", 120102);
        registerLocation.put("河西区", 120103);
        registerLocation.put("南开区", 120104);
        registerLocation.put("河北区", 120105);
        registerLocation.put("红桥区", 120106);
        registerLocation.put("东丽区", 120110);
        registerLocation.put("西青区", 120111);
        registerLocation.put("津南区", 120112);
        registerLocation.put("北辰区", 120113);
        registerLocation.put("武清区", 120114);
        registerLocation.put("宝坻区", 120115);
        registerLocation.put("宁河县", 120221);
        registerLocation.put("静海县", 120223);
        registerLocation.put("蓟　县", 120225);
    }


}
