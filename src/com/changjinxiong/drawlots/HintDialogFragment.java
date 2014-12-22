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

public class HintDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        int numberOfLots = ((MainActivity) getActivity()).numberOfLots;
//        int numberOfNames = ((MainActivity) getActivity()).nameList.size();
//        assert numberOfNames < numberOfLots;
        builder.setTitle("Help")
               .setMessage(	"Type a name and press Add to add a name to the name list." + System.getProperty("line.separator") + System.getProperty("line.separator") +
            		   		"Long press a name in the list to remove the name from the list." + System.getProperty("line.separator") +  System.getProperty("line.separator") +
       		   				"Reset - clear the current name list." + System.getProperty("line.separator") + System.getProperty("line.separator") +
            		   		"Save - save the current name list." + System.getProperty("line.separator") + System.getProperty("line.separator") +
            		   		"Load - load saved name list."
            		   );
        builder.setPositiveButton(R.string.dialog_got_it, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                 
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    }
}