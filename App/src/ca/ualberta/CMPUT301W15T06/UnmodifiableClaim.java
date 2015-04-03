package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

import android.util.Log;

public class UnmodifiableClaim extends Claim {

	public UnmodifiableClaim(Claim oldClaim,String status) {
		super(oldClaim,status);
		// TODO Auto-generated constructor stub

		for (Item item:oldClaim.getItemList()){
			itemList.add(new UnmodifiableItem(item));
		}
		
		for (Destination dest:oldClaim.getDestinationList()){
			itemList.add(new UnmodifiableDestination(dest));
		}	
		
	}

	public void setBeginDate(Date beginDate) throws StatusException{
		throw new StatusException();							
	}

	public void setEndDate(Date endDate) throws StatusException{
		throw new StatusException();							
	}


}
