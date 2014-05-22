package com.android.indigo.activity;

import com.android.indigo.R;
import com.android.indigo.adapter.TabPagerAdapter;
import com.android.indigo.fragment.QuickContactFragment;
import com.android.indigo.fragment.base.FragmentActivityBase;
import com.android.indigo.utility.SlidingTabStrip;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends FragmentActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		slidingTab = (SlidingTabStrip) findViewById(R.id.tabs);
		viewPager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

		slidingTab.setShouldExpand(true);
		slidingTab.setIndicatorColorResource(R.color.indicator_slider_color);
		
		viewPager.setAdapter(pagerAdapter);
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		viewPager.setPageMargin(pageMargin);
		viewPager.setCurrentItem(1);

		slidingTab.setViewPager(viewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(this, "You are home now!", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_contact:
				QuickContactFragment dialog = new QuickContactFragment();
				dialog.show(getSupportFragmentManager(), "QuickContactFragment");
				return true;
		case R.id.action_search:
			Toast.makeText(this, "Please try google.com!", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_info:
			Toast.makeText(this, "No information available!", Toast.LENGTH_SHORT).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}