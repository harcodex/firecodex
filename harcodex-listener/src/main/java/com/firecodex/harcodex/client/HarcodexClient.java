package com.firecodex.harcodex.client;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

public class HarcodexClient {
	public static void main(String[] args) throws Exception{
		testFileUpload();
	}
	private static void testEx() throws Exception{
		try{
			int x = 3/0;
		}catch(Exception e){
			System.out.println("in catch");
			throw new Exception("it's custom err");
		}finally{
			System.out.println("in finally");
		}
	}
	private static void testFileUpload() throws Exception{
		HttpClient httpclient = new DefaultHttpClient();
//	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    HttpPost httppost = new HttpPost("http://localhost:8181/harcodex-api/upload/harlogfile");
	    File file = new File("C:/Users/tputhen/AppData/Local/Temp/hars/test-har.har");

	    MultipartEntity mpEntity = new MultipartEntity();
	    mpEntity.addPart("file", new FileBody(file));
	    httppost.setEntity(mpEntity);
	    System.out.println("Executing request: " + httppost.getRequestLine());
	    
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    System.out.println("Status line: "+response.getStatusLine());
	    System.out.println("Status code: "+response.getStatusLine().getStatusCode());
	    if (resEntity != null) {
	      System.out.println(EntityUtils.toString(resEntity));
	    }
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
	}
}
