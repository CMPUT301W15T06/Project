package ca.ualberta.CMPUT301W15T06;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClaimantEditClaimActivity extends Activity {

	private ClaimantEditClaimController cecc=null;
	private EditText nameView=null;
	private EditText beginView=null;
	private EditText endView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_edit_claim);
		
		cecc=new ClaimantEditClaimController(AppSingleton.getInstance().getCurrentClaim());

		nameView= (EditText) findViewById(R.id.editClaimNameEditText);
		beginView=(EditText) findViewById(R.id.editClaimStartingDateEditText);
		endView=(EditText) findViewById(R.id.editClaimEndDateEditText);
		
		nameView.setText(AppSingleton.getInstance().getCurrentClaim().getName());
		beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
		endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_edit_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void finishEdit(View v){
		try {
			if (beginView.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Start Date can't be empty", Toast.LENGTH_LONG).show();
			}else{
				cecc.editClaim(nameView.getText().toString(), beginView.getText().toString(),endView.getText().toString());
			}
		} catch (StatusException e) {
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}
		finish();
	}
	
	public void showDatePickerDialogBegin(View v) {
		AppSingleton.getInstance().setDateEditText(beginView);
		AppSingleton.getInstance().setEditDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void showDatePickerDialogEnd(View v) {
		AppSingleton.getInstance().setDateEditText(endView);
		AppSingleton.getInstance().setEditDate(AppSingleton.getInstance().getCurrentClaim().getEndDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
}
