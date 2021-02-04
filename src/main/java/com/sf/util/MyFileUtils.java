package com.sf.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

/**
 * @Author yangwei
 * @create 2021/2/4 11:35
 */
public class MyFileUtils {
    private MyFileUtils() {
    }

    public static void unzip(File file) {
        String filePath = file.getAbsolutePath();
        if (StringUtils.endsWithAny(filePath, "zip")) {
            try {
                Std.print("[开始解压]:" + filePath);
                unzip(file, filePath);
            } catch (Exception e) {
            } finally {
                delete(file);
            }
        }

        if (StringUtils.endsWithAny(filePath, "gz")) {
            try {
                Std.print("[开始解压]:" + filePath);
                ungzip(filePath);
            } finally {
                delete(file);
            }
        }
    }

    private static synchronized void unzip(File file, String filePath) {
        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
        expand.setEncoding("utf-8");

        expand.setSrc(file);

        File destinationFile = new File(filePath.substring(0, filePath.lastIndexOf(".")));
        destinationFile.mkdirs();
        expand.setDest(destinationFile);

        expand.execute();
    }

    private static synchronized void ungzip(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return;
        }
        String outFile = fileName.substring(0, fileName.lastIndexOf("."));
        if (StringUtils.isBlank(outFile)) {
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             GZIPInputStream gzin = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(outFile)) {

            int count;
            byte[] buffer = new byte[1024];
            while ((count = gzin.read(buffer, 0, buffer.length)) != -1) {
                fos.write(buffer, 0, count);
            }
        } catch (Exception e) {
        }
    }

    public static void delete(File file) {
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception e) {
        }
    }
}
