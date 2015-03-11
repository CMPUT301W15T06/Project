package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.widget.EditText;

public class AppSingleton {
	private static AppSingleton appsingleton;
	private ClaimList claimList;
	private Claim currentClaim;
	private Item currentItem;
	private EditText dateEditText;
	private Date editDate;
	private String status;
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

	
    private AppSingleton() {  
    	claimList=ClaimListManager.getInstance().load();
    }  
    
    public static AppSingleton getInstance() {  
        if (appsingleton == null) {  
        	appsingleton = new AppSingleton();  
        }  
        return appsingleton;  
    }
    

  	public ClaimList getClaimList(){
  		return claimList;
  	}

	public void setCurrentClaim(Claim claim) {
		currentClaim=claim;
		status=claim.getStatus();
	}

	public Claim getCurrentClaim() {
		// TODO Auto-generated method stub
		return currentClaim;
	}
	
	public void setCurrentItem(Item item) {
		currentItem=item;
	}
	
	public Item getCurrentItem() {
		return currentItem;	
	}


	
	public void setDateEditText(EditText date){
		dateEditText=date;
	}
	
	
	public EditText getDateEditText() {
		// TODO Auto-generated method stub
		return dateEditText;
	}
	
	public void setEditDate(Date date){
		editDate=date;
	}
	
	public Date getEditDate(){
		return editDate;
	}

	
	public String getStatus() {
		return status;

	}
	
	public static DateFormat getDateFormat(){
		return format;
	}
	
//	public static int getYear(String date){
//		return Integer.valueOf(date.split("-")[0]);
//	}
//	
//	public static int getMonth(String date){
//		return Integer.valueOf(date.split("-")[1]);
//	}
//	
//	public static int getDay(String date){
//		return Integer.valueOf(date.split("-")[2]);
//	}
    
}
