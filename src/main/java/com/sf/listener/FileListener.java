package com.sf.listener;

import com.sf.util.MyFileUtils;
import com.sf.util.Std;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileListener extends FileAlterationListenerAdaptor {
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    /**
     *  文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        EXECUTOR.submit(() -> MyFileUtils.unzip(file));
    }

    /**
     *  文件创建修改
     */
    @Override
    public void onFileChange(File file) {
//        Std.print("[修改]:" + file.getAbsolutePath());
    }

    /**
     *  文件删除
     */
    @Override
    public void onFileDelete(File file) {
        if (StringUtils.endsWithAny(file.getName(), "zip", "gz")) {
            Std.print("[删除]:" + file.getAbsolutePath());
        }
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
//        Std.print("[新建]:" + directory.getAbsolutePath());
    }

    /**
     *  目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
//        Std.print("[修改]:" + directory.getAbsolutePath());
    }

    /**
     *  目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
//        Std.print("[删除]:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }
}