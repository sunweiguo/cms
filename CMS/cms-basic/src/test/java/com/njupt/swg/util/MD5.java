package com.njupt.swg.util;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.njupt.swg.utils.SecurityUtil;

public class MD5 {
	@Test
	public void test(){
		String name = "admin";
		String password = "123";
		try {
			String p = SecurityUtil.md5(name, password);
			System.out.println(p);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
}
