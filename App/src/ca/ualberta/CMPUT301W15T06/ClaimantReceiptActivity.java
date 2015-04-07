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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This <code>ClaimantReceiptActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>Receipt</code> for claimant. This view displays the
 * <code>Receipt</code> and creates an option menu and return true as 
 * the boolean value. It will be used when the claimant asks to access to 
 * the <code>Receipt</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.io.File
 * @see java.io.FileNotFoundException
 * @see java.io.IOException
 * @see android.net.Uri
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.os.Environment
 * @see android.provider.MediaStore
 * @see android.content.Intent
 * @see android.graphics.Bitmap
 * @see android.graphics.BitmapFactory
 * @see android.graphics.drawable.BitmapDrawable
 * @see android.graphics.drawable.Drawable
 * @see android.view.Menu
 * @see android.view.MenuItem
 * @see android.widget.ImageButton
 * @see android.widget.ImageView
 * @see android.widget.TextView
 * @see android.widget.Toast
 */
public class ClaimantReceiptActivity extends Activity {
	
	/**
	 * Set an unchangeable integer variable ADD_PHOTO_RQ to 22.
	 */
	private static final int ADD_PHOTO_RQ = 22;
	/**
	 * Set an Uri object imageFileUri to add and remove a photo by their uri address
	 */
	private Uri imageFileUri;
	/**
	 * Set a ClaimantReceiptController object crc (the controller of this class) to a default value null.
	 */
	private ClaimantReceiptController crc=null;
	/**
	 * Set a Receipt object receipt (an image) with default value null.
	 */
	private Receipt receipt=null;
	/**
	 * a ImageView imageView to displays an arbitrary image, such as an icon. The default value is set to null.
	 */
	private ImageView imageView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_receipt);
		
		
		if(AppSingleton.getInstance().iscMod()){
			setTitle(AppSingleton.getInstance().getCurrentItem().getDescription()+"<-"
					+AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
					+"<-"+AppSingleton.getInstance().getUserName());
		}else{
			setTitle(AppSingleton.getInstance().getCurrentItem().getDescription()+"<-"+
					AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
					+"<-"+AppSingleton.getInstance().getCurrentClaim().getName()+"<-"+"Approver: "+AppSingleton.getInstance().getUserName());
		}
	
		
		receipt=AppSingleton.getInstance().getCurrentItem().getRecipt();
		crc=new ClaimantReceiptController(receipt);
		imageView = (ImageView) findViewById(R.id.photoReciptImageView);
		
		
		if (receipt.getPhotoStr()!=null){
	        byte[] byteArray=Base64.decode(receipt.getPhotoStr(), Base64.DEFAULT);
	        Bitmap bm=BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
	        imageView.setImageBitmap(bm);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if(AppSingleton.getInstance().iscMod()){
			getMenuInflater().inflate(R.menu.claimant_recipt, menu);
			return true;
		}else{
			return false;
		}
	}
	
	//https://eclass.srv.ualberta.ca/mod/resource/view.php?id=1314790 Author:Unknow
	/**
	 * This method allows user to add a receipt photo by their uri address.
	 * 
	 * @param m  Interface for direct access to a previously created menu item
	 */
	public void addPhoto(MenuItem m) {
		if(!AppSingleton.getInstance().isEditable()){
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}else{
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
	        File folderF = new File(folder);
	        if (!folderF.exists()) {
	            folderF.mkdir();
	        }
	        
	        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + ".jpeg";
	        File imageFile = new File(imageFilePath);
	        imageFileUri = Uri.fromFile(imageFile);
	        
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	        startActivityForResult(intent,ADD_PHOTO_RQ);
		}
        
    }
	/**
	 * This method allows user to delete a receipt photo by their uri address.
	 * 
	 * @param m  Interface for direct access to a previously created menu item
	 * @throws StatusException
	 * @throws NetWorkException
	 */
	public void deletePhoto(MenuItem m){
		try {
			crc.deletePhoto();
			imageView.setImageBitmap(null);
		} catch (StatusException e) {
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
		
	}
	
	//https://eclass.srv.ualberta.ca/mod/resource/view.php?id=1314790 Author:Unknow
	/**
	 * This method will check if the photo taken successfully. Check for warnings to prevent crush.
	 * 
	 * @throws StatusException
	 * @throws NetWorkException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PHOTO_RQ) {
            if (resultCode == RESULT_OK) {       
                try {
                	//http://stackoverflow.com/questions/3879992/get-bitmap-from-an-uri-android Author: Mark Ingram
					Bitmap bm= MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageFileUri);
					crc.addPhoto(bm);
					imageView.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));  
				}catch(StatusException e){
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (FileNotFoundException e) {
					throw new RuntimeException(e);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
            } else if (resultCode == RESULT_CANCELED) {
            	Toast.makeText(getApplicationContext(), "Take photo canceled!", Toast.LENGTH_LONG).show();
            } 
        }
    }

}
