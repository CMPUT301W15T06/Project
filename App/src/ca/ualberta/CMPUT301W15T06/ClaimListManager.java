package ca.ualberta.CMPUT301W15T06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

public class ClaimListManager {
	private static final String FILENAME="data";
	private static Context context=null;
	
	public static void initial(Context ct){
		if (context==null){
			context=ct;
		}		
	}
	
	public static ClaimList load(){
		Gson gson =new Gson();
		ClaimList cl=null;
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			Type dataType = new TypeToken<ClaimList>(){}.getType();
			InputStreamReader isr =new InputStreamReader(fis);
			cl = gson.fromJson(isr, dataType);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cl==null){
			cl=new ClaimList();		
		}
		return cl;
		
	}
	
	public static void save(){
		Gson gson=new Gson();
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			gson.toJson(ClaimListController.getClaimListForSave(),osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("FileNotFoundException when save");
		}catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("JsonIOException when save");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
