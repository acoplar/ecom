package com.ecom.log.util;

import com.ecom.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

public class LogUtil {

    private static final String LOG_METHOD = "\n\tMethod: ";
    private static final String LOG_EXCEPTION = "\n\tException: ";
    private static final String LOG_ADD_INFO = "\n\tUser Information: ";
    private static final String LOG_STACK_TRACE = "\n\tStack Trace: ";
    private static BaseLogService m_baseLogService;
    // Static initializer

    static {
        PropertyConfigurator.configure(Loader.getResource("log4j.properties"));
    }

    @SuppressWarnings("unchecked")
    public static Logger getLogger(Class callingObjType) {
        return Logger.getLogger(callingObjType.getName());
    }

    public static BaseLogService getLogService() {
        if (m_baseLogService == null) {
            m_baseLogService = new BaseLogService();
        }
        return m_baseLogService;
    }

    public static void errorLog(Logger p_logger, Class p_source, String p_method, Exception p_exception, String p_additional) {
        StringBuilder message = new StringBuilder();
        message.append(p_source.getName());
        message.append(LOG_METHOD);
        message.append(p_method);
        message.append(LOG_EXCEPTION);
        message.append(p_exception.getMessage());
        if (!StringUtil.isNullOrEmpty(p_additional)) {
            message.append(LOG_ADD_INFO);
            message.append(p_additional);
        }
        message.append(LOG_STACK_TRACE);
        message.append(getStackTraceString(p_exception.getStackTrace()));
        BaseLogService logService = getLogService();
        logService.log(new ErrorLogObject(p_source.getName(), message.toString(), p_exception));
    }

    public static void infoLog(Logger p_logger, Class p_source, String p_method, String p_additional) {
        StringBuilder message = new StringBuilder();
        message.append(p_source.getName());
        message.append(LOG_METHOD);
        message.append(p_method);

        if (!StringUtil.isNullOrEmpty(p_additional)) {
            message.append(LOG_ADD_INFO);
            message.append(p_additional);
        }

        BaseLogService logService = getLogService();
        logService.log(new BaseLogObject(p_source.getName(), LogService.INFO, message.toString()));
    }

    public static void start(Logger p_logger, Class p_source, String p_method, String p_additional) {
        infoLog(p_logger, p_source, p_method + "[START]", p_additional);
    }

    public static void end(Logger p_logger, Class p_source, String p_method, String p_additional, Long p_startTime) {
        infoLog(p_logger, p_source, p_method + "[END]", p_additional);

        BaseLogService logService = getLogService();
        logService.log(new PerformanceLogObject(p_source.getName() + "." + p_method, p_startTime, System.currentTimeMillis()));
    }

    private static String getStackTraceString(StackTraceElement[] p_Elements) {
        StringBuilder str = new StringBuilder();
        if (p_Elements != null) {
            for (int i = 0; i < p_Elements.length; i++) {
                str.append("\n\t\t");
                str.append(p_Elements[i]);
            }
        }
        return str.toString();
    }
}
