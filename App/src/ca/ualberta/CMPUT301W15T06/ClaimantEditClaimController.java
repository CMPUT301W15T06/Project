package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClaimantEditClaimController {

	private Claim claim=null;
	
	public ClaimantEditClaimController(Claim currentClaim) {
		// TODO Auto-generated constructor stub
		claim=currentClaim;
	}

	public void editClaim(String name, String begin, String end) throws StatusException {
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = AppSingleton.getDateFormat().parse(begin);	
		} catch (ParseException e) {
			beginDate=null;
		}
		try {
			endDate = AppSingleton.getDateFormat().parse(end);
		} catch (ParseException e) {
			endDate=null;
		}

		claim.setName(name);
		claim.setBeginDate(beginDate);
		claim.setEndDate(endDate);
		
		if (name.equals("")||begin.equals("")||end.equals("")){
			claim.setMissValue(true);
		}else{
			claim.setMissValue(false);
		}
		
	}
	
	

}
