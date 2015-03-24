package ca.ualberta.CMPUT301W15T06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

//https://github.com/joshua2ua/AndroidElasticSearch Author: Joshua Campbell
public class ESClient {
	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();


	/**
	 * Consumes the POST/Insert operation of the service
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void insertClaimList(ClaimList claimList) throws IllegalStateException, IOException{
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w15t06/app/4");
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(claimList));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		httpPost.setHeader("Accept","application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}

		String status = response.getStatusLine().toString();
		Log.i("insert", status);
	}

	/**
	 * Consumes the Get operation of the service
	 * @return 
	 */
	public ClaimList getClaimList(){
		Hit<ClaimList> sr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w15t06/app/4");

		HttpResponse response = null;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			return null;
		}
		
		Type HitType = new TypeToken<Hit<ClaimList>>() {}.getType();

		try {
			sr = gson.fromJson(
					new InputStreamReader(response.getEntity().getContent()),
					HitType);
		} catch (JsonIOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return sr.getSource();
	}

//	
//
//
//	/**
//	 * delete an entry specified by the id
//	 */
//	public void deleteClaimList() throws IOException {
//		HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/testing/lab02/1");
//		httpDelete.addHeader("Accept","application/json");
//
//		HttpResponse response = httpclient.execute(httpDelete);
//
//		String status = response.getStatusLine().toString();
//		System.out.println(status);
//
//		HttpEntity entity = response.getEntity();
//		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
//		String output;
//		System.err.println("Output from Server -> ");
//		while ((output = br.readLine()) != null) {
//			System.err.println(output);
//		}
//		EntityUtils.consume(entity);
//
//		httpDelete.releaseConnection();
//	}


}


