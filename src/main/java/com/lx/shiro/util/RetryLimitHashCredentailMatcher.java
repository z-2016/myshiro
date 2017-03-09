package com.lx.shiro.util;

import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashCredentailMatcher extends HashedCredentialsMatcher{

	private Ehcache passwordRetryCache;

	public RetryLimitHashCredentailMatcher() {
		 URL resource = CacheManager.class.getClassLoader().getResource("ehcache.xml");
		 CacheManager cacheManager = CacheManager.create(resource);
		 passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		
		String username =(String) token.getPrincipal();
		Element element = passwordRetryCache.get(username);
		if(element==null){
			element = new Element(username,new AtomicInteger(0));
			passwordRetryCache.put(element);
		}
		AtomicInteger retryCount =(AtomicInteger) element.getObjectValue();
		if(retryCount.incrementAndGet()>5){
			throw new ExcessiveAttemptsException("输入超过5次，请一会在试");
		}
		
		boolean matches = super.doCredentialsMatch(token, info);
		if(matches){
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}
