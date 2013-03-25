/**
 * 
 */
package com.expedients.sqs;

import com.amazonaws.auth.AWSCredentials;

/**
 * @author Aravind
 *
 */
public class Credentials implements AWSCredentials {

	final private  String accessKeyId = "AKIAIMWYE73IGN6UVH2Q"; 
	final private String secretAccessKey = "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";
	final private static String requestQueueURL = "https://sqs.us-east-1.amazonaws.com/558145441370/Request";
	final private static String responseQueueURL = "https://sqs.us-east-1.amazonaws.com/558145441370/Response";
	
	/**
	 * Make the constructor to be private
	 */
	private Credentials(){
		
	}
	
	/**
	 * Returns the credential instance
	 * @return
	 */
	public static Credentials getCredentials(){
		return new Credentials();
	}
	
	/**
	 * Returns the URL at which the request queue is running
	 * @return
	 */
	public static String getRequestQueueURL(){
		return requestQueueURL;
	}
	
	/**
	 * Returns the URL at which the request queue is running
	 * @return
	 */
	public static String getResponseQueueURL(){
		return responseQueueURL;
	}

	public String getAWSAccessKeyId() {
		return accessKeyId;
	}

	public String getAWSSecretKey() {
		return secretAccessKey;
	}

}
