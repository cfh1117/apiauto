package com.course.cases;




import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;

public class Demo2 {
    public static void main(String[] args) {
        try {
            Yaml yaml = new Yaml();
            File dumpFile = new File("E:\\idea\\apitest\\src\\test\\resources\\demo.yaml");
            //��ȡtest.yaml�ļ��е��������ݣ�Ȼ��ת��Ϊobj��
            Object load = yaml.load(new FileInputStream(dumpFile));
            System.out.println(yaml.dump(load));
            System.out.println("#############################################################################");
            //Ҳ���Խ�ֵת��ΪMap
            Map<String, String> map = (Map<String, String>) yaml.load(new FileInputStream(dumpFile));
            System.out.println(map);
            System.out.println("#############################################################################");

            /**
             for (String key : map.keySet()) {
             System.out.println("key= "+ key + "; value= " + map.get(key));
             }
             ���������������������Map��key, value����ʽ�ģ����������������ʽ�ľͻᱨ��java.util.LinkedHashMap cannot be cast to java.lang.String
             */
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                Object val = entry.getValue();

                if (key.equals("ip") | key.equals("port")) {
                    System.out.println("------map------");
                    String string = (String) val;    //��Ϊval��7788��ʽ�����Կ���ǿת��String���������val��{port=9000}���޷�ǿת��String
                    System.out.println("ket:value " + key + ":" + string);
                } else {
                    System.out.println("------LinkedHashMap------");
                    System.out.println("ket:value " + key + ":" + val);
                    if (key.equals("server")) {
                        Map<String, Integer> map1 = (Map<String, Integer>) val;
                        for (String key1 : map1.keySet()) {
                            //��LinkedHashMap��value�ٹ���һ��Map�Ӷ���ȡ���������ֵ
//                            System.out.println("key= " + key1 + "; value= " + map1.get(key1));
                        System.out.println("key1  "+key1);
                        }
                    }
                }
            }
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
