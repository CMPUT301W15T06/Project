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
  * This <code>ClaimantEditClaimController</code> is a controller class
  * of <code>Claim</code>. By calling this class, user(claimant) can edit 
  * the detail information of a <code>Claim</code>.
  * 
  * @author CMPUT301W15T06
  * @version 03/16/2015
  * @see java.text.DateFormat
  * @see java.text.ParseException
  * @see java.text.SimpleDateFormat
  * @see java.util.Date
  * @see java.util.Locale
  */
package ca.ualberta.CMPUT301W15T06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClaimantEditClaimController {

	/**
	 * Set a Claim object claim with a default value 
	 * as null
	 */
	private Claim claim=null;
	
	
	/**
	 * General construction. This method will create a Claim object 
	 * currentClaim for other classes and methods to use.
	 * 
	 * @param currentClaim  a Claim object
	 */
	public ClaimantEditClaimController(Claim currentClaim) {
		// TODO Auto-generated constructor stub
		claim=currentClaim;
	}

	/**
	 * This class will allows claimants edit the detail information
	 * of the claim, including name, begin date and end date. It will
	 * also checks <code>StatusException()</code> warning to prevent
	 * crush.
	 * 
	 * @param name  a String variable
	 * @param begin  a String variable
	 * @param end  a String variable
	 * @throws StatusException
	 * @see java.text.DateFormat
	 * @see java.text.ParseException
	 * @see java.text.SimpleDateFormat
	 * @see java.util.Date
	 */
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
