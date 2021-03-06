package tech.mhuang.interchan.sso.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
    // 加密算法
    private static final String ALG = "AES";
    // 字符编码
    private static final String ENC = "UTF-8";
    // 密钥正规化算法
    private static final String SEC_NORMALIZE_ALG = "MD5";

    // 加密
    public static String encrypt(String secret, String data) throws Exception {
        MessageDigest dig = MessageDigest.getInstance(SEC_NORMALIZE_ALG);
        byte[] key = dig.digest(secret.getBytes(ENC));
        SecretKeySpec secKey = new SecretKeySpec(key, ALG);

        Cipher aesCipher = Cipher.getInstance(ALG);
        byte[] byteText = data.getBytes(ENC);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(secret.getBytes());
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey, random);
        byte[] byteCipherText = aesCipher.doFinal(byteText);

        return new String(Base64.getEncoder().encode(byteCipherText), ENC);
    }

    // 解密
    public static String decrypt(String secret, String ciphertext) throws Exception {
        MessageDigest dig = MessageDigest.getInstance(SEC_NORMALIZE_ALG);
        byte[] key = dig.digest(secret.getBytes(ENC));
        SecretKeySpec secKey = new SecretKeySpec(key, ALG);
        Cipher aesCipher = Cipher.getInstance(ALG);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(secret.getBytes());
        aesCipher.init(Cipher.DECRYPT_MODE, secKey, random);
        byte[] cipherbytes = Base64.getDecoder().decode(ciphertext.getBytes());
        byte[] bytePlainText = aesCipher.doFinal(cipherbytes);
        return new String(bytePlainText, ENC);
    }

}