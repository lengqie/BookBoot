package cn.bookmanager.constant;

/**
 * 错误码（来自于阿里巴巴代码规范）
 * @author lengqie
 */

public enum StatusEnum {
    
    /*
    Alibaba错误码 00000 一切ok
     */
    OK("00000"),
    OUT_OF_LIMIT("A0425"),
    INSUFFICIENT_BALANCE("A0601"),
    ;
    private final String value;

    StatusEnum(String value){
        this.value = value;
    }

    public String value(){
        return this.value;
    }

}
