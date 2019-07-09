package com.course.cases;

import com.course.utils.RandomUtil;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Demo3 {
    public static void main(String[] args) throws FileNotFoundException {
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("","");

//    builder.addTextBody("Language", "9");  //添加文本类型参数
    Yaml yaml = new Yaml();
    File dumpFile = new File("E:\\idea\\apitest\\src\\test\\resources\\data.yaml");
    //获取test.yaml文件中的配置数据，然后转换为obj，
        Map<String, String> map = (Map<String, String>) yaml.load(new FileInputStream(dumpFile));
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();

            Map<String, String> map1 = (Map<String, String>) val;

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (String key1 : map1.keySet()) {
                //将LinkedHashMap的value再构建一个Map从而能取到最里面的值
                // System.out.println("key= " + key1.trim() + "; value= " + map1.get(key1));
                NameValuePair pair = new BasicNameValuePair(key1,map1.get(key1));
                if (map1.get(key1).equalsIgnoreCase("RandomUtil.getChineseName()")){
                    pair = new BasicNameValuePair(key1,RandomUtil.getChineseName());
                }
                formparams.add(pair);
            }
            System.out.println(formparams);
    }
}
}
