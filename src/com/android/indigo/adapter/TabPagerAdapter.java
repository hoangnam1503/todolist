package com.android.indigo.adapter;

import com.android.indigo.fragment.ListIndigoFragment;
import com.android.indigo.fragment.TodoIndigoFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {


	private final String[] TITLES = { "Todo", "Notes" };

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new TodoIndigoFragment();
		default:
			return new ListIndigoFragment();
		}
	}

}
