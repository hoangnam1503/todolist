package com.android.indigo.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.indigo.R;
import com.nhaarman.listviewanimations.itemmanipulation.ExpandableListItemAdapter;


public class ExpandableListAdapter extends ExpandableListItemAdapter<Integer> {

    private final Context mContext;

    /**
     * Creates a new ExpandableListItemAdapter with the specified list, or an empty list if
     * items == null.
     */
    public ExpandableListAdapter(final Context context, final List<Integer> items) {
        super(context, R.layout.adapter_expandablelist, R.id.adapter_expandablelist_title, R.id.adapter_expandablelist_content, items);
        mContext = context;
    }

    @Override
    public View getTitleView(final int position, final View convertView, final ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if (textView == null) {
            textView = new TextView(mContext);
        }
        textView.setText(mContext.getString(R.string.listview_expandable_text, getItem(position)));
        return textView;
    }

    @Override
    public View getContentView(final int position, final View convertView, final ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            imageView = new ImageView(mContext);
        }

        int imageResId;
        switch (getItem(position) % 5) {
            case 0:
                imageResId = R.drawable.img_nature1;
                break;
            case 1:
                imageResId = R.drawable.img_nature2;
                break;
            case 2:
                imageResId = R.drawable.img_nature3;
                break;
            case 3:
                imageResId = R.drawable.img_nature4;
                break;
            default:
                imageResId = R.drawable.img_nature5;
        }
        imageView.setImageResource(imageResId);

        return imageView;
    }
}