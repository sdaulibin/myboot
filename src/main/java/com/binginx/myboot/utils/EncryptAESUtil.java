package com.binginx.myboot.utils;
/**
 * Created by IntelliJ IDEA.
 * User: hjxu
 * Date: 2017-3-30
 * Time: 2:53:34
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class EncryptAESUtil {

    private static final int ZERO = 0;
    private static final int ONE = 1;


    public static byte[] initKey() throws Exception {
        //实例化
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度
        kgen.init(128);
        //生成密钥
        SecretKey skey = kgen.generateKey();
        //返回密钥的二进制编码
        return skey.getEncoded();
    }
   public  void decriptfile1(String id){
         try {
             String str = "";
             //解密
             File file1 = new File(File.separator+"usr"+File.separator+"23.txt");
            decriptfile(file1,File.separator+"usr"+File.separator+"23jiemi", str);

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    public static void main(String[] args) {
        try {
           byte[] key = "".getBytes();
//           String str = Base64.encodeBase64String(key);
           byte[]  enbyte = Base64.encodeBase64(key);
           String str = new String(enbyte);
           System.out.println(str);
            //解密
            File file1 = new File("/Users/binginx/Downloads/01.txt");
            decriptfile(file1, "/Users/binginx/Downloads/02", str);
           // File file1 = new File("G:\\test\\03.txt");
    //         encryptFile(file1, "G:\\test\\jiami\\03", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化 AES Cipher
     *
     * @param sKey
     * @param cipherMode
     * @return Cipher
     */
    public static  Cipher initAESCipher(String sKey, int cipherMode) {
        //创建Key gen
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            //原始方法
           SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
           random.setSeed(sKey.getBytes());
           keyGenerator.init(128, random);
            // keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
               
            byte[] codeFormat = secretKey.getEncoded();
            System.out.println("使用原始方法的密钥："+new String(codeFormat));
            SecretKeySpec key = new SecretKeySpec(codeFormat, "AES");
            
            //使用sun方法
            /*SecureRandom random02 = SecureRandom.getInstance("SHA1PRNG","SUN");
            random02.setSeed(sKey.getBytes());
            keyGenerator.init(128, random02);
            SecretKey secretKey02 = keyGenerator.generateKey();
            byte[] codeFormat02 = secretKey02.getEncoded();
            System.out.println("使用sun方法的密钥："+new String(codeFormat02));*/
   //         SecretKeySpec key02 = new SecretKeySpec(codeFormat02, "AES");
           //使用IBMSecureRandom方法
            /*SecureRandom random03 = SecureRandom.getInstance("SHA1PRNG","IBMSecureRandom");
            random03.setSeed(sKey.getBytes());
            keyGenerator.init(128, random03);
            SecretKey secretKey03 = keyGenerator.generateKey();
            byte[] codeFormat03 = secretKey03.getEncoded();
            System.out.println("使用IBMSecureRandom方法的密钥："+new String(codeFormat03));*/
   //         SecretKeySpec key03 = new SecretKeySpec(codeFormat03, "AES");
    
            //使用tj.jar方法
         /*   Security.addProvider(new sun.security.provider.Sun());
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG",new sun.security.provider.Sun());
            secureRandom.setSeed(sKey.getBytes()); 
            keyGenerator.init(128, secureRandom);
            // keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey04 = keyGenerator.generateKey();
               
            byte[] codeFormat04 = secretKey04.getEncoded();
            SecretKeySpec key04 = new SecretKeySpec(codeFormat04, "AES");
            System.out.println("使用sun.security.provider.Sun()方法的密钥："+key04.toString());
            */
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
        	System.out.println("初始化密钥报错："+e.getMessage());
			e.printStackTrace();
		}
        return cipher;
    }

    /**
     * 对文件进行AES加密
     *
     * @param sourceFile
     * @param fileType
     * @param sKey
     * @return File
     */
    public static  File encryptFile(File sourceFile, String toPath, String sKey) throws Exception {
        //新建临时加密文件
      
        File encrypfile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            encrypfile = new File(toPath + "." + sourceFile.getName().split("\\.")[1]);
            if (encrypfile.exists()) {
                encrypfile.delete();
            }
            encrypfile.createNewFile();
            outputStream = new FileOutputStream(encrypfile);

            Cipher cipher = initAESCipher(sKey, Cipher.ENCRYPT_MODE);

            //以加密流写入文件
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return encrypfile;
    }

    /**
     * AES方式解密文件
     *
     * @param sourceFile
     * @return File
     */
    public static  File decryptFile(File sourceFile, String toPath, String sKey) {
    	System.out.println("密钥----"+sKey);
    	File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            decryptFile = new File(toPath + "." + sourceFile.getName().split("\\.")[1]);
            System.out.println("解密文件路径："+decryptFile);
            System.out.println("一个字长度："+"字".getBytes().length);
            Cipher cipher = initAESCipher(sKey, Cipher.DECRYPT_MODE);
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(decryptFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte [] buffer = new byte [1024];
            int r;
            while ((r = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, r);
            }
            cipherOutputStream.close();
            decryptFile.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        
        return decryptFile;
    }

    /**
     * 文件处理方法
     * code为加密或者解密的判断条件
     * key 加密密钥
     */
    public static  void doFile1(int code, File file, String toPath, String sKey) throws Exception {


        if (0 == code) {
            encryptFile(file, toPath, sKey);
        } else if (1 == code) {
            decryptFile(file, toPath, sKey);
        }
    }


    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public  byte[] encrypt(String content, String password) {
        try {
            Cipher cipher = initAESCipher(password, Cipher.ENCRYPT_MODE);

            byte[] byteContent = content.getBytes("utf-8");
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public  byte[] decrypt(byte[] content, String password) {
        try {
            Cipher cipher = initAESCipher(password, Cipher.ENCRYPT_MODE);
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    //文件加密
    public  void encryptfile(File file, String topath, String key) throws Exception {
        doFile1(ZERO, file, topath, key);
//        doFile(ZERO,file,topath,key);
    }

    //文件解密
    public static  void decriptfile(File file, String topath, String key) throws Exception {
        doFile1(ONE, file, topath, key);
    }
}
