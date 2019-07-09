package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddUserInfoTest {
    private String url;
    private CookieStore store;
    @SuppressWarnings("deprecation")
	@Test
    public void addUser() throws IOException, InterruptedException {
        DefaultHttpClient client = new DefaultHttpClient();
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        System.out.println(ConfigFile.getUrl(InterfaceName.ADDUSERINFO));
//        https://isits.axatp.com/callCenter/saveCustomer.do?contactId=
        post.setHeader("Cookie","style=test;OKLoginFlag-callcenter=" +
                "FLK_callcenter&zhangxb");
        //设置头信息
        post.setHeader("Accept-Language","zh-CN");
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; " +
                "Windows NT 10.0; " +
                "WOW64; Trident/7.0; .NET4.0C; .NET4.0E; InfoPath.2)");

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        //客户姓名
        formparams.add(new BasicNameValuePair("partyName", RandomUtil.getChineseName()));
        //性别
        formparams.add(new BasicNameValuePair("gender", "F"));
        //地区编号
        formparams.add(new BasicNameValuePair("areaCode", "22"));
        //手机
        formparams.add(new BasicNameValuePair("phone1", RandomUtil.getTelephone()));
        //证件号码
        formparams.add(new BasicNameValuePair("credentialNumber", CardUtil.getOneCardNo(null)));
        //邮箱地址
        formparams.add(new BasicNameValuePair("emailAddress", RandomUtil.getEmail(5, 10)));
        //邮政编码
        formparams.add(new BasicNameValuePair("postalCode", RandomUtil.getcode()));
        //车型
        formparams.add(new BasicNameValuePair("vehicleType", "大众"));
        //车架号
        formparams.add(new BasicNameValuePair("vehicleFrameNo", CarUtil.createRandomString(17)));
        //车牌
        formparams.add(new BasicNameValuePair("vehicleNumber", CarUtil.getPalte()));
        //联系地址
        formparams.add(new BasicNameValuePair("contactAddress", RandomUtil.getRoad()));
        //发动机号
        formparams.add(new BasicNameValuePair("engineNo", CarUtil.createRandomString(10)));
        //初次登记年月
        formparams.add(new BasicNameValuePair("registerTime", Util.getDate(-500)));
        //保险到期日
        formparams.add(new BasicNameValuePair("insuredueDate", Util.getDate(20)));
        formparams.add(new BasicNameValuePair("insuredueDate2", Util.getDate(20)));
        formparams.add(new BasicNameValuePair("insuredueDateFlag", "1"));
        formparams.add(new BasicNameValuePair("carType", "01"));
        formparams.add(new BasicNameValuePair("credentialType", "1"));
        formparams.add(new BasicNameValuePair("dispatched", "07"));
        formparams.add(new BasicNameValuePair("isOB", "1"));
        formparams.add(new BasicNameValuePair("isPrivate", "Y"));
        formparams.add(new BasicNameValuePair("lastContactBusiness", "IB"));
        formparams.add(new BasicNameValuePair("netFlag", "dxSale"));
        formparams.add(new BasicNameValuePair("sourceId", "409"));
        System.out.println(formparams);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Charset.forName("GBK"));
        post.setEntity(entity);
        HttpResponse response=client.execute(post);
        int statusCode=response.getStatusLine().getStatusCode();
        if(statusCode==200){
            String result=EntityUtils.toString(response.getEntity(),"GBK");
            System.out.println("新增成功");
        }else{
            System.out.println("新增失败");
        }

    }
}
