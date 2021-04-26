package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 管理员账号密码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private long id;

    private String name;
    private String password;

    private Date createTime;
    private Date updateTime;
    /**
     * 添加 只有账号密码的 构造 方法
     * @param name
     * @param password
     */
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
