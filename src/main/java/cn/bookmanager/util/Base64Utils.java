package cn.bookmanager.util;


import java.util.Base64;

/**
 * @author lengqie
 */
public class Base64Utils {


    final  static String SALT = "Flag";


    public static String encoder(String s){
        final byte[] bytes = (s+ SALT).getBytes();
        String base = Base64.getEncoder().encodeToString(bytes);

        // 不要= 结尾,（适当减少修改的可能... 聊胜于无..吧）
        // 或者使用 Jwt..
        while (true){
            if (base.endsWith("=")){
                base = base.substring(0,base.length() -1);
            }
            else{
                break;
            }
        }
        return base;
    }
    public static String decoder(String s){
        byte[] decoded = Base64.getDecoder().decode(s);


        s = (new String(decoded));
        return s.substring(0,s.length() - SALT.length());
    }
}