package com.yushu.utils;

import java.security.MessageDigest;
import java.util.*;

public class CommonUtils {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
    }

    public static String MD5(String data){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static SortedMap<String,String> mapToSortedMap(Map<String,String> map){
        SortedMap sortedMap = new TreeMap();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            sortedMap.put(key,value);
        }
        return sortedMap;
    }
}
