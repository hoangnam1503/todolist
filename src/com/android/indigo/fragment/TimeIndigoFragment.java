package com.android.indigo.fragment;

import java.util.ArrayList;
import java.util.Arrays;

import com.android.indigo.R;
import com.android.indigo.adapter.ExpandableListAdapter;
import com.android.indigo.fragment.base.ListFragmentBase;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter.DeleteItemCallback;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by hoang_nam on 2014/05/14.
 */
public class TimeIndigoFragment extends ListFragmentBase implements OnDismissCallback, DeleteItemCallback {
	protected ExpandableListAdapter mExpandableListAdapter;
	protected ListView mListView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mListView = (ListView) getListView();
		mExpandableListAdapter = new ExpandableListAdapter(mContext, getItems());
		AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(new SwipeDismissAdapter(mExpandableListAdapter, this));
		alphaInAnimationAdapter.setAbsListView(getListView());
		alphaInAnimationAdapter.setInitialDelayMillis(500);
		
		mListView.setAdapter(alphaInAnimationAdapter);
	}

	@Override
	public void deleteItem(int position) {
		mExpandableListAdapter.remove(position);
	}

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			mExpandableListAdapter.remove(position);
		}
		Toast.makeText(mContext, "Removed position: " + Arrays.toString(reverseSortedPositions), Toast.LENGTH_SHORT).show();
	}
	
}
