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
 * This <code>ClaimantEditItemActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * editing <code>Item</code> detail for claimant. This view displays the 
 * detail of <code>Item</code> and allows claimant to change and update 
 * the information. It will be used when the claimant asks to edit the item
 * detail.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see android.app.Activity
 * @see android.app.DialogFragment
 * @see android.os.Bundle
 * @see android.view.Menu
 * @see android.view.View
 * @see android.widget.EditText
 * @see android.widget.Toast
 * @see android.widget.ArrayAdapter
 * @see android.widget.Spinner
 */
package ca.ualberta.CMPUT301W15T06;


import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClaimantEditItemActivity extends Activity {

	/**
	 * Set a EditText object dateView to view and edit the date.
	 * The initial default value is null.
	 * 
	 * @see android.widget.EditText
	 */
	private EditText dateView=null;
	/**
	 * Set a ClaimantEditItemController object ceic with initial 
	 * default value null.
	 */
	private ClaimantEditItemController ceic=null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_edit_item);
		

		dateView=(EditText) findViewById(R.id.editItemDateEditText);
		ceic=new ClaimantEditItemController(AppSingleton.getInstance().getCurrentItem());
		
		EditText descriptionView=(EditText) findViewById(R.id.editItemDescriptionEditText);
		EditText amountView=(EditText) findViewById(R.id.editItemAmountEditText);
		
		dateView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentItem().getDate()));
		descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
		
		if (AppSingleton.getInstance().getCurrentItem().getAmount()==null){
			amountView.setText("");
		}else{
			amountView.setText(String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
		}
		
		
		Spinner currency=(Spinner) findViewById(R.id.editCurrencySpinner);
		ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,R.array.currency, 
				android.R.layout.simple_spinner_item);
		currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currency.setAdapter(currencyAdapter);
		currency.setSelection(getCurrencyPosition(AppSingleton.getInstance().getCurrentItem().getCurrency()));
		
		Spinner category=(Spinner) findViewById(R.id.editCategorySpinner);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,R.array.category, 
				android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(categoryAdapter);
		category.setSelection(getCategoryPosition(AppSingleton.getInstance().getCurrentItem().getCategory()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_edit_item, menu);
		return true;
	}

	/**
	 * This method get the category of a item. It will choose a category from 
	 * a String List, and return the number of the position of the desired 
	 * category in the list.
	 * 
	 * @param category  a String variable
	 * @return an integer variable
	 */
	private int getCategoryPosition(String category) {
		String [] list={"air fare", "ground transport", "vehicle rental", "private automobile", "fuel", "parking",
				"registration", "accommodation", "meal","supplies"};
		for (int i=0;i<list.length;i++){
			if (list[i].equals(category)){
				return i;
			}
		}
		return 0;
	}

	//used to check the integer position of the input currency
	/**
	 * This method get the currency of a item. It will choose a category from 
	 * a String List, and return the number of the position of the desired 
	 * currency in the list.
	 * 
	 * @param currency
	 * @return an integer variable
	 */
	private int getCurrencyPosition(String currency){
		String [] list={"CAD","USD", "EUR", "GBP","CHF","JPY","CNY"};
		for (int i=0;i<list.length;i++){
			if (list[i].equals(currency)){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * This method will create a finish button to trigger the action finishAdd. 
	 * It will also checks <code>NumberFormatException()</code> and 
	 * <code>StatusException()</code> warning to prevent crush.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @see android.widget.Spinner
	 * @see android.widget.Toast
	 */
	public void finishEdit(View v){
		EditText dateView= (EditText) findViewById(R.id.editItemDateEditText);
		Spinner category= (Spinner ) findViewById(R.id.editCategorySpinner);
		EditText descriptionView= (EditText) findViewById(R.id.editItemDescriptionEditText);
		EditText amountView= (EditText) findViewById(R.id.editItemAmountEditText);
		Spinner currency=(Spinner) findViewById(R.id.editCurrencySpinner);
		

		Double amount;
		try {
			amount=Double.parseDouble(amountView.getText().toString());
		} catch (NumberFormatException e) {
			amount=null;
		}


		try {
			ceic.editItem(dateView.getText().toString(),category.getSelectedItem().toString(),
					descriptionView.getText().toString(),amount,
					currency.getSelectedItem().toString());
		} catch (StatusException e) {
			Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}
		finish();
	}
	
	/**
	 * This method will display the DatePickerDialog by calling 
	 * <code>AppSingleton</code> and <code>DialogFragment</code>.
	 * 
	 * @param v  a View object
	 * @see android.app.DialogFragment
	 */
	public void showDatePickerDialog(View v) {
		AppSingleton.getInstance().setDateEditText(dateView);
		AppSingleton.getInstance().setEditDate(AppSingleton.getInstance().getCurrentItem().getDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

}
