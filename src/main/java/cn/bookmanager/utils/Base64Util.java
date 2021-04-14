package cn.bookmanager.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

/**
 * @author lengqie
 */
public class Base64Util {


    final  static String salt = "";


    public static String encoder(String s){
        final byte[] bytes = (s+salt).getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
    public static String decoder(String s){
        byte[] decoded = Base64.getDecoder().decode(s);


        s = (new String(decoded));
        return s.substring(0,s.length() - salt.length());
    }
}