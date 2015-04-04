package ca.ualberta.CMPUT301W15T06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

//http://stackoverflow.com/questions/12157130/internet-listener-android-example Author:Chirag Raval
public class NetworkStateReceiver extends BroadcastReceiver {


	public void onReceive(Context context, Intent intent) {
	 

     if(intent.getExtras()!=null) {
        NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
        if(ni!=null && ni.getState()==NetworkInfo.State.CONNECTED) {
            ClaimListManager.getInstance().save(AppSingleton.getInstance().getUserName());
        }
     }
     if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            Log.d("app","There's no network connectivity");
     }
   }
}