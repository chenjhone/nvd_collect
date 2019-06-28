
package com.chenjh.util.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public final class AESCryptoUtil {
    /**
     * AES256加解密位长
     */
    public static final int AES_256 = 256;

    /**
     * 默认加解密迭代次数
     **/
    public static final int DEFAULT_ITERATION = 5;

    /**
     * 系统根密钥 256位
     **/
    private static final String ROOTKEY = "MN3DwldsMRaURiJUvYcGzCLrUcrmfiIy";

    /**
     * 系统密码用盐
     **/
    private static final String ROOTSALT = "CulNZj5vEd";

    /**
     * 普通用盐
     **/
    private static final String SALT = "xPv7jI26vH";

    /**
     * 密钥密文
     */
    private static final String DEFAULT_KEY = "N6iBy1FJEKjKwT1By8nXkAP28pG3wfTu72O0U/OFe8o9K7WIUY9nn5eSyd52Oxj59n7dlb+8AftEbWThKMLN5A==";

    /**
     * 私有静态单例
     **/
    private static AESCryptoUtil entity = new AESCryptoUtil();

    /**
     * 构造函数。
     */
    private AESCryptoUtil() {

    }

    /**
     * 获得AESCryptoUtil单例
     *
     * @return 单例对象
     */
    public static AESCryptoUtil getAESCryptoUtil() {
        return entity;
    }

    /**
     * AES加密算法
     * AES加盐算法工具
     *
     * @param content        加密明文
     * @param key            加密密钥
     * @param bitLength      算法位长
     * @param salt           密钥用盐
     * @param iterationCount 加密迭代次数
     * @return 加密密文
     * @throws InvalidCipherTextException 初始化加解密器异常
     */
    public String encryptRijndael(String content, String key, int bitLength, String salt, int iterationCount)
            throws InvalidCipherTextException, UnsupportedEncodingException {
        if (content == null || "".equals(content)) {
            return null;
        }

        PKCS12ParametersGenerator pGen = new PKCS12ParametersGenerator(new SHA3Digest(bitLength));

        final byte[] pkcs12PasswordBytes = PBEParametersGenerator.PKCS12PasswordToBytes(key.toCharArray());

        pGen.init(pkcs12PasswordBytes, salt.getBytes("UTF-8"), iterationCount);


        BlockCipher engine = new RijndaelEngine(bitLength);
        CBCBlockCipher cbc = new CBCBlockCipher(engine);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbc, new PKCS7Padding());

        ParametersWithIV aesCBCParams = (ParametersWithIV) pGen.generateDerivedParameters(bitLength, bitLength);
        cipher.init(true, aesCBCParams);

        byte[] cipherText = new byte[cipher.getOutputSize(content.length())];

        int cipherLength = 0;

        cipherLength = cipher.processBytes(content.getBytes("UTF-8"), 0, content.length(), cipherText, 0);

        cipher.doFinal(cipherText, cipherLength);

        return new String(Base64.encode(cipherText), "UTF-8");
    }

    /**
     * AES解密算法
     * AES加盐解密算法工具
     *
     * @param content        解密密文
     * @param key            解密密钥
     * @param bitLength      算法位长
     * @param salt           密钥用盐
     * @param iterationCount 加密迭代次数
     * @return 解密明文
     * @throws InvalidCipherTextException 初始化加解密器异常
     */
    public String decryptRijndael(String content, String key, int bitLength, String salt, int iterationCount)
            throws InvalidCipherTextException, UnsupportedEncodingException {
        if (content == null || "".equals(content)) {
            return null;
        }

        final byte[] pkcs12PasswordBytes = PBEParametersGenerator.PKCS12PasswordToBytes(key.toCharArray());
        PKCS12ParametersGenerator pGen = new PKCS12ParametersGenerator(new SHA3Digest(bitLength));

        pGen.init(pkcs12PasswordBytes, salt.getBytes("UTF-8"), iterationCount);


        BlockCipher engine = new RijndaelEngine(bitLength);
        CBCBlockCipher cbc = new CBCBlockCipher(engine);
        ParametersWithIV aesCBCParams = (ParametersWithIV) pGen.generateDerivedParameters(bitLength, bitLength);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbc, new PKCS7Padding());
        cipher.init(false, aesCBCParams);

        byte[] output = Base64.decode(content.getBytes("UTF-8"));

        byte[] cipherText = new byte[cipher.getOutputSize(output.length)];

        int cipherLength = cipher.processBytes(output, 0, output.length, cipherText, 0);
        int outputLength = cipher.doFinal(cipherText, cipherLength);
        outputLength += cipherLength;

        byte[] resultBytes = cipherText;
        if (outputLength != output.length) {
            resultBytes = new byte[outputLength];
            System.arraycopy(cipherText, 0, resultBytes, 0, outputLength);
        }

        return new String(resultBytes, "UTF-8");
    }

    /**
     * AES256加密算法
     *
     * @param content        加密明文
     * @param key            加密密钥
     * @param salt           密钥用盐
     * @param iterationCount 加密迭代次数
     * @return 加密密文
     * @throws InvalidCipherTextException 初始化加解密器异常
     */
    public static String aes256Encrypt(String content, String key, String salt, int iterationCount)
            throws InvalidCipherTextException {
        String aes256Encrypt = null;
        try {
            aes256Encrypt=getAESCryptoUtil().encryptRijndael(content, key, AES_256, salt, iterationCount);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aes256Encrypt;
    }

    /**
     * AES256解密算法
     *
     * @param content        解密密文
     * @param key            解密密钥
     * @param salt           密钥用盐
     * @param iterationCount 加密迭代次数
     * @return 解密明文
     * @throws InvalidCipherTextException 初始化加解密器异常
     */
    public static String aes256Decrypt(String content, String key, String salt, int iterationCount)
            throws InvalidCipherTextException {
        String aes256Encrypt = null;
        try {
            aes256Encrypt=getAESCryptoUtil().decryptRijndael(content, key, AES_256, salt, iterationCount);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aes256Encrypt;
    }

    /**
     * AES256加密
     *
     * @param content 待加密内容
     * @return 内容密文
     * @throws InvalidCipherTextException 初始化加密器异常
     */
    public static String contentAes256Encrypt(String content) throws InvalidCipherTextException {
        String key = aes256Decrypt(DEFAULT_KEY, ROOTKEY, ROOTSALT, DEFAULT_ITERATION);

        return aes256Encrypt(content, key, SALT, DEFAULT_ITERATION);
    }

    /**
     * AES256解密
     *
     * @param encodeContent 待解密内容
     * @return 内容明文
     * @throws InvalidCipherTextException 初始化加密器异常
     */
    public static String contentAes256Decrypt(String encodeContent) throws InvalidCipherTextException {
        String key = aes256Decrypt(DEFAULT_KEY, ROOTKEY, ROOTSALT, DEFAULT_ITERATION);
        return aes256Decrypt(encodeContent, key, SALT, DEFAULT_ITERATION);
    }

    public static void main(String[] args){
        String in = "123456";
//        String in = "wBVlSQPXFY810HCJJ9dIJ9fojM4xODDzaa0N/nrkLs0=";
        String out = null;

        try {
            out = AESCryptoUtil.contentAes256Encrypt(in);
//            out = AESCryptoUtil.contentAes256Decrypt(in);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        System.out.println(out);
    }
}
