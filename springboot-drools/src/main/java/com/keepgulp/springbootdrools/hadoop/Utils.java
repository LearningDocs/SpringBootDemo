package com.keepgulp.springbootdrools.hadoop;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-23 20:49
 **/
@Slf4j
public class Utils {
    public static ArrayList<File> getFileList(String[] list) {
        ArrayList<File> fileList = new ArrayList<File>();
        for (String file : list) {
            File f = new File(file);

            if (f.exists() != true) {
                continue;
            }
            if (f.isDirectory()) {
                ArrayList<String> inFiles = new ArrayList<String>();
                for (String name : f.list()) {
                    inFiles.add(f.getPath() + System.getProperty("file.separator") + name);
                }
                fileList.addAll(Utils.getFileList(inFiles.toArray(new String[0])));
            } else {
                fileList.add(f);
            }
        }
        return fileList;
    }

    public static InputStream getDistributedCacheInputStream(Configuration conf, Path specifiedPath) throws IOException {
        FileSystem fs = LocalFileSystem.getLocal(conf);
        Path pathTarget = specifiedPath;
        if (fs.exists(pathTarget) != true) {
            //Utils.LOG.info("DistributedCache Not Exists");
            File tagetFile = new File(specifiedPath.toString());
            ArrayList<File> fileChildList = Utils.getFileList(new String[] { tagetFile.getParent() });
            for (File child : fileChildList) {
                log.info("DistributedCache CurrentDir childs : " + child.getAbsolutePath());
                if (child.getName().equals(specifiedPath.getName())) {
                    log.info("DistributedCache found.");
                    return fs.open(new Path(child.toURI()));
                }
            }
            throw new IOException("DistributedCache Not Exists (and childs)");
        } else {
            log.info("DistributedCache Exists");
            return fs.open(specifiedPath);
        }
    }
}
