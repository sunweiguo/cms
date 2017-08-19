package com.njupt.swg.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class testMD5 {
	@Test
	public void md5() throws NoSuchAlgorithmException{
		String password = "123";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		BigInteger b = new BigInteger(1,md.digest());
		System.out.println(b.toString(16));
	}
}
