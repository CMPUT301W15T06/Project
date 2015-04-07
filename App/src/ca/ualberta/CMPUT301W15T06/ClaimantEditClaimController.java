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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

/**
 * This <code>ClaimantEditClaimController</code> is a controller class
 * of <code>Claim</code>. By calling this class, user(claimant) can edit 
 * the detail information of a <code>Claim</code>.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.text.DateFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * @see java.util.Locale
 * @see android.util.Log
 */
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
	 * @param currentClaim  which represented the currently claim that the user is working on
	 */
	public ClaimantEditClaimController(Claim currentClaim) {
		// TODO Auto-generated constructor stub
		claim=currentClaim;
	}

	/**
	 * This class will allows claimants edit the end date. It will
	 * also checks warnings to prevent crush.
	 * 
	 * @param end  the end date of the travel (like "31-Mar-2015")
	 * @throws StatusException
	 * @throws WrongEndDateException 
	 * @throws NetWorkException 
	 * @see java.text.DateFormat
	 * @see java.text.ParseException
	 * @see java.text.SimpleDateFormat
	 * @see java.util.Date
	 */
	public void editEnd(String end) throws StatusException, WrongEndDateException, NetWorkException {
		
		
		Date endDate = null;

		try {
			endDate = AppSingleton.getDateFormat().parse(end);
		} catch (ParseException e) {
			endDate=null;
		}

		
		claim.setEndDate(endDate);
		
	}

	/**
	 * This class will allows claimants edit the begin date. It will
	 * also checks warnings to prevent crush.
	 * 
	 * @param begin  the begin date of the travel (like "15-Mar-2015")
	 * @throws StatusException
	 * @throws WrongEndDateException
	 * @throws NetWorkException
	 */
	public void editBegin(String begin) throws StatusException, WrongEndDateException, NetWorkException {
		// TODO Auto-generated method stub
		Date beginDate = null;
		try {
			beginDate = AppSingleton.getDateFormat().parse(begin);	
		} catch (ParseException e) {
			beginDate=null;
		}
		claim.setBeginDate(beginDate);
	}
	
	

}
