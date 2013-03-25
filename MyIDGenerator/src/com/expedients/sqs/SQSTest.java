/**
 * 
 */
package com.expedients.sqs;

import java.util.List;

import com.expedients.dynamodb.DynamoDBUtility;

/**
 * @author Aravind
 *
 */
public class SQSTest {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQSUtility objTest = new SQSUtility();
		
		List<String> lstMessages;
		
				
		//Get the list of requests
		//lstMessages = objTest.getAllMessages(Credentials.getRequestQueueURL());
		
		//DynamoDBUtility objDynamoDBUtility = new DynamoDBUtility();
		
		//objDynamoDBUtility.deleteRequestData();
		//objDynamoDBUtility.insertRequestData();
		//objDynamoDBUtility.getRequestData();
		
	}

}
