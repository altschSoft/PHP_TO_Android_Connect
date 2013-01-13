package com.example.phpconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
public class testJAVAToPHPConnect {
	
	
	public String doConnection(Context appContext) 
	{
	    HttpResponse response = this.CallWebSite();
	    InputStream is = this.GetInputStream(response);
	    String result = this.GetResultAsString(is);
	    int value = this.ParseValueFromJSONArray(result);
		
		return "" + value;
	}
	
	private HttpResponse CallWebSite()
	{
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = null;
		    
			try 
			{
				HttpGet httpRequest = new HttpGet(new URI("http://192.168.0.14:8050/xampp/test/test.php"));
				response = httpclient.execute(httpRequest);
			} 
			catch (URISyntaxException e1) 
			{
				e1.printStackTrace();
			} 
			catch (IllegalArgumentException iAex) 
			{
				iAex.printStackTrace();
			} 
			catch (ClientProtocolException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace(); 
			} 
			
			return response;
	}
	
	private InputStream GetInputStream(HttpResponse response) 
	{
		InputStream is = null;
		try 
		{
			is = response.getEntity().getContent();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return is;
	}
	
	private String GetResultAsString(InputStream is)
	{
		String result = "";
		
		try
	    {
		
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) 
	        {
	            sb.append(line + "\n");
	        }
	        is.close();
	        result=sb.toString();
		}
	    catch(Exception exc){
			exc.printStackTrace();
		}
		
		return result;
	}
	
	private int ParseValueFromJSONArray(String result)
	{
		 int value = -1;
		 JSONArray jArray = null;
		    try
		    {
		        jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++)
		        {
		                JSONObject json_data = jArray.getJSONObject(i);
		                value = json_data.getInt("Wert");
		        }
		    }
			catch(Exception e)
			{
				e.printStackTrace();
			}
		    
		  return value;
	}

	

}
