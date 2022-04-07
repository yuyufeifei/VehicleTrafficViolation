package com.wxcm.vtvi.util;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;

/**
 * @author GZH
 * @date 2020-12-28
 */
public class Des3 {

    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8" ;

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText, String key) throws Exception {
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );
        IvParameterSpec ips = new IvParameterSpec(key.substring(0,8).getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return getURLEncode(filter(Base64.encode(encryptData)));
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String key) throws Exception {
        if(StringUtils.isBlank(encryptText))
            return null;
        encryptText = getURLDecoderdecode(encryptText);
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );

        IvParameterSpec ips = new IvParameterSpec(key.substring(0,8).getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }

    public static String padding(String str) {
        byte[] oldByteArray;
        try {
            oldByteArray = str.getBytes("UTF8");
            int numberToPad = 8 - oldByteArray.length % 8;
            byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
            System.arraycopy(oldByteArray, 0, newByteArray, 0,
                    oldByteArray.length);
            for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
                newByteArray[i] = 0;
            }
            return new String(newByteArray, "UTF8");
        } catch (UnsupportedEncodingException e) {
//            System.out.println("");
        }
        return null;
    }

    /**
     * Base64编码工具类
     *
     */
    public static class Base64 {
        private static final char [] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_" .toCharArray();

        public static String encode( byte [] data) {
            int start = 0 ;
            int len = data.length;
            StringBuffer buf = new StringBuffer(data.length * 3 / 2 );

            int end = len - 3 ;
            int i = start;
            int n = 0 ;

            while (i <= end) {
                int d = (((( int ) data[i]) & 0x0ff ) << 16 ) | (((( int ) data[i + 1 ]) & 0x0ff ) << 8 ) | ((( int ) data[i + 2 ]) & 0x0ff );

                buf.append(legalChars[(d >> 18 ) & 63 ]);
                buf.append(legalChars[(d >> 12 ) & 63 ]);
                buf.append(legalChars[(d >> 6 ) & 63 ]);
                buf.append(legalChars[d & 63 ]);

                i += 3 ;

                if (n++ >= 14 ) {
                    n = 0 ;
                    buf.append( " " );
                }
            }

            if (i == start + len - 2 ) {
                int d = (((( int ) data[i]) & 0x0ff ) << 16 ) | (((( int ) data[i + 1 ]) & 255 ) << 8 );

                buf.append(legalChars[(d >> 18 ) & 63 ]);
                buf.append(legalChars[(d >> 12 ) & 63 ]);
                buf.append(legalChars[(d >> 6 ) & 63 ]);
                buf.append( "=" );
            } else if (i == start + len - 1 ) {
                int d = ((( int ) data[i]) & 0x0ff ) << 16 ;

                buf.append(legalChars[(d >> 18 ) & 63 ]);
                buf.append(legalChars[(d >> 12 ) & 63 ]);
                buf.append( "==" );
            }

            return buf.toString();
        }

        private static int decode( char c) {
            if (c >= 'A' && c <= 'Z' )
                return (( int ) c) - 65 ;
            else if (c >= 'a' && c <= 'z' )
                return (( int ) c) - 97 + 26 ;
            else if (c >= '0' && c <= '9' )
                return (( int ) c) - 48 + 26 + 26 ;
            else
                switch (c) {
                    case '-' :
                        return 62 ;
                    case '_' :
                        return 63 ;
                    case '=' :
                        return 0 ;
                    default :
                        throw new RuntimeException( "unexpected code: " + c);
                }
        }

        /**
         * Decodes the given Base64 encoded String to a new byte array. The byte array holding the decoded data is returned.
         */

        public static byte [] decode(String s) {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {

                decode(s, bos);
            } catch (IOException e) {
                throw new RuntimeException();
            }

            byte [] decodedBytes = bos.toByteArray();
            try {
                bos.close();
                bos = null ;
            } catch (IOException ex) {
                System.err.println( "Error while decoding BASE64: " + ex.toString());
            }
            return decodedBytes;
        }

        private static void decode(String s, OutputStream os) throws IOException {
            int i = 0 ;

            int len = s.length();

            while ( true ) {
                while (i < len && s.charAt(i) <= ' ' )
                    i++;

                if (i == len)
                    break ;
//                System.out.println(String.valueOf(i));
//                System.out.println(String.valueOf((decode(s.charAt(i + 3 )))));
                int tri = (decode(s.charAt(i)) << 18 ) + (decode(s.charAt(i + 1 )) << 12 ) + (decode(s.charAt(i + 2 )) << 6 ) + (decode(s.charAt(i + 3 )));
//                System.out.println(String.valueOf(i));
                os.write((tri >> 16 ) & 255 );
                if (s.charAt(i + 2 ) == '=' )
                    break ;
                os.write((tri >> 8 ) & 255 );
                if (s.charAt(i + 3 ) == '=' )
                    break ;
                os.write(tri & 255 );

                i += 4 ;
            }
        }
    }


    /**
     **  md5 加密
     */
    public static String md5Capital(String s){
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] strTemp = s.getBytes();
            //使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                ////logger.debug((int)b);
                //将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {return null;}
    }

    private static String filter(String str) {
        String output = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13)
                sb.append(str.subSequence(i, i + 1));
        }
        output = new String(sb);
        return output;
    }

    public static String getURLEncode(String src) {
        String requestValue = "";
        try {

            requestValue = URLEncoder.encode(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requestValue;
    }

    public static String getURLDecoderdecode(String src) {
        String requestValue = "";
        try {
            requestValue = URLDecoder.decode(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requestValue;
    }
}

