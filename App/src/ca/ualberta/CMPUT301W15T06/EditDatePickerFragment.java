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

//http://developer.android.com/guide/topics/ui/controls/pickers.html  Apache 2.0 license 
public class EditDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		String date=AppSingleton.getInstance().getEditDate();
		int year = AppSingleton.getYear(date);
		int month = AppSingleton.getMonth(date)-1;
		int day =AppSingleton.getDay(date);
		

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		Calendar c = Calendar.getInstance();
		c.set(year,month,day);
		
		String myFormat = "yyyy-MM-dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

	    AppSingleton.getInstance().getDateEditText().setText(sdf.format(c.getTime()));
	}
	
	
}
