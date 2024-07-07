/*
* wu1015
* https://github.com/wu1015/EncryptFileWithChaCha20Poly1305
* */
import com.wu1015.DecryptFile.DecryptFile;
import com.wu1015.EncryptFile.EncryptFile;
import com.wu1015.Utils.MyFileVisitor;

import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        Scanner input = new Scanner(System.in);
        int flag=1;
        while(flag!=0){

            System.out.println("0. Exit");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");

            flag = input.nextInt();

            if(flag==1){
                System.out.println("Please type you path.");
                encrypt(input.next());
            } else if (flag==2) {
                System.out.println("Please type you path.");
                decrypt(input.next());
            }
        }
        System.out.println("GoodBye!");
    }
    private static void encrypt(String basePath) throws Exception {
        MyFileVisitor myFileVisitor=new MyFileVisitor();

        Files.walkFileTree(Paths.get(basePath),myFileVisitor);
        System.out.println(myFileVisitor.getStringList());
        int succ=0;int err=0;
        for(String test:myFileVisitor.getStringList()){
            EncryptFile encryptFile=new EncryptFile();
            if (encryptFile.encryptFile(test)){
                succ++;
            }else {
                err++;
            }
        }
        System.out.println("Success:"+succ+" Error:"+err);
    }
    private static void decrypt(String basePath) throws Exception {
        basePath=basePath.concat("/.tmp");
        MyFileVisitor myFileVisitor=new MyFileVisitor();

        Files.walkFileTree(Paths.get(basePath),myFileVisitor);
        System.out.println(myFileVisitor.getStringList());
        int succ=0;int err=0;
        for(String test:myFileVisitor.getStringList()){
            DecryptFile decryptFile=new DecryptFile();
            if (decryptFile.decryptFile(test)){
                succ++;
            }else {
                err++;
            }
        }
        System.out.println("Success:"+succ+" Error:"+err);
    }
}