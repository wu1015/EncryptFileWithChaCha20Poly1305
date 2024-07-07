package com.wu1015.Utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptAny {
    private static final int NONCE_LEN = 12;                    // 96 bits, 12 bytes
    private static final int MAC_LEN = 16;                    // 128 bits, 16 bytes
    private SecretKey key;
    ChaCha20Poly1305 chaCha20Poly1305;

    public EncryptAny() {
        chaCha20Poly1305 = new ChaCha20Poly1305();
    }

    public byte[] encrypt(byte[] pText) throws Exception {
        byte[] cText = chaCha20Poly1305.encrypt(pText, key);
        ByteBuffer bb = ByteBuffer.wrap(cText);

        // This cText contains chacha20 ciphertext + poly1305 MAC + nonce

        // ChaCha20 encrypted the plaintext into a ciphertext of equal length.
        byte[] originalCText = new byte[pText.length];
        byte[] nonce = new byte[NONCE_LEN];     // 16 bytes , 128 bits
        byte[] mac = new byte[MAC_LEN];         // 12 bytes , 96 bits

        // 加入poly
        bb.get(originalCText);
        bb.get(mac);
        bb.get(nonce);

        return cText;
    }

    public byte[] decrypt(byte[] pText) throws Exception {
        return chaCha20Poly1305.decrypt(pText, key);
    }

    public String setKey() throws NoSuchAlgorithmException {
        key = getKey();
        return getKeyString();
    }
    public void setKey(SecretKey key){
        this.key=key;
    }
    public void setKey(String keyString){
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        this.key= new SecretKeySpec(decodedKey, 0, decodedKey.length, "ChaCha20");
    }
    public String getKeyString(){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    private static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }
}
