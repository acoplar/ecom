package com.ecom.log.util;

public class AccessLogObject extends BaseLogObject {

    private String ip;
    private Object user;

    public AccessLogObject(String className, int logLevel, String ip, Object user, String message) {
        super(className, logLevel, message);
        this.ip = "XXX.XXX.XXX.XXX";
        this.ip = ip;
        if (user == null) {
            user = new Object();
        }
        this.user = user;
    }

    @Override
    public String getLogMessage() {
        return (new StringBuilder("IP[")).append(ip).append("] USER[").append(user.toString()).append("] MESSAGE[").append(message).append("]").toString();
    }
}
