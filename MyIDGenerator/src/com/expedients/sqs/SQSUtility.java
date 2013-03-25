/**
 * 
 */
package com.expedients.sqs;


import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.model.*;



import com.amazonaws.services.sqs.*;



/**
 * @author Aravind
 *
 */
public class SQSUtility {

	private static AmazonSQS amazonSQS;
	
	/**
	 * Default Constructor
	 */
	public SQSUtility(){
		//Get the Credentials
		AWSCredentials objCredentials = Credentials.getCredentials();						
		
		//Create AmazonSQS client			
		amazonSQS = new AmazonSQSClient(objCredentials);
	}

	
	/**
	 * Posts the request message to Amazon SQS
	 * @param strMessage
	 */
	public String postRequest(String strRequest){
		//Declaratoins
		String strID = "";
		
		try {
			//Get the Queue URL
			String strQueueURL = Credentials.getRequestQueueURL();
									
			//Create the message request
			SendMessageRequest objMessageRequest = new SendMessageRequest(strQueueURL, strRequest);
			
			//Send the message
			SendMessageResult sendMessageResult = amazonSQS.sendMessage(objMessageRequest);
			
			//Get the message id of the added item in the queue
			strID = sendMessageResult.getMessageId();
		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmazonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return strID;
		
	}
	
	/**
	 * Retrieves the message from Amazon SQS
	 */
	public String getResponse(){
		//Declarations
		List<Message> lstMessages;
		String strResponse = "";
		DeleteMessageRequest objDeleteMessageRequest;
		
		try {
			//Get the Queue URL
			String strQueueURL = Credentials.getResponseQueueURL();
			
			//Get the receive request 
			ReceiveMessageRequest objReceiveMessageRequest = new ReceiveMessageRequest(strQueueURL);
			
			//Receive the message 
			ReceiveMessageResult objReceiveMessageResult = amazonSQS.receiveMessage(objReceiveMessageRequest);
			
			//Get the messages
			lstMessages = objReceiveMessageResult.getMessages();
			
					
			
			for (Message message : lstMessages) {
				//Get the response message
				strResponse = message.getBody();
				
				//Delete the message request
				objDeleteMessageRequest = new DeleteMessageRequest(strQueueURL, message.getReceiptHandle());			
				amazonSQS.deleteMessage(objDeleteMessageRequest);
			}
		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmazonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return strResponse;
	}

	
    /**
     * Gets all the messages present in the given queue
     * @param strQueueURL
     * @return
     */
	public List<String> getAllMessages(String strQueueURL){
		List<String> lstMessages = new ArrayList<String>();
		List<Message> lstResult;
		String strResponse = "";
		
		//Get the receive request 
		ReceiveMessageRequest objReceiveMessageRequest = new ReceiveMessageRequest(strQueueURL);
				
			
		do {
			objReceiveMessageRequest.setWaitTimeSeconds(0);
			objReceiveMessageRequest.setVisibilityTimeout(0);
						
			//Receive the message 
			ReceiveMessageResult objReceiveMessageResult = amazonSQS.receiveMessage(objReceiveMessageRequest);
			
			//Get the messages
			lstResult = objReceiveMessageResult.getMessages();
			
			for (Message message : lstResult) {
				//Get the response message
				strResponse = message.getBody();				
			}			
			
						
			//If its not already added
			if(!lstMessages.contains(strResponse))
				lstMessages.add(strResponse);
			
		} while (lstResult.size() > 0 && lstMessages.contains(strResponse));
		

						
		return lstMessages;
	}

}
