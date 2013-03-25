/**
 * 
 */
package com.expedients.dynamodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.ScanRequest;
import com.amazonaws.services.dynamodb.model.ScanResult;
import com.expedients.sqs.Credentials;

/**
 * @author Aravind
 *
 */
public class DynamoDBUtility {
	
	private static DynamoDBUtility dynamoDBUtility;
	
	private AmazonDynamoDBClient dynamoDB;
	
	public static DynamoDBUtility getDynamoDBUtility(){
		if(dynamoDBUtility == null)
			dynamoDBUtility = new DynamoDBUtility();
		
		return dynamoDBUtility;
	}
	
	/**
	 * Default constructor
	 */
	private DynamoDBUtility(){
		
		//Credentials
		AWSCredentials credentials;
		try {
			//credentials = new PropertiesCredentials(DynamoDBUtility.class.getResourceAsStream("AwsCredentials.properties"));
			credentials = Credentials.getCredentials();
			dynamoDB = new AmazonDynamoDBClient(credentials);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the list of data
	 * @return
	 */
	public List<String> getData(String strTableName){
		//Declarations
		List<String> lstMessages = new ArrayList<String>();
		
		//Create the scan filter
        ScanRequest scanRequest = new ScanRequest(strTableName);
        ScanResult scanResult = dynamoDB.scan(scanRequest);
        
        for (Map<String, AttributeValue> item : scanResult.getItems()) {
        	lstMessages.add(item.get("Message").getS());
		}

		
		return lstMessages;
	}
	
	/**
	 * Inserts a record for the item 
	 * @param strTableName
	 * @param strID
	 * @param strMessage
	 */
	public void insertData(String strTableName,String strID, String strMessage){				
        //Create the item which has to be inserted
		Map<String, AttributeValue> item = newItem(strID, strMessage);
        
		//Create the insertion request
		PutItemRequest putItemRequest = new PutItemRequest(strTableName, item);
        
		//Insert the item onto DB
		dynamoDB.putItem(putItemRequest);
	}
	
	/**
	 * Deletes the request data
	 */
	public void deleteData(String strTableName, String strRequestID){
		//Declarations
		Key key = new Key(new AttributeValue(strRequestID));
		
		DeleteItemRequest deleteItemRequest = new DeleteItemRequest(strTableName, key);
		
		dynamoDB.deleteItem(deleteItemRequest);
	}

	/**
	 * Adding a new item
	 * @param requestID
	 * @param strMessage
	 * @return
	 */
    private static Map<String, AttributeValue> newItem(String strID, String strMessage) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("ID", new AttributeValue(strID));
        item.put("Message", new AttributeValue(strMessage));

        return item;
    }
	
}
