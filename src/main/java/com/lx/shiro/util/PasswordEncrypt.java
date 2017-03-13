package com.lx.shiro.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class PasswordEncrypt {
	
	private String ALGORITHM_NAME = "MD5";
	
	private int HASH_ITERATIONS = 100; 
	
	private String privateSalt = "zzzz";
	
	private DefaultHashService hashService;
	
	public PasswordEncrypt() {
		hashService = new DefaultHashService();
	}

	//加密
	public String encryptPassword(String password) {
		hashService.setHashAlgorithmName(ALGORITHM_NAME);
		hashService.setPrivateSalt(new SimpleByteSource(privateSalt));
		hashService.setGeneratePublicSalt(true);
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		hashService.setHashIterations(HASH_ITERATIONS);
		
		HashRequest hashRequest = new HashRequest.Builder().setAlgorithmName(ALGORITHM_NAME)
				.setIterations(HASH_ITERATIONS)
				.setSalt(ByteSource.Util.bytes(privateSalt))
				.setSource(ByteSource.Util.bytes(password)).build();
		password = hashService.computeHash(hashRequest).toHex();
		return password;
	}
	
}
