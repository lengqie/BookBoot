package cn.bookmanager.util;

/**常量
 * @author lengqie
 */

@Deprecated
public enum ConstantsUtil {
    /**
     * 已经存在
     */
    EXIST("exist"),
    SESSION_ADMIN("session_admin"),
    COOKIE_ADMIN("cookie_admin"),
    SESSION_USER("session_user"),
    COOKIE_USER("cookie_user"),
    ;

    private final String value;

    ConstantsUtil(String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }
}
