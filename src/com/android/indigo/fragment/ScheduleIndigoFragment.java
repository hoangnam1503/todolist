package com.android.indigo.fragment;

import com.android.indigo.R;
import com.android.indigo.fragment.base.IndigoFragmentBase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by hoang_nam on 2014/05/14.
 */
public class ScheduleIndigoFragment extends IndigoFragmentBase {
	public CalendarView mCalendarView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
		
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mCalendarView = (CalendarView) getActivity().findViewById(R.id.calendar_view);
	}
}
