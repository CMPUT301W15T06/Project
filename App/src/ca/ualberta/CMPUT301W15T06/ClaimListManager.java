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
	private static ClaimListManager clm=null;
	private static final String FILENAME="data";
	private Context context=null;
	
	private ClaimListManager(Context context){
		this.context=context;
	}
	
	public static ClaimListManager getInstance() {  
        if (clm == null) {  
    		throw new RuntimeException("Did not initialize ClaimListManager");
        }  
        return clm;  
    }
	
	public static void initial(Context ct){
		if (clm==null){
			clm=new ClaimListManager(ct);	
		}
	}
	
	public ClaimList load(){
		Gson gson =new Gson();
		ClaimList cl=null;
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			Type dataType = new TypeToken<ClaimList>(){}.getType();
			InputStreamReader isr =new InputStreamReader(fis);
			cl = gson.fromJson(isr, dataType);
			fis.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}

		if (cl==null){
			cl=new ClaimList();		
		}
		return cl;
		
	}
	
	public void save(){
		Gson gson=new Gson();
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
			OutputStreamWriter osw =new OutputStreamWriter(fos);
			gson.toJson(AppSingleton.getInstance().getClaimList(),osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException when save");
		}catch (JsonIOException e) {
			throw new RuntimeException("JsonIOException when save");
		}catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
