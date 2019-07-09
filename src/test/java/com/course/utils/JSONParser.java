package com.course.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JSONParser {
    JSONObject internalJSON;

    public String getCity(JSONObject jo){
        String city = "";
        try {
            //先获取反馈中的result这个一个内部JSON对象　
            JSONObject internalJSON = jo.getJSONObject("result");
            //再根据键名查找键值
            city = internalJSON.getString("city");;
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }
    public boolean isResponseCorrect(JSONObject jo,String checkpoint,String passValue){
         //用jsonpath处理json，获取result中特定键值
        ReadContext context = JsonPath.parse(jo);
        JSONArray result = context.read("$.result.."+checkpoint);
        String resultString = result.get(0).toString();
        if(resultString.equals(passValue)){
            return true;
        }else{
            return false;
        }
    }
}