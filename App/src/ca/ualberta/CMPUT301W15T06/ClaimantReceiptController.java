/*
UA CMPUT 301 Project Group: CMPUT301W15T06
Copyright {2015} {Jingjiao Ni
              Tianqi Xiao
              Jiafeng Wu
              Xinyi Pan 
              Xinyi Wu
              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.
 */
 
package ca.ualberta.CMPUT301W15T06;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Date;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

/**
 * This <code>ClaimantReceiptController</code> is a controller class
 * of <code>ClaimantReceiptActivity</code> and <code>Receipt/code>. By calling this class, 
 * user(claimant) can add a new receipt photo or delete an existing 
 * receipt photo to an <code>Item</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.io.ByteArrayOutputStream
 * @see java.text.ParseException
 * @see java.util.Date
 * @see android.graphics.Bitmap
 * @see android.util.Base64
 * @see android.util.Log
 */
public class ClaimantReceiptController {

	/**
	 * Set a Receipt object receipt with deault value of null.
	 */
	private Receipt receipt=null;
	
	/**
	 * General constructor.
	 */
	public ClaimantReceiptController(Receipt r){
		receipt=r;
	}
	
	/**
	 * This method will add a photo receipt.
	 * 
	 * @param bm  possible bitmap configurations
	 * @see android.graphics.Bitmap
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void addPhoto(Bitmap bm) throws StatusException, NetWorkException{

		if (!AppSingleton.getInstance().isEditable()){
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

	/**
	 * This method will delete a photo repceipt and set the receipt object as null.
	 * 
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void deletePhoto() throws StatusException, NetWorkException {
		// TODO Auto-generated method stub
		if (!AppSingleton.getInstance().isEditable()){
			throw new StatusException();					
		}
		receipt.setPhotoStr(null);
	}
	
	
}
