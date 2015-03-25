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

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * This <code>ClaimantTagListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>TagList</code> for claimant. This view displays a list of
 * <code>Tag</code> and creates an option menu and return true as 
 * the boolean value. It will be used when the claimant asks to access to 
 * the <code>TagList</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.view.Menu
 */
public class ClaimantTagListActivity extends Activity {

	private ClaimantTagListController ctlc=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_tag_list);
		
		ctlc=new ClaimantTagListController(AppSingleton.getInstance().getClaimList());
		
		ListView listView = (ListView) findViewById(R.id.tagListView);
		final ArrayList<Tag> list =AppSingleton.getInstance().getClaimList().getTagList();
		final ArrayAdapter<Tag> adapter=new ArrayAdapter<Tag>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		
		AppSingleton.getInstance().getClaimList().addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantTagListActivity.this);
				builder.setItems(R.array.tag_dialog_array, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which==0){
							AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantTagListActivity.this);
							builder.setTitle("Edit the Tag Name");
							final EditText input=new EditText(ClaimantTagListActivity.this);
							input.setText(list.get(position).getName());
							input.setKeyListener(DigitsKeyListener.getInstance("0123456789zxcvbnmasdfghjklqwertyuiop"));				
							builder.setView(input);
							builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									ctlc.edit(list.get(position),input.getText().toString());
								}
							});
							builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							});
							builder.create();  
							builder.show();
						}else if (which==1){
							ctlc.delete(list.get(position));
						}
					}
				
			});
			builder.create();  
			builder.show();
		}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_tag_list, menu);
		return true;
	}

	
	public void addTag(View v){
		EditText addView=(EditText)findViewById(R.id.addTagEditText);
		ctlc.addTag(addView.getText().toString());
		addView.setText("");
	}
}
