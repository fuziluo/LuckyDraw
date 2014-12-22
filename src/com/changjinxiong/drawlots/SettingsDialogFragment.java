package com.changjinxiong.drawlots;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class SettingsDialogFragment extends DialogFragment {
	public interface SettingsDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
	SettingsDialogListener mListener;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        View settingsView = inflater.inflate(R.layout.settings, null);
        NumberPicker numberPicker = (NumberPicker)settingsView.findViewById(R.id.settings_number_picker);
    	numberPicker.setMaxValue(10);
    	numberPicker.setMinValue(1);
    	numberPicker.setValue(((MainActivity)getActivity()).numberOfLots);
    	
        builder.setView(settingsView)
        // Add action buttons
               .setPositiveButton(R.string.settings_OK, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogPositiveClick(SettingsDialogFragment.this);
                   }
               })
               .setNegativeButton(R.string.settings_cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mListener.onDialogNegativeClick(SettingsDialogFragment.this);
                   }
               }); 
        

    	
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
 // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (SettingsDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}