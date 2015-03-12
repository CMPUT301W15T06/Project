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
 
 /**
  * This <code>ClaimantAddClaimController</code> is a controller class
  * of <code>ClaimantList</code> and <code>Claim</code>. By calling 
  * this class, user(claimant) can add a <code>Claim</code> with its 
  * detail to the claimList.
  * 
  * @author CMPUT301W15T06
  * @version 03/16/2015
  * @see java.text.DateFormat
  * @see java.text.ParseException
  * @see java.text.SimpleDateFormat
  * @see java.util.ArrayList
  * @see java.util.Date
  * @see java.util.Locale
  */
package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ClaimantAddClaimController {
	/**
	 * Set a ClaimList object claimList with a default value 
	 * as null
	 */
	private ClaimList claimList=null;
	
	
	/**
	 * General contruction. This method is will create a ClaimList object 
	 * claimList for other classes and methods to use.
	 * 
	 * @param claimList  a ClaimList object
	 */
	public ClaimantAddClaimController(ClaimList claimList) {
		this.claimList=claimList;
	}
	
	/**
	 * This method will create a new Claim object claim and set the name,
	 * starting date and ending date to the claim. Then add the claim to 
	 * the claimList. It also gonna notify all Listener in listeners list
	 * about this update.
	 * 
	 * @param claimName  a String variable
	 * @param begin  a String variable
	 * @param end  a String variable
	 * @exception ParseException
	 * @see java.text.DateFormat
	 * @see java.text.ParseException
	 * @see java.text.SimpleDateFormat
	 * @see java.util.ArrayList
	 * @see java.util.Date
	 * @see java.util.Locale
	 */
	public void addClaim(String claimName, String begin, String end){
		Claim claim=new Claim(claimName);
		
		
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

		claim.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				claimList.notifyListeners();
			}
		});
		claimList.getClaimList().add(claim);
		claimList.notifyListeners();
	}

}
