package com.example.demo.elk;

import net.logstash.logback.appender.LogstashTcpSocketAppender;

public class SafeLogstashAppender extends LogstashTcpSocketAppender {

    @Override
    public void start() {
        try {
            super.start();
        } catch (Exception e) {
            System.err.println("LOGSTASH is not found. Logging will continue without it.");
        }
    }
}