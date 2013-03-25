/**
 * 
 */
package com.expedients.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expedients.dynamodb.DynamoDBUtility;
import com.expedients.sqs.SQSUtility;



/**
 * @author Aravind
 *
 */
@SuppressWarnings("serial")
public class SubmitServlet extends HttpServlet {
	public void doPost(HttpServletRequest objReq, HttpServletResponse objResp)
			throws IOException {		
		//Declarations
		StringBuilder sbRequest = new StringBuilder();
		//Get the SQS utility
		SQSUtility sqsUtility = new SQSUtility();
		String strID;
		
		//Build the request
		sbRequest.append("Email:");
		sbRequest.append(objReq.getParameter("user_email"));
		sbRequest.append(",");
		sbRequest.append("Limit:");
		sbRequest.append(objReq.getParameter("prime_limit"));
					
		//Submit the seed message
		strID = sqsUtility.postRequest(sbRequest.toString());
		
		//Insert the data to DB
		DynamoDBUtility.getDynamoDBUtility().insertData("Requests", strID, sbRequest.toString());
		
		//Redirect the user back to the home page
		//objResp.sendRedirect("/MyIDGenerator");
		objResp.sendRedirect("/");
		
		
	}
}
