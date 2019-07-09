package com.course.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Util {
    public static List<NameValuePair> formparamsList(){
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      return formparams;

    }
    public static void formparamsAdd(String name,String value){
        formparamsList().add(new BasicNameValuePair(name,value));
    }
    //生成日期
    public static String getDate(int date){
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + date); // 获取两天后日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
    return df.format(calendar.getTime());
    }
}
