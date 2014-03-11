package com.spydi2kood.prisma;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

/**
 * Created by jim on 11/3/2014.
 */
public class AddGeo extends Fragment {
	private static final String TAG = "addGeo";
	private ProgressDialog pDialog;
	AQuery aq;
	EditText ada;
	Button buttonSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.geo_add, container, false);
		aq = new AQuery(getActivity(), rootView);
		buttonSearch = (Button) rootView.findViewById(R.id.button_search);
		buttonSearch.setOnClickListener(new buttonClickListener());
		ada = (EditText) rootView.findViewById(R.id.entered_ada);
		return rootView;
	}

	private class buttonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			if (ada.getText().toString() != null) {
				String mada = ada.getText().toString();
				showDialog();
				serverRequest(mada);
			}
		}
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//		showDialog();
	}

	private void showDialog() {
		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Loading. Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	private void serverRequest(String ada) {
		Log.d(TAG, ada);
		String url = "http://83.212.109.124/Prisma/android/ada/".concat(ada);
		aq.ajax(url, JSONObject.class, this, "jsonCallback");
	}

	public void jsonCallback(String url, JSONObject json, AjaxStatus status) {
		if (json != null) {
			Log.d(TAG,json.toString());
		} else {
			Log.d(TAG, status.getMessage());
//			error.setText(status.getMessage());
		}
		pDialog.dismiss();
	}

}