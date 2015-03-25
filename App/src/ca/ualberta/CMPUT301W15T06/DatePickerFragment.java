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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * The <code>DatePickerFragment</code> class is an sub-class of <code>DialogFragment</code>.
 * This class can set up a new DatePickerDialog and set date with desired format. This class 
 * will be called when user need to enter a date.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.text.SimpleDateFormat
 * @see java.util.Calendar
 * @see java.util.Locale
 * @see android.app.DatePickerDialog
 * @see android.app.Dialog
 * @see android.app.DialogFragment
 * @see android.app.TimePickerDialog
 * @see android.os.Bundle
 * @see android.widget.DatePicker
 * @see android.widget.Toast
 */
//http://developer.android.com/guide/topics/ui/controls/pickers.html  Apache 2.0 license 
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
	
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	/**
	 * Set a date with desired format. This class also called <code>AppSingleton</code>
	 * to use shared date.
	 * 
	 * @param view  a DatePicker object
	 * @param year  an Integer variable
	 * @param month  an Integer variable
	 * @param day  an Integer variable
	 */
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		Calendar c = Calendar.getInstance();
		c.set(year,month,day);
		
		

	    AppSingleton.getInstance().getDateEditText().setText(AppSingleton.getDateFormat().format(c.getTime()));
	}
}