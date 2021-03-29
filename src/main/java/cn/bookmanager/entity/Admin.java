package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lengqie
 * 管理员账号密码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private String name;
    private String password;
}
