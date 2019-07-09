package com.course.client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import com.course.utils.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class RestfulClient {
    CloseableHttpClient httpclient=HttpClients.createDefault();
    HttpGet httpGet;
    HttpPost httpPost;
    CloseableHttpResponse httpResponse;
    int responseCode;
    JSONObject responseBody;
    HashMap<String, String> responseHeads;
    List<NameValuePair> formparams;
    //ͨ��httpclient��ȡ����ķ���
    public void getResponse(String url) throws ClientProtocolException, IOException {
        httpclient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        httpResponse = httpclient.execute(httpGet);
    }

    //��JSON��ʽ��ȡ������������
    public JSONObject getBodyInJSON() throws ParseException, IOException{
        HttpEntity entity;
        String entityToString;
        entity = httpResponse.getEntity();
        entityToString = EntityUtils.toString(entity);
        responseBody = JSON.parseObject(entityToString);

        System.out.println("This is your response body" + responseBody);

        return responseBody;
    }

    //�Թ�ϣͼ�ķ�ʽ��ȡ������ͷ��
    public HashMap<String, String> getHeaderInHash(){
        Header[] headers;
        headers = httpResponse.getAllHeaders();

        responseHeads = new HashMap<String, String>();

        for(Header header:headers){
            responseHeads.put(header.getName(), header.getValue());
        }

        System.out.println("This is your response header" + responseHeads);

        return    responseHeads;
    }

    //��ȡ����״̬��
    public int getCodeInNumber(){
        responseCode = httpResponse.getStatusLine().getStatusCode();

        System.out.println("This is your response code" + responseCode);

        return responseCode;
    }
    //ͨ��httpclient��ȡpost����ķ���
    public void sendPost(String url, List<NameValuePair> params, HashMap<String, String> headers) throws ClientProtocolException, IOException{
        //����post�������
        httpPost = new HttpPost(url);
        //�������������ʽ
//        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params, Charset.forName("GBK"));
//        httpPost.setEntity(entity);
//        System.out.println(entity);
        httpPost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("GBK")));
//        //����ͷ����Ϣ
        Set<String> set = headers.keySet();
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            String value = headers.get(key);
            httpPost.addHeader(key, value);
//            httpPost.setHeader(key, value);
        }
        httpResponse = httpclient.execute(httpPost);
    }

    //POST����Ӳ���
    public List<NameValuePair> yamladd() throws FileNotFoundException {
        String file = Constants.File_Path + Constants.File_Name2;
//        System.out.println(file);
        Yaml yaml = new Yaml();
        File dumpFile = new File(file);
        //��ȡtest.yaml�ļ��е��������ݣ�Ȼ��ת��Ϊobj��
        Map<String, String> map = (Map<String, String>) yaml.load(new FileInputStream(dumpFile));
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();

            Map<String, String> map1 = (Map<String, String>) val;

             formparams = new ArrayList<NameValuePair>();
            for (String key1 : map1.keySet()) {
                //��LinkedHashMap��value�ٹ���һ��Map�Ӷ���ȡ���������ֵ
                // System.out.println("key= " + key1.trim() + "; value= " + map1.get(key1));
                NameValuePair pair = new BasicNameValuePair(key1, map1.get(key1));
                if (map1.get(key1).equalsIgnoreCase("RandomUtil.getChineseName()")) {
                    pair = new BasicNameValuePair(key1, RandomUtil.getChineseName());
                } else if (map1.get(key1).equalsIgnoreCase("RandomUtil.getTelephone()")) {
                    pair = new BasicNameValuePair(key1, RandomUtil.getTelephone());
                } else if (map1.get(key1).equalsIgnoreCase("CardUtil.getOneCardNo(null)")) {
                    pair = new BasicNameValuePair(key1, CardUtil.getOneCardNo(null));
                } else if (map1.get(key1).equalsIgnoreCase("RandomUtil.getEmail(5, 10)")) {
                    pair = new BasicNameValuePair(key1, RandomUtil.getEmail(5, 10));
                } else if (map1.get(key1).equalsIgnoreCase("RandomUtil.getcode()")) {
                    pair = new BasicNameValuePair(key1, RandomUtil.getcode());
                } else if (map1.get(key1).equalsIgnoreCase("CarUtil.createRandomString(17)")) {
                    pair = new BasicNameValuePair(key1, CarUtil.createRandomString(17));
                } else if (map1.get(key1).equalsIgnoreCase("CarUtil.getPalte()")) {
                    pair = new BasicNameValuePair(key1, CarUtil.getPalte());
                } else if (map1.get(key1).equalsIgnoreCase("RandomUtil.getRoad()")) {
                    pair = new BasicNameValuePair(key1, RandomUtil.getRoad());
                } else if (map1.get(key1).equalsIgnoreCase("CarUtil.createRandomString(10)")) {
                    pair = new BasicNameValuePair(key1, CarUtil.createRandomString(10));
                } else if (map1.get(key1).equalsIgnoreCase("Util.getDate(20)")) {
                    pair = new BasicNameValuePair(key1, Util.getDate(20));
                } else if (map1.get(key1).equalsIgnoreCase("Util.getDate(-500)")) {
                    pair = new BasicNameValuePair(key1, Util.getDate(-500));
                }
                formparams.add(pair);
            }


        }
//        System.out.println(formparams);
        return formparams;
    }
}
