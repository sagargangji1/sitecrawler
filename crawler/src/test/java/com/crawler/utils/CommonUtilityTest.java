package com.crawler.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class CommonUtilityTest {
	
	 @Test
	 public final void testInvalid() throws Exception {
	        checkDomain(".....", null);
	        checkDomain("redhat", null);
	        checkDomain("redhat.com", null);
	    }
	 
	 @Test
	 public final void testValidInvalid() throws Exception {
	        checkDomain("http://redhat.com", "redhat.com");
	        checkDomain("http://google.com", "google.com");
	        checkDomain("http://www.redhat.com", "redhat.com");
	        checkDomain("http://www.google.com", "google.com");
	    }
	 
	 private void checkDomain(String url, String expectedDomain) {
	        if (url == null) {
	            return;
	        }
	        String domain = CommonUtility.getDomain(url);
	        assertEquals(expectedDomain, domain);
	    }
}
