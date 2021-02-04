package com.sf.listener;

import com.sf.util.Std;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author yangwei
 * @create 2021/2/4 18:36
 */
public class FileMonitor {
    private FileMonitor() {}

    public static void start(String rootDir) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(1), observer);

        try {
            Std.print("开始监控目录: " + rootDir);
            monitor.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    monitor.stop();
                    Std.print("程序正常退出...");
                } catch (Exception e) {
                    Std.print("退出异常...");
                }
            }));
        } catch (Exception e) {
            Std.print(e.getMessage());
        }
    }
}
