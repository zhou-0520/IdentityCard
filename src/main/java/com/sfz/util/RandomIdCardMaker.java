package com.sfz.util;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 随机姓名生成器
 * @author qz.z
 */
public class RandomIdCardMaker {
    public static Set<String> getFullIdCardList(int count) {
        final long start = Clock.systemDefaultZone().millis();
        // 身份证，使用不可重复集合
        final Set<String> idCardList = new HashSet<String>();

        final List<String> number_1to6 = get_1_to_6_Number(count);
        final List<String> number_7to14 = get_7_to_14_Number(count, 2019, 90);
        final List<String> number_15to17 = get_15_to_17_Number(count);
        final List<String> number_18 = get_18_Number(count);

        for (int i = 0; i < count; i++) {
            idCardList.add(number_1to6.get(i) + number_7to14.get(i)
                    + number_15to17.get(i) + number_18.get(i));
        }
        int realSize = idCardList.size();
        int repetition = count - realSize;

        final long end = Clock.systemDefaultZone().millis();
        System.out.println("生成身份证号码：" + count + "条，" + "重复：" + repetition
                + "条\n" + "去重复后，实际：" + realSize + "条\n" + "耗时：" + (end - start)
                + "ms");
        return idCardList;
    }

    /**
     * 获取身份证 1-6 位数字
     *
     * @return 可重复集合
     */
    private static List<String> get_1_to_6_Number(int count) {
        Random r = new Random();
        List<String> nList_1to6 = new ArrayList<String>(count);
        String number_1_to_6 = "";
        int size = nList_1to6.size();
        final int total = count;

        // 中国省份代号，非规律（真实代号），11 表示北京
        final int n1to2[] = new int[] { 11, 12, 13, 14, 15, 21, 22, 23, 31, 32,
                33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54,
                61, 62, 63, 64, 65, 71, 81, 82 };
        int len = n1to2.length;

        String s1to2 = "";

        int n3to4 = 0;
        String s3to4 = "";

        int n5to6 = 0;
        String s5to6 = "";

        while (size < total) {
            // 省级代号
            s1to2 = String.valueOf(n1to2[r.nextInt(len)]);
            // 城市代号 ，01 ~ 99 两位数
            n3to4 = (r.nextInt(99)) + 1;
            s3to4 = n3to4 < 10 ? "0" + String.valueOf(n3to4) : String
                    .valueOf(n3to4);
            // 县级代号 ，01 ~ 99 两位数
            n5to6 = (r.nextInt(99)) + 1;
            s5to6 = n5to6 < 10 ? "0" + String.valueOf(n5to6) : String
                    .valueOf(n5to6);

            number_1_to_6 = s1to2 + s3to4 + s5to6;// 拼接
            nList_1to6.add(number_1_to_6);
            size = nList_1to6.size();
        }
        return nList_1to6;
    }

    /**
     * 生成随机的 出生日期
     *
     * @param year
     *            当前或目标年份
     * @param oldRange
     *            年龄范围
     * @return 可重复集合
     */
    private static List<String> get_7_to_14_Number(int count, int year,
                                                   int oldRange) {
        Random r = new Random();
        List<String> nList_7to14 = new ArrayList<String>(count);

        final int total = count;
        int size = nList_7to14.size();
        String number_7_to_14 = "";
        // 年份：合理设计，至今应该不超过 90 岁
        int cYear = year;// 今年日期
        int old = 0;
        // 出生年份
        int bornYear = 0;
        String sYear = "";
        // 出生月份
        int month = 0;
        String sMonth = "";
        // 日期
        int day = 0;
        String sDay = "";

        boolean isLeap = false;

        while (size < total) {
            // 年份
            old = r.nextInt(oldRange) + 1;
            bornYear = cYear - old;// 获得出生年份
            sYear = String.valueOf(bornYear);

            // 月份：1-12月，固定
            month = r.nextInt(12) + 1;
            sMonth = month < 10 ? "0" + String.valueOf(month) : String
                    .valueOf(month);

            // 日期，大月31天，小月30天
            if (month == 2) {// 如果是 2 月，判断是不是闰年，2月闰年有 29 天，平年 28 天，

                isLeap = YearUtil.isLeapYear(bornYear);
                if (isLeap) {
                    day = r.nextInt(29) + 1;
                } else {
                    day = r.nextInt(28) + 1;
                }

            } else {

                if (month % 2 == 0) {// 如果是 双月
                    day = r.nextInt(31) + 1;
                } else {// 单月
                    day = r.nextInt(30) + 1;
                }

            }
            sDay = day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);

            number_7_to_14 = sYear + sMonth + sDay;
            nList_7to14.add(number_7_to_14);
            size = nList_7to14.size();
        }
        return nList_7to14;
    }

    /**
     * 获取 15~17 位数，其中第17位，单数代表男，双数代表女
     *
     * @return 可重复集合
     */
    private static List<String> get_15_to_17_Number(int count) {
        Random r = new Random();
        final int total = count;
        List<String> nList_15to17 = new ArrayList<String>(count);
        int size = nList_15to17.size();

        int n15to17 = 0;
        String s15to17 = "";

        while (size < total) {

            n15to17 = r.nextInt(999) + 1;

            if (n15to17 < 10) {
                s15to17 = String.valueOf("00" + n15to17);
            } else if (n15to17 < 100) {
                s15to17 = String.valueOf("0" + n15to17);
            } else {
                s15to17 = String.valueOf(n15to17);
            }

            nList_15to17.add(s15to17);
            size = nList_15to17.size();
        }
        return nList_15to17;
    }

    /**
     * 第18位代表校验码，X 表示数字 10
     *
     * @return 可重复集合
     */
    private static List<String> get_18_Number(int count) {
        Random r = new Random();
        final int total = count;
        List<String> nList_18 = new ArrayList<String>(count);
        int size = nList_18.size();

        String s18 = "";

        while (size < total) {
            // (最后一位为 X，发生几率 < 1% )
            s18 = r.nextInt(100) == 99 ? "X" : String.valueOf(r.nextInt(10));

            nList_18.add(s18);
            size = nList_18.size();
        }
        return nList_18;
    }
}