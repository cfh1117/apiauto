package com.course.cases;

import com.course.client.RestfulClient;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AddUserInfoTest2 {
    private String url;
    private CookieStore store;
    private CloseableHttpResponse response;
    String result;
    @SuppressWarnings("deprecation")
	@Test
    public void addUser() throws IOException, InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
//        System.out.println(ConfigFile.getUrl(InterfaceName.ADDUSERINFO));
//        https://isits.axatp.com/callCenter/saveCustomer.do?contactId=
        post.setHeader("Cookie","style=test;OKLoginFlag-callcenter=" +
                "FLK_callcenter&zhangxb");
        //设置头信息
        post.setHeader("Accept-Language","zh-CN");
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; " +
                "Windows NT 10.0; " +
                "WOW64; Trident/7.0; .NET4.0C; .NET4.0E; InfoPath.2)");
//        Header header = new BasicHeader("Range", "bytes=" + startIndex + "-" + endIndex);
        RestfulClient rest=new RestfulClient();
        rest.yamladd();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(rest.yamladd(), Charset.forName("GBK"));
        post.setEntity(entity);
        System.out.println(entity);
        try{
            response =client.execute(post);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode==200){
                result=EntityUtils.toString(response.getEntity(),"GBK");
                System.out.println("新增成功");
            }else{
                System.out.println("新增失败");
            }
        }
        catch (Exception e) {
//            e.printStackTrace();
        }finally {
            response.close();

        }

//        rest.sendPost(TestConfig.addUserUrl,);

    }
}
