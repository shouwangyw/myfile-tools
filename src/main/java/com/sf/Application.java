package com.sf;

import com.sf.listener.FileMonitor;

/**
 * @Author 01399565
 */
public class Application {
    public static void main(String[] args) throws Exception {
        String rootDir = ".";
        if (args != null && args.length > 0) {
            rootDir = args[0];
        }
        FileMonitor.start(rootDir);
    }
}
