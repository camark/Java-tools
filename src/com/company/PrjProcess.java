package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/10/27.
 */
public class PrjProcess {
    public void Processs(String filename){
        try(Stream<String> lines= Files.lines(Paths.get(filename))) {

            HashMap<String,String> projs=new HashMap<>();
            HashMap<String,List<String>> proj_dirs=new HashMap<>();
            lines.forEach(s->{
                String[] words=s.split((","));

                String pno=words[0];
                String pname=words[1];
                String pstatus=words[1];

                String pno1=pno.substring(0,8);
                String pno2=pno.substring(8,pno.length());
                if(!projs.containsKey(pno1)){
                    projs.put(pname,pno1);
                }

                if(!proj_dirs.containsKey(pname)){
                    proj_dirs.put(pname,new ArrayList<String>());
                }

                proj_dirs.get(pname).add(pno2);
            });

            for(String sKey : proj_dirs.keySet()){
                List<String> nos=proj_dirs.get(sKey);
                // System.out.println(sKey+":");
                String projj="";
                if (nos.size()==1){
                    projj=nos.get(0);
                }
                else {
                    projj=nos.get(0)+"-"+nos.get(nos.size()-1);
                }

                String qianzhui=projs.get(sKey);
                System.out.println(qianzhui+projj+","+sKey+","+"进行中");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
