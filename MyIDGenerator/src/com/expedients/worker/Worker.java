/**
 * 
 */
package com.expedients.worker;

import java.util.ArrayList;
import java.util.List;


import com.expedients.dynamodb.DynamoDBUtility;

/**
 * @author Aravind
 *
 */
public class Worker {

	
	
	public String getResultsData(){
		//Declarations
		StringBuilder sbResultsData = new StringBuilder();		
		List<String> lstMessages = new ArrayList<String>();		
							
		//Get the list of requests
		lstMessages = DynamoDBUtility.getDynamoDBUtility().getData("Requests");	
		//Get the requests results data
		sbResultsData.append(constructRequestData(lstMessages));
		
		//Get the list of responses		
		lstMessages = new ArrayList<String>();
		lstMessages = DynamoDBUtility.getDynamoDBUtility().getData("Responses");		
		//Get the response results data
		sbResultsData.append(constructResponseData(lstMessages));
		
		return sbResultsData.toString();
	}
	
	private String constructRequestData(List<String> lstMessages){
		//Declarations
		StringBuilder sbResultsData = new StringBuilder();	
		String[] arrMessage;
		String[] arrParameter;
		
		for (String strMessage : lstMessages) {
			arrMessage = strMessage.split(",");
			
			sbResultsData.append("<tr>");
			
			for (String strItem : arrMessage) {
				arrParameter = strItem.split(":");
				
				sbResultsData.append("<td>");
				sbResultsData.append(arrParameter[1]);
				sbResultsData.append("</td>");
			}
			
			sbResultsData.append("<td class='loading'>");
			sbResultsData.append("&nbsp;");
			sbResultsData.append("</td>");
			sbResultsData.append("<td class='loading'>");
			sbResultsData.append("&nbsp;");
			sbResultsData.append("</td>");
			
			
			sbResultsData.append("</tr>");
			
		}
		
		
		return sbResultsData.toString();
	}
	
	private String constructResponseData(List<String> lstMessages){
		//Declarations
		StringBuilder sbResultsData = new StringBuilder();	
		String[] arrMessage;
		String[] arrParameter;
		
		for (String strMessage : lstMessages) {
			arrMessage = strMessage.split(",");
			
			sbResultsData.append("<tr>");
			
			for (String strItem : arrMessage) {
				arrParameter = strItem.split(":");
				
				sbResultsData.append("<td>");
				sbResultsData.append(arrParameter[1]);
				sbResultsData.append("</td>");
			}
									
			sbResultsData.append("</tr>");			
		}
		
		
		return sbResultsData.toString();
	}
	
	
	
	
	
}
