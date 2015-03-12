package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ClaimantAddClaimController {
	private ClaimList claimList=null;
	
	public ClaimantAddClaimController(ClaimList claimList) {
		this.claimList=claimList;
	}
	
	public void addClaim(String claimName, String begin, String end){
		Claim claim=new Claim(claimName);
		claim.setParent(claimList);
		
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate =AppSingleton.getDateFormat().parse(begin);	
		} catch (ParseException e) {
			beginDate=null;
		}
		try {
			endDate = AppSingleton.getDateFormat().parse(end);
		} catch (ParseException e) {
			endDate=null;
		}
		
		claim.setBeginDate(beginDate);
		claim.setEndDate(endDate);
		
		if (claimName.equals("")||begin.equals("")||end.equals("")){
			claim.setMissValue(true);
		}else{
			claim.setMissValue(false);
		}

		
		claimList.getClaimList().add(claim);
		
		claimList.notifyListeners();
	}

}
