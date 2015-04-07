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

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class will show the location of the item and claim. 
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class ShowLocationActivity extends Activity {


	private Bitmap bm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_location);
		
		setTitle("Geolocation");
		ImageView image=(ImageView)findViewById(R.id.getLocationImageView);
		TextView tv=(TextView)findViewById(R.id.llTextView);
		

		
		
		Location location=AppSingleton.getInstance().getLocation();
		
	
		bm=BitmapFactory.decodeResource(getResources(), R.drawable.worldmap);



		Bitmap newbm=drawMarker(bm,transXFromLoc(location.getLongitude()), transYFromLoc(location.getLatitude()), 20);
		image.setImageBitmap(newbm);

	
		
		tv.setText("Lat: " + location.getLatitude()
		+ "\nLong: " + location.getLongitude()
		+ "\nThe red square on the map is the location");
		
	}
	
	private int transXFromLoc(double x){
		return (int) ((0.5-(x/360))*bm.getWidth());
	}
	
	private int transYFromLoc(double x){
		double y=Math.log(Math.tan((((x/2)+45)*Math.PI/180)));
		return (int) ((0.5-(y/(Math.PI*2)))*bm.getHeight());
	}
	
	

	
	public Bitmap drawMarker(Bitmap src,int x, int y,int r) {
        if(src == null) {
            return null;
        }
        int minX,maxX,minY,maxY;
        if(x-r<0){
        	minX=0;
        	maxX=x+r;
        }else if(x+r>bm.getWidth()){
        	minX=x-r;
        	maxX=bm.getWidth();
        }else{
        	minX=x-r;
        	maxX=x+r;
        }
        
       
        if(y-r<0){
        	minY=0;
        	maxY=y+r;
        }else if(y+r>bm.getHeight()){
        	minY=y-r;
        	maxY=bm.getHeight();
        }else{
        	minY=y-r;
        	maxY=y+r;
        }
        Bitmap result= src.copy(Bitmap.Config.ARGB_8888 ,true);
        for(int i=minX;i<maxX;i++){
        	for(int j=minY;j<maxY;j++){
        		result.setPixel(i, j, Color.RED);
        	}
        	
        }

        return result;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}

}
