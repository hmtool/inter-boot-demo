package tech.mhuang.interchan.logger.consts;

/**
 * 日志常量
 *
 * @author mhuang
 * @since 1.0.0
 */
public class LoggerConsts {

    protected static String jwtHeaderName = "global_header";
    protected static boolean jwtHeader = true;
    protected static String systemType = "";

    public static void setJwtHeader(boolean header) {
        jwtHeader = header;
    }

    public static boolean getJwtHeader() {
        return jwtHeader;
    }

    public static void setJwtHeaderName(String headerName) {
        jwtHeaderName = headerName;
    }

    public static String getJwtHeaderName() {
        return jwtHeaderName;
    }

    public static String getSystemType() {
        return systemType;
    }

    public static void setSystemType(String systemType) {
        LoggerConsts.systemType = systemType;
    }
}
