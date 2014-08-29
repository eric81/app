package com.eudemon.taurus.app.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5Util {
	private final static Logger logger = Logger.getLogger(MD5Util.class);
    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // ���MD5ժҪ�㷨�� MessageDigest ����
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // ʹ��ָ�����ֽڸ���ժҪ
            mdInst.update(btInput);
            // �������
            byte[] md = mdInst.digest();
            // ������ת����ʮ����Ƶ��ַ���ʽ
            BigInteger bigInt = new BigInteger(1, md);  
            String output = bigInt.toString(16); 
            return output;
        } catch (Exception e) {
        	logger.warn("MD5 fail, soure : " + s);
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("20121221"));
        System.out.println(MD5Util.MD5("����"));
    }
}