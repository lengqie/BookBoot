package cn.bookmanager.constant;

/**
 * 错误码（来自于阿里巴巴代码规范）
 * @author lengqie
 */

public enum ErrorStatusEnum {

    /**
     * 00000 一切ok
     */
    OK("00000"),

    /**
     * 超出范围
     */
    OUT_OF_LIMIT("A0425"),
    /**
     * 余额不足
     */
    INSUFFICIENT_BALANCE("A0601"),
    /**
     * ALREADY_EXISTS
     */
    ALREADY_EXISTS("A0111"),
    ;

    private final String value;

    ErrorStatusEnum(String value){
        this.value = value;
    }

    public String value(){
        return this.value;
    }

}
