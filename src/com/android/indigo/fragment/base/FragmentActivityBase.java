package com.android.indigo.fragment.base;

import com.android.indigo.utility.SlidingTabStrip;
import com.android.indigo.adapter.TabPagerAdapter;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public abstract class FragmentActivityBase extends ActionBarActivity {
	// Application code
	public static final int REQUEST_CODE_BASIC = 1;
	
	// Design features
	protected SlidingTabStrip slidingTab;
	protected ViewPager viewPager;
	protected TabPagerAdapter pagerAdapter;

	public void goNextActivity(Intent intent) {
		startActivityForResult(intent, REQUEST_CODE_BASIC);
	}
	
	public void goNextActivity(Intent intent, int requestCode) {
		startActivityForResult(intent, requestCode);
	}
}
