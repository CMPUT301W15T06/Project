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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
* This <code>ClaimantItemDetailActivity</code> class is an extended class
* of <code>Activity</code> class. This class will control the interface
* of <code>Item</code> detail for claimant. This view displays 
* <code>Item</code> details, and creates an option menu, including item's
* date, category, description, amount, and amount. It will be used when the claimant 
* asks to access to the <code>Item</code> detail.
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see android.os.Bundle
* @see android.app.Activity
* @see android.content.Intent
* @see android.view.Menu
* @see android.view.MenuItem
* @see android.view.View
* @see android.widget.TextView
* @see android.widget.Toast
*/
public class ClaimantItemDetailActivity extends Activity {

	/**
	 * Set a ClaimantClaimListController object cdic with initial 
	 * default value null.
	 */
	private ClaimantDeleteItemController cdic=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_item_detail);
		
		setTitle(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
				+"<-"+AppSingleton.getInstance().getUserName());

		
		cdic=new ClaimantDeleteItemController(AppSingleton.getInstance().getCurrentClaim());
		
		final TextView dateView=(TextView) findViewById(R.id.itemDateVTextView);
		final TextView categoryView=(TextView) findViewById(R.id.itemCategoryVTextView);
		final TextView descriptionView=(TextView) findViewById(R.id.itemDescriptionVTextView);
		final TextView amountView=(TextView) findViewById(R.id.itemAmountVTextView);
		final TextView currencyView=(TextView) findViewById(R.id.itemCurrencyVTextView);
		dateView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentItem().getDate()));
		categoryView.setText(AppSingleton.getInstance().getCurrentItem().getCategory());
		descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
		amountView.setText(AppSingleton.getInstance().getCurrentItem().getAmount()==null?"":String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
		currencyView.setText(AppSingleton.getInstance().getCurrentItem().getCurrency());
		
		AppSingleton.getInstance().getCurrentItem().addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				dateView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentItem().getDate()));
				categoryView.setText(AppSingleton.getInstance().getCurrentItem().getCategory());
				descriptionView.setText(AppSingleton.getInstance().getCurrentItem().getDescription());
				amountView.setText(AppSingleton.getInstance().getCurrentItem().getAmount()==null?"":String.valueOf(AppSingleton.getInstance().getCurrentItem().getAmount()));
				currencyView.setText(AppSingleton.getInstance().getCurrentItem().getCurrency());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}

	/**
	 * Delete an item from the <code>ItemList</code> by calling the 
	 * <code>AppSingleton</code> class. It also checks the 
	 * <code>StatusException</code> warnings and errors.
	 * 
	 * @param v  a View object
	 * @see android.widget.TextView
	 * @see android.widget.Toast
	 * @exception StatusException
	 */
	public void deleteItem(View v){
		try {
			cdic.removeItem(AppSingleton.getInstance().getCurrentItem());
			finish();
		} catch (StatusException e) {
			Toast.makeText(ClaimantItemDetailActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * Edit an item in the <code>ItemList</code> by calling the 
	 * <code>ClaimantEditItemActivity</code> class and set a new intent.
	 * 
	 * @param v  a View object
	 * @see android.content.Intent
	 */
	public void editItem(View v){
		if (!AppSingleton.getInstance().isEditable()){
			Toast.makeText(ClaimantItemDetailActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();			
		}else{
			Intent intent =new Intent(ClaimantItemDetailActivity.this,ClaimantEditItemActivity.class);
			startActivity(intent);	
		}
	}
}
