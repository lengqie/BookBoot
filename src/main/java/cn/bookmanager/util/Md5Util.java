package cn.bookmanager.util;

import org.springframework.util.DigestUtils;

/**
 *
 * 密码加密工具类
 * @author lengqie
 */
public class Md5Util {

    /**
     *  盐
     */
    private static String salt = "asdAsd12_qS";

    /**
     * 生成md5
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

}