package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.client.RestfulClient;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class AddUserInfoTest {
    HashMap<String, String> hashHead;
    String result;
    RestfulClient client;
    JSONObject responseBody;
    int responseCode;
    @BeforeClass
    public void setup(){
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        //设置好请求头部
        hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        hashHead.put("Cookie", "style=test;OKLoginFlag-callcenter=FLK_callcenter&zhangxb");
    }
    @SuppressWarnings("deprecation")
	@Test
    public void addUser() throws IOException, InterruptedException {

        client=new RestfulClient();
//        client.yamladd();
        //发出请求
        client.sendPost(TestConfig.addUserUrl, client.yamladd(), hashHead);
    }
    @AfterClass
    public void setdown() throws IOException {
//        responseBody = client.getBodyInJSON();
//        responseCode = client.getCodeInNumber();
//
//        JSONParser jParser = new JSONParser();
//        boolean result = jParser.isResponseCorrect(responseBody, checkPoint, checkValue);
//
//        //断言判断结果
//        Assert.assertTrue(result);
//        Assert.assertEquals(responseCode, 200);
    }
}
