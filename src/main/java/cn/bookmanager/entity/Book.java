package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lengqie
 * 书籍信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String isbn;
    private String name;
    private String type;
    private int hot;
    private int num;

    private int status;
    private Date createTime;
    private Date modifiedTime;
}
