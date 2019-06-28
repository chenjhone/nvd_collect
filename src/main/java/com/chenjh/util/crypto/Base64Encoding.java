package com.chenjh.util.crypto;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Base64Encoding
{
    private static final int NUMBER_0X3C = 0x3c;
    private static final int NUMBER_0X30 = 0x30;
    private static final int NUMBER_0X3F = 0x3f;
    private static final int NUMBER_0XC0 = 0xc0;
    private static final int NUMBER_0X0F = 0x0f;
    private static final int NUMBER_0XF0 = 0xf0;
    private static final int NUMBER_0X03 = 0x03;
    private static final int NUMBER_0X3 = 0x3;
    private static final int NUMBER_0XFF = 0xff;
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_3 = 3;
    private static final int NUMBER_4 = 4;
    private static final int NUMBER_5 = 5;
    private static final int NUMBER_6 = 6;
    private static final int NUMBER_7 = 7;
    private static final int NUMBER_8 = 8;
    private static final int NUMBER_9 = 9;
    private static final int NUMBER_10 = 10;
    private static final int NUMBER_11 = 11;
    private static final int NUMBER_12 = 12;
    private static final int NUMBER_13 = 13;
    private static final int NUMBER_14 = 14;
    private static final int NUMBER_15 = 15;
    private static final int NUMBER_16 = 16;
    private static final int NUMBER_17 = 17;
    private static final int NUMBER_18 = 18;
    private static final int NUMBER_19 = 19;
    private static final int NUMBER_20 = 20;
    private static final int NUMBER_21 = 21;
    private static final int NUMBER_22 = 22;
    private static final int NUMBER_23 = 23;
    private static final int NUMBER_24 = 24;
    private static final int NUMBER_25 = 25;
    private static final int NUMBER_26 = 26;
    private static final int NUMBER_27 = 27;
    private static final int NUMBER_28 = 28;
    private static final int NUMBER_29 = 29;
    private static final int NUMBER_30 = 30;
    private static final int NUMBER_31 = 31;
    private static final int NUMBER_32 = 32;
    private static final int NUMBER_33 = 33;
    private static final int NUMBER_34 = 34;
    private static final int NUMBER_35 = 35;
    private static final int NUMBER_36 = 36;
    private static final int NUMBER_37 = 37;
    private static final int NUMBER_38 = 38;
    private static final int NUMBER_39 = 39;
    private static final int NUMBER_40 = 40;
    private static final int NUMBER_41 = 41;
    private static final int NUMBER_42 = 42;
    private static final int NUMBER_43 = 43;
    private static final int NUMBER_44 = 44;
    private static final int NUMBER_45 = 45;
    private static final int NUMBER_46 = 46;
    private static final int NUMBER_47 = 47;
    private static final int NUMBER_48 = 48;
    private static final int NUMBER_49 = 49;
    private static final int NUMBER_50 = 50;
    private static final int NUMBER_51 = 51;
    private static final int NUMBER_52 = 52;
    private static final int NUMBER_53 = 53;
    private static final int NUMBER_54 = 54;
    private static final int NUMBER_55 = 55;
    private static final int NUMBER_56 = 56;
    private static final int NUMBER_57 = 57;
    private static final int NUMBER_58 = 58;
    private static final int NUMBER_59 = 59;
    private static final int NUMBER_60 = 60;
    private static final int NUMBER_61 = 61;
    private static final int NUMBER_62 = 62;
    private static final int NUMBER_63 = 63;
    private final static Logger LOG = LoggerFactory.getLogger(Base64Encoding.class);
    
    private static char[] base64EncodeChars = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                                                          'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
                                                          'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                                                          'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
                                                          'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                                          'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
                                                          'w', 'x', 'y', 'z', '0', '1', '2', '3', 
                                                          '4', '5', '6', '7', '8', '9', '+', '/'};
    
    private static byte[] base64DecodeChars = new byte[] {-1, -1, -1, -1, -1, -1, -1, -1, 
                                                          -1, -1, -1, -1, -1, -1, -1, -1, 
                                                          -1, -1, -1, -1, -1, -1, -1, -1, 
                                                          -1, -1, -1, -1, -1, -1, -1, -1, 
                                                          -1, -1, -1, -1, -1, -1, -1, -1, 
                                                          -1, -1, -1, NUMBER_62, -1, -1, -1, NUMBER_63, 
                                                          NUMBER_52, NUMBER_53, NUMBER_54, NUMBER_55, 
                                                          NUMBER_56, NUMBER_57, NUMBER_58, NUMBER_59, 
                                                          NUMBER_60, NUMBER_61, -1, -1, -1, -1, -1, -1, 
                                                          -1, 0, NUMBER_1, NUMBER_2, NUMBER_3, NUMBER_4, 
                                                          NUMBER_5, NUMBER_6, NUMBER_7, NUMBER_8,  
                                                          NUMBER_9, NUMBER_10, NUMBER_11, NUMBER_12, 
                                                          NUMBER_13, NUMBER_14, NUMBER_15, NUMBER_16,  
                                                          NUMBER_17, NUMBER_18, NUMBER_19, NUMBER_20,
                                                          NUMBER_21, NUMBER_22, NUMBER_23, NUMBER_24, 
                                                          NUMBER_25, -1, -1, -1, -1, -1, -1, NUMBER_26,  
                                                          NUMBER_27, NUMBER_28, NUMBER_29, NUMBER_30, 
                                                          NUMBER_31, NUMBER_32, NUMBER_33, NUMBER_34, 
                                                          NUMBER_35, NUMBER_36, NUMBER_37, NUMBER_38, 
                                                          NUMBER_39, NUMBER_40, NUMBER_41, NUMBER_42, 
                                                          NUMBER_43, NUMBER_44, NUMBER_45, NUMBER_46,
                                                          NUMBER_47, NUMBER_48, NUMBER_49, NUMBER_50, 
                                                          NUMBER_51, -1, -1, -1, -1, -1};
    
    private Base64Encoding()
    {
    }
    
    /**
     * 该方法对传入的一个二进制数组进行64位加
     * @param data 二进制数集合
     * @return String 加密后的字符
     */
    public static String encode(byte[] data)
    {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len)
        {
            b1 = data[i++] & NUMBER_0XFF;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> NUMBER_2]);
                sb.append(base64EncodeChars[(b1 & NUMBER_0X3) << NUMBER_4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & NUMBER_0XFF;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> NUMBER_2]);
                sb.append(base64EncodeChars[((b1 & NUMBER_0X03) << NUMBER_4) | ((b2 & NUMBER_0XF0) >>> NUMBER_4)]);
                sb.append(base64EncodeChars[(b2 & NUMBER_0X0F) << NUMBER_2]);
                sb.append('=');
                break;
            }
            b3 = data[i++] & NUMBER_0XFF;
            sb.append(base64EncodeChars[b1 >>> NUMBER_2]);
            sb.append(base64EncodeChars[((b1 & NUMBER_0X03) << NUMBER_4) | ((b2 & NUMBER_0XF0) >>> NUMBER_4)]);
            sb.append(base64EncodeChars[((b2 & NUMBER_0X0F) << NUMBER_2) | ((b3 & NUMBER_0XC0) >>> NUMBER_6)]);
            sb.append(base64EncodeChars[b3 & NUMBER_0X3F]);
        }
        return sb.toString();
    }
    
    /**
     * 该方法对传入的一个字符串进行解密
     * @param str 已经解密的二进制数组
     * @return String 加密后的字符
     */
    public static byte[] decode(String str)
    {
        ByteArrayOutputStream buf = null;
        try
        {
            byte[] data = str.getBytes("UTF-8");
            int len = data.length;
            buf = new ByteArrayOutputStream(len);
            int i = 0;
            int b1, b2, b3, b4;
            while (i < len)
            {
                
                /* b1 */
                do
                {
                    b1 = base64DecodeChars[data[i++]];
                } while (i < len && b1 == -1);
                if (b1 == -1)
                {
                    break;
                }
                
                /* b2 */
                do
                {
                    b2 = base64DecodeChars[data[i++]];
                } while (i < len && b2 == -1);
                if (b2 == -1)
                {
                    break;
                }
                buf.write((int)((b1 << NUMBER_2) | ((b2 & NUMBER_0X30) >>> NUMBER_4)));
                
                /* b3 */
                do
                {
                    b3 = data[i++];
                    if (b3 == NUMBER_61)
                    {
                        return buf.toByteArray();
                    }
                    b3 = base64DecodeChars[b3];
                } while (i < len && b3 == -1);
                if (b3 == -1)
                {
                    break;
                }
                buf.write((int)(((b2 & NUMBER_0X0F) << NUMBER_4) | ((b3 & NUMBER_0X3C) >>> NUMBER_2)));
                
                /* b4 */
                do
                {
                    b4 = data[i++];
                    if (b4 == NUMBER_61)
                    {
                        return buf.toByteArray();
                    }
                    b4 = base64DecodeChars[b4];
                } while (i < len && b4 == -1);
                if (b4 == -1)
                {
                    break;
                }
                buf.write((int)(((b3 & NUMBER_0X03) << NUMBER_6) | b4));
            }
        }
        catch (UnsupportedEncodingException e)
        {
            LOG.error("decode error.", e);
        }
        return buf == null ? new byte[1] : buf.toByteArray();
    }
    
    public static void main(String[] args) {
    	String source = "123456";
    	String encodeStr = Base64Encoding.encode(source.getBytes());
    	System.out.println(encodeStr);
    	String deCodeStr = new String(Base64Encoding.decode(encodeStr));
    	System.out.println(deCodeStr);
	}
}
