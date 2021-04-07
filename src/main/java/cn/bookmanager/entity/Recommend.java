package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 推荐的书籍
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {

    private int id;

    private String name;
    private String isbn;
    private String type;
    private int success;


    private Date createTime;
    private Date modifiedTime;

}
