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

public class ClaimantAddItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_item, menu);
		return true;
	}
	
	public void onResume(){
		super.onResume();
		Spinner currency=(Spinner) findViewById(R.id.createCurrencySpinner);
		ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,R.array.currency, 
				android.R.layout.simple_spinner_item);
		currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currency.setAdapter(currencyAdapter);
		Spinner category= (Spinner) findViewById(R.id.createItemCategorySpinner);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,R.array.category, 
				android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(categoryAdapter);
	}
	
	public void finishAdd(View v){
		EditText dateView= (EditText) findViewById(R.id.createItemDateEditText);
		Spinner category= (Spinner ) findViewById(R.id.createItemCategorySpinner);
		EditText descriptionView= (EditText) findViewById(R.id.createItemDescriptionEditText);
		EditText amountView= (EditText) findViewById(R.id.createItemAmountEditText);
		Spinner currency=(Spinner) findViewById(R.id.createCurrencySpinner);
		ClaimListController.addItem(dateView.getText().toString(),category.getSelectedItem().toString(),
				descriptionView.getText().toString(),Double.parseDouble(amountView.getText().toString()),
				currency.getSelectedItem().toString());
		finish();
	}

}
