package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;
    private int max;
    private int count;
    private int total;
    private int balance;


    private Date createTime;
    private Date modifiedTime;

    /**
     * 添加 只有账号密码的 构造 方法
     * @param name
     * @param password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
