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
	 
     Log.d("app","Network connectivity change");
//     Toast.makeText(AppSingleton.getInstance().getTestContext(), "Network connectivity change", Toast.LENGTH_LONG).show();
     if(intent.getExtras()!=null) {
        NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
        if(ni!=null && ni.getState()==NetworkInfo.State.CONNECTED) {
            Log.i("app","Network "+ni.getTypeName()+" connected");
//            Toast.makeText(AppSingleton.getInstance().getTestContext(), "connected", Toast.LENGTH_LONG).show();
        }
     }
     if(intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            Log.d("app","There's no network connectivity");
//            Toast.makeText(AppSingleton.getInstance().getTestContext(), "no network connectivity", Toast.LENGTH_LONG).show();
     }
   }
}