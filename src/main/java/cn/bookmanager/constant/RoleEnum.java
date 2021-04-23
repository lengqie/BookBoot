package cn.bookmanager.constant;

/**
 * Cookie And Session Name
 * @author lengqie
 */

public enum RoleEnum {

    /**
     * admin
     */
    ADMIN("admin"),
    /**
     * user
     */
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
