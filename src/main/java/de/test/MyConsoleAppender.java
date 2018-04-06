package de.test;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.LogLog;

public class MyConsoleAppender extends WriterAppender {

    public static final String SYSTEM_OUT = "System.out";
    public static final String SYSTEM_ERR = "System.err";
    protected String           target;
    private boolean            follow;

    public MyConsoleAppender() {
        target = "System.out";
        follow = false;
    }

    public MyConsoleAppender(Layout layout) {
        this(layout, "System.out");
    }

    public MyConsoleAppender(Layout layout, String target) {
        this.target = "System.out";
        follow = false;
        setLayout(layout);
        setTarget(target);
        activateOptions();
    }

    public void setTarget(String value) {
        String v = value.trim();
        if ("System.out".equalsIgnoreCase(v)) {
            target = "System.out";
        } else if ("System.err".equalsIgnoreCase(v)) {
            target = "System.err";
        } else {
            targetWarn(value);
        }

    }

    public String getTarget() {
        return target;
    }

    public final void setFollow(boolean newValue) {
        follow = newValue;
    }

    public final boolean getFollow() {
        return follow;
    }

    void targetWarn(String val) {
        LogLog.warn("[" + val + "] should be System.out or System.err.");
        LogLog.warn("Using previously set target, System.out by default.");
    }

    @Override
    public void activateOptions() {
        if (follow) {
            if (target.equals("System.err")) {
            } else {
            }
        } else if (target.equals("System.err")) {
            setWriter(createWriter(System.err));
        } else {
            setWriter(createWriter(System.out));
        }

        super.activateOptions();
    }

    @Override
    protected final void closeWriter() {
        if (follow) {
            super.closeWriter();
        }

    }

    private static class SystemOutStream extends OutputStream {

        public SystemOutStream() {
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() {
            System.out.flush();
        }

        @Override
        public void write(byte[] b) throws IOException {
            System.out.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            System.out.write(b, off, len);
        }

        @Override
        public void write(int b) throws IOException {
            System.out.write(b);
        }
    }

    private static class SystemErrStream extends OutputStream {

        public SystemErrStream() {
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() {
            System.err.flush();
        }

        @Override
        public void write(byte[] b) throws IOException {
            System.err.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            System.err.write(b, off, len);
        }

        @Override
        public void write(int b) throws IOException {
            System.err.write(b);
        }
    }
}
