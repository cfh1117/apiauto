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
            //�Ȼ�ȡ�����е�result���һ���ڲ�JSON����
            JSONObject internalJSON = jo.getJSONObject("result");
            //�ٸ��ݼ������Ҽ�ֵ
            city = internalJSON.getString("city");;
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }
    public boolean isResponseCorrect(JSONObject jo,String checkpoint,String passValue){
         //��jsonpath����json����ȡresult���ض���ֵ
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