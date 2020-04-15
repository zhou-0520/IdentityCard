package com.sfz.util;


/**
 * 随机姓名生成器
 * @author qz.z
 */
public class YearUtil {
    /**
     * 判断是否是闰年
     *
     * @param year
     * @return 是闰年
     */
    public static boolean isLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0 || year % 100 == 0 || year % 400 == 0) {
            isLeap = true;
        }
        return isLeap;
    }

    /**
     * 通过计算差值，获取年龄
     *
     * @param year
     *            今年
     * @param identityCard
     *            身份证号码
     * @return 年龄
     */
    public static int getAge(int year, String identityCard) {
        int age = 0;
        if (identityCard != null && identityCard.length() == 18) {

            age = year - Integer.valueOf(identityCard.substring(6, 10));
        }
        return age;
    }

    /**
     * 根据身份证第 17 位，判断男（单），女（双）
     *
     * @param identityCard
     * @return
     */
    public static String getGender(String identityCard) {
        if (identityCard != null && identityCard.length() == 18) {
            return Integer.valueOf(identityCard.charAt(16)) % 2 == 0 ? "女"
                    : "男";
        }
        return null;
    }
}