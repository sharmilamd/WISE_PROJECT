package com.ts;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class AESEncryption {
	static String plainText;
    static byte[] cipherText;
    static SecretKey secKey;
    static String original;
    public AESEncryption(String plainText) {
        this.plainText=plainText;
        System.out.println("Original Text:" + plainText);
    }
    
    public static String enc() throws Exception{
        secKey = getSecretEncryptionKey();
        cipherText = encryptText(plainText, secKey);
        System.out.println("Original Text:" + bytesToHex(cipherText));
        String s = new String(cipherText, StandardCharsets.UTF_8);
        return  s;
         }
    public static String dec() throws Exception{
        secKey = getSecretEncryptionKey();
       // System.out.println("inside decrypt:" + bytesToHex(cipherText));
        original = decryptText(plainText.getBytes(), secKey);
        System.out.println("Original Text:" + original);
        return original ;
         }
        
        // //System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        // System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
        // //System.out.println("Descrypted Text:"+decryptedText);
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, secKey);
            byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
            return new String(bytePlainText);
        }
        
         
    
    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }
    public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
    // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
    private static byte[]  hexToBytes(String hash) {
        return DatatypeConverter.parseHexBinary(hash);
    }

}