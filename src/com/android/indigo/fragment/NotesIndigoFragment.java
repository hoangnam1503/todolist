package com.android.indigo.fragment;

import android.os.Bundle;
import android.widget.AbsListView;

import com.android.indigo.adapter.NoteListAdapter;
import com.android.indigo.fragment.base.ListFragmentBase;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class NotesIndigoFragment extends ListFragmentBase implements OnDismissCallback {
	private NoteListAdapter mNoteListAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mNoteListAdapter = new NoteListAdapter(mContext);
		SwipeDismissAdapter mSwipeDismissAdapter = new SwipeDismissAdapter(mNoteListAdapter, this);
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter( mNoteListAdapter, this));
		swingBottomInAnimationAdapter.setInitialDelayMillis(300);
		swingBottomInAnimationAdapter.setAbsListView(mListView);
		
		mListView.setAdapter(swingBottomInAnimationAdapter);
		mNoteListAdapter.addAll(getItems());
	}

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			mNoteListAdapter.remove(position);
		}
	}
}
