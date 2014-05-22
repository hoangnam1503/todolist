package com.android.indigo.fragment;

import com.android.indigo.R;
import com.android.indigo.adapter.EnhancedNoteListAdapter;
import com.android.indigo.fragment.base.ListFragmentBase;
import com.android.indigo.utility.EnhancedListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by hoang_nam on 2014/05/14.
 */
public class ListIndigoFragment extends ListFragmentBase {
	EnhancedListView mListView;
	EnhancedNoteListAdapter mListAdapter;
    
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

        mListView = (EnhancedListView) getListView();

        mListAdapter = new EnhancedNoteListAdapter(mContext);
        mListAdapter.addAll(getItems());

        mListView.setAdapter(mListAdapter);

        // Set the callback that handles dismisses.
        mListView.setDismissCallback(new EnhancedListView.OnDismissCallback() {
            /**
             * This method will be called when the user swiped a way or deleted it via
             * {@link de.timroes.android.listview.EnhancedListView#delete(int)}.
             *
             * @param listView The {@link EnhancedListView} the item has been deleted from.
             * @param position The position of the item to delete from your adapter.
             * @return An {@link de.timroes.android.listview.EnhancedListView.Undoable}, if you want
             *      to give the user the possibility to undo the deletion.
             */
            @Override
            public EnhancedListView.Undoable onDismiss(EnhancedListView listView, final int position) {

                final Integer item = (Integer) getItem(position);
                mListAdapter.remove(position);
                return new EnhancedListView.Undoable() {
                    @Override
                    public void undo() {
                        mListAdapter.add(position, item);
                    }
                };
            }
        });

        // Show toast message on click and long click on list items.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Clicked on item " + mListAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Long clicked on item " + mListAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        applySettings();
        mListView.setSelection(20);
	}
	
    /**
     * Applies the settings the user has made to the list view.
     */
    private void applySettings() {

        // Set the UndoStyle, the user selected.
        EnhancedListView.UndoStyle style;
        style = EnhancedListView.UndoStyle.SINGLE_POPUP;
        mListView.setUndoStyle(style);

        // Enable or disable Swipe to Dismiss
        mListView.enableSwipeToDismiss();

        // Set the swipe direction
        EnhancedListView.SwipeDirection direction;
        direction = EnhancedListView.SwipeDirection.START;
        mListView.setSwipeDirection(direction);

        // Enable or disable swiping layout feature
        mListView.setSwipingLayout(R.id.swiping_layout);
    }
}
