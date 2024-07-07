package com.wu1015.DecryptFile;

import com.wu1015.Utils.EncryptAny;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DecryptFile {
    public boolean decryptFile(String fullPath) throws Exception {
        try{
            EncryptAny encryptAny = new EncryptAny();
            String path = getPath(fullPath);
            String fileName = getFileName(fullPath);
            String outPath = path.replace("/.tmp", "");
            String outFullPath = outPath.concat("/" + fileName);


            byte[] originFile = Files.readAllBytes(Paths.get(fullPath));

            Files.createDirectories(Path.of(outPath));

            GetKey getKey = new GetKey();
            encryptAny.setKey(getKey.getKey(DigestUtils.md5Hex(originFile)));

            byte[] pByte = encryptAny.decrypt(originFile);
            Files.write(Paths.get(outFullPath), pByte);
            return true;
        }catch (Exception e){
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
