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


import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This <code>ClaimantEditItemActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * editing <code>Item</code> detail for claimant. This view displays the 
 * detail of <code>Item</code> and allows claimant to change and update 
 * the information. It will be used when the claimant asks to edit the item
 * detail.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see android.app.Activity
 * @see android.app.DialogFragment
 * @see android.text.Editable
 * @see android.text.TextWatcher
 * @see android.os.Bundle
 * @see android.view.Menu
 * @see android.view.View
 * @see android.view.View.OnClickListener
 * @see android.widget.EditText
 * @see android.widget.Toast
 * @see android.widget.AdapterView
 * @see android.widget.AdapterView.OnItemClickListener
 * @see android.widget.AdapterView.OnItemSelectedListener
 * @see android.widget.ArrayAdapter
 * @see android.widget.Spinner
 */
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
	/**
	 * Set a Item object item to represent an expense item in the claim with default value as null.
	 */
	private Item item=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_edit_item);
		

		setTitle(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
				+"<-"+AppSingleton.getInstance().getUserName());

		
		item=AppSingleton.getInstance().getCurrentItem();
		ceic=new ClaimantEditItemController(item);
		
		dateView=(EditText) findViewById(R.id.editItemDateEditText);
		
		final EditText descriptionView=(EditText) findViewById(R.id.editItemDescriptionEditText);
		final EditText amountView=(EditText) findViewById(R.id.editItemAmountEditText);
		
		
		
		
		
		dateView.setText(AppSingleton.formatDate(item.getDate()));		
		dateView.addTextChangedListener(new  TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					ceic.editDate(dateView.getText().toString());
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}
		});

		descriptionView.setText(item.getDescription());
		descriptionView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					ceic.editDescription(descriptionView.getText().toString());
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}
		});
		
		if (item.getAmount()==null){
			amountView.setText("");
		}else{
			amountView.setText(String.valueOf(item.getAmount()));
		}
		

		amountView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					Double amount;
					try {
						amount=Double.parseDouble(amountView.getText().toString());
					} catch (NumberFormatException e) {
						amount=null;
					}
					ceic.editAmount(amount);
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}
		});
		
		Spinner currency=(Spinner) findViewById(R.id.editCurrencySpinner);
		ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,R.array.currency, 
				android.R.layout.simple_spinner_item);
		currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currency.setAdapter(currencyAdapter);
		currency.setSelection(getCurrencyPosition(item.getCurrency()));
		currency.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				try {
					ceic.editCurrency(getCurrency(position));
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Spinner category=(Spinner) findViewById(R.id.editCategorySpinner);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,R.array.category, 
				android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(categoryAdapter);
		category.setSelection(getCategoryPosition(item.getCategory()));
		category.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				try {
					ceic.editCategory(getCategory(position));
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
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
	
	/**
	 * This method will display a list of item category for user to choose.
	 * 
	 * @param i  an integer that represent the index in the list of option category
	 * @return one of the category in the listed category
	 */
	private String getCategory(int i){
		String [] list={"air fare", "ground transport", "vehicle rental", "private automobile", "fuel", "parking",
				"registration", "accommodation", "meal","supplies"};
		return list[i];
	}

	/**
	 * This method used to check the integer position of the input currency.
	 * 
	 * @param currency  the currency of the expense amount (like "CAD")
	 * @return the index integer. If fail the check, return 0.
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
	 * This method will display a list of the currency of the expense amount for user to choose.
	 * 
	 * @param i  an integer that represent the index in the list of option currency
	 * @return one of the category in the listed currency
	 */
	private String getCurrency(int i){
		String [] list={"CAD","USD", "EUR", "GBP","CHF","JPY","CNY"};
		return list[i];
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
		AppSingleton.getInstance().setEditDate(item.getDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

}
