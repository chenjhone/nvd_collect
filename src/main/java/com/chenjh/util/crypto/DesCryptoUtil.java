package com.chenjh.util.crypto;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DesCryptoUtil {
    private final byte[] DESkey = "kingfykj".getBytes("UTF-8");// 设置密钥，略去
    private final byte[] DESIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// 设置向量，略去

    private AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private Key key = null;
    
    private static DesCryptoUtil desUtil = null;

    private DesCryptoUtil() throws Exception {
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }
    
    public static synchronized DesCryptoUtil getInstance()
    {
        if (desUtil == null)
        {
            try
            {
                desUtil = new DesCryptoUtil();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return desUtil;
    }

	public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        DesCryptoUtil desUtil = new DesCryptoUtil();
        String sourceCode = "123456";
        String opType = "2";
        System.out.println("sourceCode:" + sourceCode);
        if(opType.equals("1")) {//decode
        	String encodeCode = desUtil.decode(sourceCode);
        	System.out.println("encodeCode:" + encodeCode);
        }else {//encode
        	String decodeCode = desUtil.encode(sourceCode);
        	System.out.println("decodeCode:" + decodeCode);
        }
    }
}
