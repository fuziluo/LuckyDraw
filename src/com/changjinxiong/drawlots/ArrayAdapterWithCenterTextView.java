package com.changjinxiong.drawlots;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArrayAdapterWithCenterTextView<T> extends ArrayAdapter<T>{
	public ArrayAdapterWithCenterTextView(Context context, int resource) {
		super(context, resource);
	}
	public ArrayAdapterWithCenterTextView(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}
	public ArrayAdapterWithCenterTextView(Context context, int resource, T[] objects) {
		super(context, resource, objects);
	}
	public ArrayAdapterWithCenterTextView(Context context, int resource, int textViewResourceId, T[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
	public ArrayAdapterWithCenterTextView(Context context, int resource, List<T> objects) {
		super(context, resource, objects);
	}
	public ArrayAdapterWithCenterTextView(Context context, int resource,int textViewResourceId, List<T> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) super.getView(position, convertView, parent);
		view.setGravity(0x11);
		return view;
	}
}