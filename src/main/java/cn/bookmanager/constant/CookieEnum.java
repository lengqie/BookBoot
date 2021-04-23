package cn.bookmanager.constant;

/**
 * Cookie And Session Name
 * @author lengqie
 */

public enum CookieEnum {

    /**
     * cookie_admin
     */
    COOKIE_ADMIN("cookie_admin"),
    /**
     * cookie_user
     */
    COOKIE_USER("cookie_user"),
    /**
     * session_admin
     */
    SESSION_ADMIN("session_admin"),
    /**
     * session_user
     */
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
