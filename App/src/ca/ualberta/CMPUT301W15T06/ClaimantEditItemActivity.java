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
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ClaimantEditItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_edit_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_edit_item, menu);
		return true;
	}
	
	public void onResume(){
		super.onResume();

		
		EditText dateView=(EditText) findViewById(R.id.editItemDateEditText);
		EditText descriptionView=(EditText) findViewById(R.id.editItemDescriptionEditText);
		EditText amountView=(EditText) findViewById(R.id.editItemAmountEditText);
		dateView.setText(ClaimListController.getCurrentItem().getDate());
		descriptionView.setText(ClaimListController.getCurrentItem().getDescription());
		amountView.setText(String.valueOf(ClaimListController.getCurrentItem().getAmount()));
		
		Spinner currency=(Spinner) findViewById(R.id.editCurrencySpinner);
		ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,R.array.currency, 
				android.R.layout.simple_spinner_item);
		currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currency.setAdapter(currencyAdapter);
		currency.setSelection(getCurrencyPosition(ClaimListController.getCurrentItem().getCurrency()));
		
		Spinner category=(Spinner) findViewById(R.id.editCategorySpinner);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,R.array.category, 
				android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(categoryAdapter);
		category.setSelection(getCategoryPosition(ClaimListController.getCurrentItem().getCategory()));


	}
	
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

	//used to check the int  position of the input currency
	private int getCurrencyPosition(String currency){
		String [] list={"CAD","USD", "EUR", "GBP","CHF","JPY","CNY"};
		for (int i=0;i<list.length;i++){
			if (list[i].equals(currency)){
				return i;
			}
		}
		return 0;
	}
	
	public void finishEdit(View v){
		EditText dateView= (EditText) findViewById(R.id.editItemDateEditText);
		Spinner category= (Spinner ) findViewById(R.id.editCategorySpinner);
		EditText descriptionView= (EditText) findViewById(R.id.editItemDescriptionEditText);
		EditText amountView= (EditText) findViewById(R.id.editItemAmountEditText);
		Spinner currency=(Spinner) findViewById(R.id.editCurrencySpinner);
		ClaimListController.editItem(dateView.getText().toString(),category.getSelectedItem().toString(),
				descriptionView.getText().toString(),Double.parseDouble(amountView.getText().toString()),
				currency.getSelectedItem().toString());
		finish();
	}

}
