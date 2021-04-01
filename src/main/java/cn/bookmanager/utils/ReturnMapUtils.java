package cn.bookmanager.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于返回Json...
 * @author lengqie
 */
public class ReturnMapUtils {


    public static Map<String, String> getMap(String status, String msg){
        Map<String,String> map =new HashMap<>(1);
        map.put("status",status);
        map.put("msg",msg);
        return map;
    }

    public static Map<String, String> getMap(String status){
        Map<String,String> map =new HashMap<>(1);
        map.put("status",status);
        map.put("msg","null");
        return map;
    }
    public static Map<String, String> getMap(){
        Map<String,String> map =new HashMap<>(1);
        map.put("status","404");
        map.put("msg","null");
        return map;
    }

}
