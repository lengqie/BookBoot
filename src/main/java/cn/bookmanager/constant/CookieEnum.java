package cn.bookmanager.constant;

/**
 * Cookie And Session Name
 * @author lengqie
 */

public enum CookieEnum {

    /**
     * 用户、管理员的 cookie session
     */
    COOKIE_ADMIN("cookie_admin"),
    COOKIE_USER("cookie_user"),
    SESSION_ADMIN("session_admin"),
    SESSION_USER("session_user"),
    ;

    private final String value;

    CookieEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
