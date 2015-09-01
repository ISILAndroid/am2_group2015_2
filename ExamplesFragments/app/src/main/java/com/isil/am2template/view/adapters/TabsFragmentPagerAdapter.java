package com.isil.am2template.view.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.isil.am2template.view.fragments.Tab1Fragment;
import com.isil.am2template.view.fragments.Tab2Fragment;
import com.isil.am2template.view.fragments.Tab3Fragment;

public class TabsFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 3;

	public TabsFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch(arg0){

			case 0:
				Tab1Fragment fragment1 =Tab1Fragment.newInstance(null,null);
				return fragment1;

			case 1:
				Tab2Fragment fragment2 =Tab2Fragment.newInstance(null,null);
				return fragment2;
            case 2:
				Tab3Fragment fragment3 =Tab3Fragment.newInstance(null,null);
				return fragment3;
		}
		
		return null;
	}

	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
}
