package ca.ualberta.CMPUT301W15T06;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

public class ClaimantReceiptController {

	private Receipt receipt=null;
	
	public ClaimantReceiptController(Receipt r){
		receipt=r;
	}
	
	public void addPhoto(Bitmap bm) throws StatusException{

		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		String encoded =null;
		int quality=100;
		do{
			//http://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string Author: jeet
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
			bm.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
			byte[] byteArray = byteArrayOutputStream .toByteArray();
			encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
			quality-=10;
		}while(encoded.length()>=65536);
		
		receipt.setPhotoStr(encoded);
		Log.i("size",String.valueOf(encoded.length()));
		Log.i("quality",String.valueOf(quality));
		
	}

	public void deletePhoto() throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		receipt.setPhotoStr(null);
	}
	
	
}
