package com.wu1015.Utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MyFileVisitor implements FileVisitor<Path> {
    private final List<String> stringList;
    private int depth;

    public MyFileVisitor() {
        stringList=new ArrayList<>();
        depth=0;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
//        System.out.println(basicFileAttributes.isDirectory());
        if (depth>0){
//            System.out.println(path.getFileName());
            return FileVisitResult.SKIP_SUBTREE;
        }
//        System.out.println(path);
        depth=depth+1;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
//        System.out.println(path);
        stringList.add(String.valueOf(path));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                return null;
//        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
//        return FileVisitResult.TERMINATE;
//        return FileVisitResult.CONTINUE;
//        return null;
        if(depth>1){
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }
    public List<String> getStringList(){
        return stringList;
    }}