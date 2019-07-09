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
            //获取test.yaml文件中的配置数据，然后转换为obj，
            Object load = yaml.load(new FileInputStream(dumpFile));
            System.out.println(yaml.dump(load));
            System.out.println("#############################################################################");
            //也可以将值转换为Map
            Map<String, String> map = (Map<String, String>) yaml.load(new FileInputStream(dumpFile));
            System.out.println(map);
            System.out.println("#############################################################################");

            /**
             for (String key : map.keySet()) {
             System.out.println("key= "+ key + "; value= " + map.get(key));
             }
             上面这个遍历方法适用于Map（key, value）形式的，而这个里有链表形式的就会报错java.util.LinkedHashMap cannot be cast to java.lang.String
             */
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                Object val = entry.getValue();

                if (key.equals("ip") | key.equals("port")) {
                    System.out.println("------map------");
                    String string = (String) val;    //因为val是7788形式的所以可以强转成String，而下面的val是{port=9000}则无法强转成String
                    System.out.println("ket:value " + key + ":" + string);
                } else {
                    System.out.println("------LinkedHashMap------");
                    System.out.println("ket:value " + key + ":" + val);
                    if (key.equals("server")) {
                        Map<String, Integer> map1 = (Map<String, Integer>) val;
                        for (String key1 : map1.keySet()) {
                            //将LinkedHashMap的value再构建一个Map从而能取到最里面的值
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
