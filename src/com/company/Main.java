package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        if (args.length==0)
//        {
//            System.out.println("Special filename");
//            return;
//        }
//
//        JoinFile(args[0]);

        PrjProcess prjProcess=new PrjProcess();
        prjProcess.Processs("c:/qt/t1.csv");
    }

    private static void JoinFile(String arg) {
        System.out.println(arg);
        Optional<String> ret= Optional.of("");
        Path processFile= Paths.get(arg);
        try(Stream<String> lines= Files.lines(processFile)){
            ret = lines.reduce((x,y)->x+","+y);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ret.get());
    }
}
