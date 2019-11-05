package com.chinasuse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> getLines(String filename) throws IOException
    {
        List<String> ret=new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename));){

            // 定义字符串,保存读取的一行文字
            String line  = null;
            // 循环读取,读取到最后返回null
            while ((line = br.readLine())!=null) {
               ret.add(line);
            }
        }
        return ret;
    }

    public static void writeLines(String filename,List<String> lines) throws IOException
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for (String line: lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
