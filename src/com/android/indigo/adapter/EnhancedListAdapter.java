package com.android.indigo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.indigo.R;
import com.android.indigo.utility.EnhancedListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EnhancedListAdapter extends BaseAdapter {

	protected LayoutInflater mLayoutInflater;
    protected List<String> mDataList = new ArrayList<String>();
    protected EnhancedListView mListView;
    protected Context mContext;
    
    public EnhancedListAdapter(Context context) {
    	super();
    	this.mContext = context;
    }

    public void resetItems() {
        mDataList.clear();
        for(int i = 1; i <= 40; i++) {
            mDataList.add("Item " + i);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }

    public void insert(int position, String item) {
        mDataList.add(position, item);
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mDataList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
    * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mTextView;
    }
    
    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
        	mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mLayoutInflater.inflate(R.layout.listview_item, parent, false);

            holder = new ViewHolder();
            assert convertView != null;
            holder.mTextView = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mDataList.get(position));

        return convertView;
    }

}
