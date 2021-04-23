package cn.bookmanager;

import cn.bookmanager.constant.ErrorStatusEnum;
import cn.bookmanager.constant.StatusEnum;
import cn.bookmanager.util.Base64Util;
import org.junit.jupiter.api.Test;

import java.util.Base64;


public class MyTest {

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

    @Test
    public void Base64(){
        System.out.println(Base64Util.encoder("li4"));
        System.out.println(Base64Util.decoder("bGk0RmxhZw"));
    }

    @Test
    public void ErrorStatusEnum(){
        // System.out.println(ErrorStatusEnum.OK.equals("00000"));
        // System.out.println(HttpStatus.OK.value());

        System.out.println(StatusEnum.OFF_THE_SHELF.getClass().getTypeName());
        System.out.println(StatusEnum.OK.getCode());
    }


}