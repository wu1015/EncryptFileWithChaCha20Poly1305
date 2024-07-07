package com.wu1015.EncryptFile;

import com.wu1015.Utils.EncryptAny;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptFile {
    public boolean encryptFile(String fullPath) {
        try{
            EncryptAny encryptAny = new EncryptAny();
            String path = getPath(fullPath);
            String fileName = getFileName(fullPath);
            String outPath = path.concat("/.tmp");
            String outFullPath = outPath.concat("/" + fileName);

            byte[] originFile = Files.readAllBytes(Paths.get(fullPath));

            Files.createDirectories(Path.of(outPath));

            String keyStr = encryptAny.setKey();
            AddKey addKey = new AddKey();

            byte[] cByte = encryptAny.encrypt(originFile);
            if (addKey.addKey(DigestUtils.md5Hex(originFile), DigestUtils.md5Hex(cByte), keyStr)) {
                Files.write(Paths.get(outFullPath), cByte);
            } else {
                encryptFile(fullPath);
            }
            return true;
        }catch (Exception e){
            System.out.println("ss");
            return false;
        }
    }
    private static String getPath(String fullPath) {
        int lastIndex = fullPath.lastIndexOf('/');
        if (lastIndex == -1) {
            return "";
        }
        return fullPath.substring(0, lastIndex);
    }

    private static String getFileName(String fullPath) {
        int lastIndex = fullPath.lastIndexOf('/');
        if (lastIndex == -1) {
            return fullPath;
        }
        return fullPath.substring(lastIndex + 1);
    }
}
