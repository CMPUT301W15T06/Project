package ca.ualberta.CMPUT301W15T06;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

//http://developer.android.com/guide/topics/ui/controls/pickers.html  Apache 2.0 license 
public class EditDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	@SuppressWarnings("deprecation")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		Date date=AppSingleton.getInstance().getEditDate();
		int year = 0;
		int month = 0;
		int day = 0;
		Calendar c = Calendar.getInstance();
		if (date!=null){
			c.setTime(date);
		}
		
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		Calendar c = Calendar.getInstance();
		c.set(year,month,day);
		
		

	    AppSingleton.getInstance().getDateEditText().setText(AppSingleton.getDateFormat().format(c.getTime()));
	}
	
	
}
