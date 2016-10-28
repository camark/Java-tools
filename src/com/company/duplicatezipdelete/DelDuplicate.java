package com.company.duplicatezipdelete;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 删除已经解压的ZIp或者RAR文件
 * Created by Ming on 2016/9/27.
 *
 * run on JDK8
 *
 *
 */
public class DelDuplicate {

    private ILog log;
    public void setLog(ILog log){
        this.log=log;
    }

    public void scanAndDel(String arg) {
        Set<String> dirs=new HashSet<String>();
        Map<String,Path> fileNames=new HashMap<String, Path>() ;

        try (Stream<Path> file_and_dir = Files.list(Paths.get(arg))) {
            for(Path w:file_and_dir.collect(Collectors.toList())){
                File f = w.toFile();
                if(f.isDirectory()){
                    dirs.add(f.getName());
                }

                if(f.isFile()){
                    String filename=f.getName();

                    int lastPos=filename.lastIndexOf('.');
                    if(lastPos!=-1){
                        String fName=filename.substring(0,lastPos);
                        //System.out.println(fName+":"+filename);
                        fileNames.put(fName,w);
                    }
                }
            }


            for(String s : dirs){
                //System.out.println(s);
                if(fileNames.containsKey((s))){
                    // System.out.println("Duplate: "+fileNames.get(s));
                    if(log!=null)
                        log.Log("Duplate: "+fileNames.get(s));
                    Files.deleteIfExists(fileNames.get(s));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
