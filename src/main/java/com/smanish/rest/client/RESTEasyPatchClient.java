package com.smanish.rest.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class RESTEasyPatchClient {

	/**
	 * Main method for making Http request to REST service via RESTEasy client. JBOSS resteasy's ClientRequest is used 
	 * for creating request object.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		try {
			ClientRequest request = new ClientRequest(
					"http://localhost:8086/RESTEasyExample/rest/json/applyPatch");
			request.accept("application/json");
			String input = "{\"op\":\"replace\",\"path\":\"title\",\"value\":\"Pink Floyd\"}";
			request.body("application/json", input);
			request.setHttpMethod("PATCH");
			ClientResponse<String> response = request.execute();
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(response.getEntity(String.class).getBytes())));
			String output;
			System.out.println("Response from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
