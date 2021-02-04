package com.sf;

import com.sf.util.Std;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author 01399565
 */
public class Application {
    public static void main(String[] args) throws Exception {
        String rootDir = ".";
        if (args != null && args.length > 0) {
            rootDir = args[0];
        }
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(1), observer);

        monitor.start();
        Std.print("开始监控目录: " + rootDir);
    }
}
