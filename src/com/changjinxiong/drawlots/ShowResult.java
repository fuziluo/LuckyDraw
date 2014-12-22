package com.changjinxiong.drawlots;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowResult extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_result);
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	    // Get the message from the intent
	    Intent intent = getIntent();
	    ArrayList<String> luckyNames = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);
	    String[] nameListArray = new String[luckyNames.size()];
	    // Set the layout defined in xml as the activity layout
	    setContentView(R.layout.fragment_show_result);
	   
	    // update the text view
//	    TextView textView1 = (TextView)findViewById(R.id.textView1);
//	    String message = "";
//	    for (String str : luckyNames)
//	    	message += str + System.getProperty("line.separator");
//	    textView1.setText(message);
	    ArrayAdapterWithCenterTextView<String> adapter = new ArrayAdapterWithCenterTextView<String>(this, android.R.layout.simple_list_item_1, luckyNames.toArray(nameListArray));
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//System.out.println("*************" + item.getItemId() + "*");
		switch (item.getItemId()) {
        case android.R.id.home:
        	onBackPressed();
            return true;
        case R.id.action_about:
            openAbout();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        	//return true;
		}
		
	}

	private void openAbout() {
		// TODO Auto-generated method stub
    	AboutDialogFragment newFragment = new AboutDialogFragment();
        newFragment.show(getFragmentManager(), "about");
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_show_result,
					container, false);
			return rootView;
		}
	}

}
