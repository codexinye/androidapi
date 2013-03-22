package com.xinye.support.fragment;

import com.xinye.support.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * µÚÒ»¸öFragment
 * @author Administrator
 *
 */
public class FirstFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_first, container, false);
	}
	@Override
	public void onPause() {
		super.onPause();
	}
}
