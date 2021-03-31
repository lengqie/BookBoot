package cn.bookmanager.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于返回Json...
 * @author lengqie
 */
public class ReturnMapUtils {


    public static Map getMap(String status, String msg){
        Map<String,String> map =new HashMap<>(1);
        map.put("status",status);
        map.put("msg",msg);
        return map;
    }

    public static Map getMap(String status){
        Map<String,String> map =new HashMap<>(1);
        map.put("status",status);
        map.put("msg","null");
        return map;
    }
    public static Map getMap(){
        Map<String,String> map =new HashMap<>(1);
        map.put("status","404");
        map.put("msg","null");
        return map;
    }

}
