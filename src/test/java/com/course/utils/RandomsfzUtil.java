package com.course.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class RandomsfzUtil {

    public String getString(){
        String str1_6[]={"110101","150422","120101","130101","330101","440101","440113","441201"
                ,"340101","340201","450101","150101","150401","150423","150602","150801","152501","152921"};

//年龄需要满18周岁
        String  str7_9[]={"189","178","168","158"};

        String str10 = getRandomStr(9);
        String str10_12 = getRandomStr(12);
        str10_12 = str10_12.length() < 2 ? str10_12 : "0"+str10_12;

        String str12_14 = getRandomStr(27);
        str12_14 = str12_14.length()<2?str12_14:"0"+str12_14;

        String str14_17 = getRandomStr(999);
        if(str14_17.length() == 1){
            str14_17="00"+str14_17;
        }
        if(str14_17.length() == 2){
            str14_17="0"+str14_17;
        }

        String tempStr=str1_6[(int) (Math.random()*17+1)] + str7_9[(int) (Math.random()*3+1)] + str10 + str10_12 + str12_14 + str14_17;

        //7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 2
        int[]  multiplier = new int[]{ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int checkDigit = 0;

        //计算校验位
        for(int i = 0; i <multiplier.length; i++){
            checkDigit += multiplier[i]*Integer.parseInt(String.valueOf(tempStr.charAt(i)));
        }
        String MapArray[]={"1","0","X","9","8","7","6","5","4","3","2"};
        String  parityBit=MapArray[checkDigit % 11];

        System.out.println(tempStr + parityBit);
        return tempStr +parityBit;
    }

    private String getRandomStr(int index){
        return String.valueOf((int)(Math.random()*index+1));
    }
}
