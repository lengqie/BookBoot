package cn.bookmanager.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 状态码
 * @author lengqie
 */

public enum StatusEnum {
    /**
     * Ok
     */
    OK(0,"ok"),
    /**
     * 下架状态（书籍）
     */
    OFF_THE_SHELF(-1,"off the shelf"),

    /**
     * 数量不足（书籍）
     */
    DEFICIENCY(1,"deficiency"),
    /**
     * 冻结（用户）
     */
    FREEZE(-1,"freeze"),

    /**
     * 删除 delete
     */
    DELETE(-2,"delete"),

    ;
    @Setter
    @Getter
    private int code;
    @Setter
    @Getter
    private String desc;

    StatusEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
