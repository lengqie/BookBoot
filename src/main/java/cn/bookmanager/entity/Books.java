package cn.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lengqie
 * 书籍信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private String Isbn;
    private String name;
    private String type;
    private int hot;
    private int num;
}
