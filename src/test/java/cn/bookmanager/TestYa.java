package cn.bookmanager;

import cn.bookmanager.utils.Base64Util;
import cn.bookmanager.utils.ConstantsUtil;
import org.junit.jupiter.api.Test;

import java.util.Base64;


public class TestYa {

    @Test
    public void myTest() throws Exception {
        // System.out.println(ConstantsUtil.COOKIE_ADMIN.value());

        final String s = "Hello";

        final String encoder = Base64Util.encoder(s);
        final String decoder = Base64Util.decoder(encoder);

        System.out.println(encoder);
        System.out.println(decoder);
    }
    @Test
    public void StringTest(){
        final String s = "HelloFlag";
        System.out.println(Base64Util.decoder("SGVsbG9GbGFn").substring(0,s.length() - 4));
        System.out.println(Base64.getEncoder().encodeToString(s.getBytes()));
    }
}