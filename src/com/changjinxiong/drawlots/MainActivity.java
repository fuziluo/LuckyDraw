package com.changjinxiong.drawlots;

import java.util.ArrayList;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity 
	implements SettingsDialogFragment.SettingsDialogListener {
	public final static String EXTRA_MESSAGE = "com.changjinxiong.drawlots.MESSAGE";
	private static final String NAME_LIST_KEY = "com.changjinxiong.drawlots.NAME_LIST";
	private static final String NUMBER_OF_LOTS_KEY = "com.changjinxiong.drawlots.NUMBER_OF_LOTS";
	//private RandomizedQueue<String> randomizedQueue;
	protected ArrayList<String> nameList;
	protected int numberOfLots = 1;
    // Create a message handling object as an anonymous class.
	
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
        	//System.out.println("position = " + position + ". Row ID = " + id);
        }
    };
    private OnItemLongClickListener mMessageLongClickedHandler = new OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the long click
        	//System.out.println("position = " + position + ". Row ID = " + id);
        	nameList.remove(position);
        	refreshText();
        	refreshNameList();
        	return true;
        }
    };
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putStringArrayList(NAME_LIST_KEY, nameList);
        savedInstanceState.putInt(NUMBER_OF_LOTS_KEY, numberOfLots);
        
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
       
        // Restore state members from saved instance
    	nameList = savedInstanceState.getStringArrayList(NAME_LIST_KEY);
    	numberOfLots = savedInstanceState.getInt(NUMBER_OF_LOTS_KEY);
    	
    	refreshNameList();

    	//System.out.println("*******onRestoreInstanceState*******");
    }

    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        if (savedInstanceState == null) {
	            getSupportFragmentManager().beginTransaction()
	                    .add(R.id.container, new PlaceholderFragment())
	                    .commit();
	            nameList = new ArrayList<String>();
	        }
	//        System.out.println("*******onCreate*******");
	    }

	@Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        // The activity is either being restarted or started for the first time
       
    }

    @Override
    protected void onStop() {
    	//System.out.println("*******on stop*******");
    	super.onStart();
    }
    @Override
    protected void onPause() {
    	//System.out.println("*******on pause*******");
    	super.onPause();
    }
    @Override
    protected void onResume() {
    	//System.out.println("*******on Resume*******");
    	super.onResume();
//    	setContentView(R.layout.fragment_main);
    	refreshText();
        //set up the listeners
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setOnItemClickListener(mMessageClickedHandler); 
        gridView.setOnItemLongClickListener(mMessageLongClickedHandler);

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       
        switch (item.getItemId()) {
        case R.id.action_reset:
            openReset();
            return true;
        case R.id.action_about:
            openAbout();
            return true;
        case R.id.action_settings:
            openSettings();
            return true;
        case R.id.action_save:
            openSave();
            return true;
        case R.id.action_load:
            openLoad();
            return true;
        case R.id.action_help:
            openHelp();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
    }

    private void openHelp() {
		// TODO Auto-generated method stub
		//launch a new help dialog
		HintDialogFragment newFragment = new HintDialogFragment();
        newFragment.show(getFragmentManager(), "hint");
		
	}

	private void openLoad() {
		// TODO Auto-generated method stub
    	String fileName = getFilesDir().getPath() + "/name list 0";
		ArrayList<String> list = new ArrayList<String>();
        try {
        	In in = new In(fileName);
            while (!in.isEmpty()) {
            	String str = in.readLine();
//            	System.out.println(str);
            	list.add(str);
            }
        }
        catch (Exception e) { 
        	System.out.println(e); 
        	showToast("No saved name list found.");
        	return;
        }
        nameList = list;
        refreshText();
        refreshNameList();
        showToast("Name list loaded.");
	}

	private void openSave() {
		// TODO Auto-generated method stub
		if (nameList.isEmpty()) {
			showToast("No name in the list, list not saved.");
			return;
		}
			
		String fileName = getFilesDir().getPath() + "/name list 0";

		Out out = new Out(fileName);
		for (String string : nameList)
			out.println(string);
        out.close();
        showToast("Name list saved.");
	}

	private void openSettings() {
    	SettingsDialogFragment newFragment = new SettingsDialogFragment();
    	newFragment.show(getFragmentManager(), "settings");
	}

	private void openAbout() {
    	// Create new fragment and transaction
    	AboutDialogFragment newFragment = new AboutDialogFragment();
        newFragment.show(getFragmentManager(), "about");
		
	}

	private void openReset() {
		nameList = new ArrayList<String>();
		//setContentView(R.layout.fragment_main);
		//numberOfLots = 1;
		refreshNameList();
		refreshText();
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    /** Called when the user clicks the add button */
	public void addName(View view) {
	    // Do something in response to button
		//LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//LayoutInflater inflater = getLayoutInflater();
		//LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_main, null);
	//	LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.linearLayoutMain2);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();

		if ((message != null) && (!message.equals("") && (!nameList.contains(message)))) {
			//add to arraylist
			nameList.add(message);

			//use adapter
			refreshNameList();
			
			//clear the edittext
			editText.getText().clear();
			//editText.setText("");
		}
		refreshText();

	}
	
	/** Called when the user clicks the Send button */
		public void sendMessage(View view) {
		    // Do something in response to button
			if (!nameList.isEmpty()) {
				ArrayList<String> nameListCopy = (ArrayList<String>) nameList.clone();
				ArrayList<String> luckyNames = new ArrayList<String>();
				
				Intent intent = new Intent(this, ShowResult.class);
	//			int luckyIndex = (int) (nameListCopy.size() * Math.random());
	//			String luckyName = nameListCopy.get(luckyIndex);
				if (numberOfLots > nameListCopy.size()) {
	//				//launch a new alert dialog, to be done
	//				HintDialogFragment newFragment = new HintDialogFragment();
	//		        newFragment.show(getFragmentManager(), "hint");
	
					//launch a toast
					showToast("The number of names in the list (" + nameList.size() + 
	        		") is less than the number of lots to be drawn (" + numberOfLots + 
	        		") . Add more names to the list or decrease the number of lots in settings");
					
					return;
				}
					
				for (int i = 0; i < numberOfLots; i++) {
					int luckyIndex = (int) (nameListCopy.size() * Math.random());
					String luckyName = nameListCopy.remove(luckyIndex);
					luckyNames.add(luckyName);
				}
				
				intent.putStringArrayListExtra(EXTRA_MESSAGE, luckyNames);
	//			intent.putExtra(EXTRA_MESSAGE, luckyName);
				startActivity(intent);
			}
			else {
				showToast("No name in the list.");
			}
			
		}

	private void showToast(String string) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, string, duration);
		toast.show();
	}

	private void refreshText() {
		
		String message = "";
		TextView textView = (TextView) findViewById(R.id.textView1);
		switch (nameList.size()) {
		case 0:
			message = "No name in the list.";
			textView.setText(message);
			break;
		case 1:
			message = "1 name in the list. " + "Press Next to pick " +  numberOfLots + " out of them. Change the number of ";
			textView.setText(message);
			break;
		default:
			message = nameList.size() + " names in the list. " + "Press Next to pick " +  numberOfLots + " out of them.";
			textView.setText(message);
			break;
		}
	}

	private void refreshNameList() {
		// TODO Auto-generated method stub
		String[] nameListArray = new String[nameList.size()];
		ArrayAdapterWithCenterTextView<String> adapter = new ArrayAdapterWithCenterTextView<String>(this, android.R.layout.simple_list_item_1, nameList.toArray(nameListArray));
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(adapter);
	}

	public void onDialogPositiveClick(DialogFragment dialog) {
		
    	NumberPicker numberPicker = (NumberPicker) dialog.getDialog().findViewById(R.id.settings_number_picker);
    	int number = numberPicker.getValue();
		if (number > 0 && number <= 10)
			numberOfLots = number;
		refreshText();
	}
    public void onDialogNegativeClick(DialogFragment dialog) {
    	
    }

}
