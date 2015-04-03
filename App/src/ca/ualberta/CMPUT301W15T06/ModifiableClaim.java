package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

import android.util.Log;

public class ModifiableClaim extends Claim {

	public ModifiableClaim(Claim oldClaim, String status) {
		super(oldClaim, status);
		// TODO Auto-generated constructor stub
		for (Item item:oldClaim.getItemList()){
			itemList.add(new ModifiableItem(item));
		}
		
		for (Destination dest:oldClaim.getDestinationList()){
			itemList.add(new ModifiableDestination(dest));
		}
	}

	public void setBeginDate(Date beginDate) throws 
			WrongEndDateException, NetWorkException {
				if (beginDate.after(endDate)){
					throw new WrongEndDateException();
				}
				this.beginDate=beginDate;
				notifyListeners();
			}

	public void setEndDate(Date endDate) throws WrongEndDateException, NetWorkException {
				if (beginDate.after(endDate)){
					throw new WrongEndDateException();
				}
				this.endDate=endDate;
				notifyListeners();
			}



	

}
