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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


//http://stackoverflow.com/questions/12157130/internet-listener-android-example Author:Chirag Raval
/**
 * This is a subclass of Destination. It will check if the Destination is edible.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see android.content.BroadcastReceiver
 * @see android.content.Context
 * @see android.content.Intent
 * @see android.net.ConnectivityManager
 * @see android.net.NetworkInfo
 * @see android.util.Log
 * @see android.widget.Toast
 */
public class NetworkStateReceiver extends BroadcastReceiver {

	/**
	 * This method will receive data from network.
	 * 
	 * @param context  a Context object
	 * @param intent  a Intent object
	 */
	public void onReceive(Context context, Intent intent) {
	 

     if(intent.getExtras()!=null) {
        NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
        if(ni!=null && ni.getState()==NetworkInfo.State.CONNECTED) {
            ClaimListManager.getInstance().save(AppSingleton.getInstance().getUserName());
        }
     }
     if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
           
     }
   }
}