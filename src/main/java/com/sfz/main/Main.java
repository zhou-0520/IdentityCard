package com.sfz.main;

import com.sfz.util.*;

import java.util.Iterator;
import java.util.List;



/**
 * 随机姓名生成器
 * @author qz.z
 */
public class Main {
    public static void main(String[] args) {

        int count = 10000;

        final List<String> names = RandomNameMaker.getFullNameList(count);
        Iterator<String> iterator = RandomIdCardMaker.getFullIdCardList(count).iterator();

        int size = names.size() - 1;
        String identityCard = "";

        while (iterator.hasNext()) {
            identityCard = iterator.next();
            System.out.println("身份证：" + identityCard + " - 姓名："
                    + names.get(size) + " - 性别：" + YearUtil.getGender(identityCard) + " - 手机号：" +PhoneRandom.getTel() + " - Mac：" + MacRandom.randomMac4Qemu()
                    + " - 经纬度：" + LonLatRandom.randomLonLat(112.1212, 134.1212, 43.12, 65.123)
                    + " - IP：" + ipRandom.getRandomIp()
                    + " - 年龄：" + YearUtil.getAge(2019, identityCard));
            size--;
        }
    }
}