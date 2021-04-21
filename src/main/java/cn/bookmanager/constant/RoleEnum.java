package cn.bookmanager.constant;

/**
 * Cookie And Session Name
 * @author lengqie
 */

public enum RoleEnum {

    /**
     * 用户、管理员的 cookie session
     */
    ADMIN("admin"),
    USER("user")
    ;

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
