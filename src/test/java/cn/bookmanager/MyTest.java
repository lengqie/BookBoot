package cn.bookmanager;

import cn.bookmanager.constant.StatusEnum;
import cn.bookmanager.util.Base64Utils;
import cn.bookmanager.util.Md5Utils;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.Test;

import java.util.Base64;


public class MyTest {

    @Test
    public void myTest() throws Exception {
        // System.out.println(ConstantsUtil.COOKIE_ADMIN.value());

        final String s = "Hello";

        final String encoder = Base64Utils.encoder(s);
        final String decoder = Base64Utils.decoder(encoder);

        System.out.println(encoder);
        System.out.println(decoder);
    }
    @Test
    public void StringTest(){
        final String s = "HelloFlag";
        System.out.println(Base64Utils.decoder("SGVsbG9GbGFn").substring(0,s.length() - 4));
        System.out.println(Base64.getEncoder().encodeToString(s.getBytes()));
    }

    @Test
    public void Base64(){
        System.out.println(Base64Utils.encoder("li4"));
        System.out.println(Base64Utils.decoder("bGk0RmxhZw"));
    }

    @Test
    public void ErrorStatusEnum(){
        // System.out.println(ErrorStatusEnum.OK.equals("00000"));
        // System.out.println(HttpStatus.OK.value());

        System.out.println(StatusEnum.OFF_THE_SHELF.getClass().getTypeName());
        System.out.println(StatusEnum.OK.getCode());
    }
    @Test
    public void Md5(){
        // System.out.println(ErrorStatusEnum.OK.equals("00000"));
        // System.out.println(HttpStatus.OK.value());

        // System.out.println(StatusEnum.OFF_THE_SHELF.getClass().getTypeName());
        // System.out.println(StatusEnum.OK.getCode());
        final String md5 = Md5Utils.getMd5("123456");
        System.out.println(md5);
    }
    //
    // @Test
    // public void Array(){
    //     int[][] a  = new int[3][1];
    //     a[1][0] =1;
    //     a[1][1] =2;
    //     System.out.println(a[1].length);
    // }
    //

}