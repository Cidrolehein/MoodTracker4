package com.gacon.julien.moodtracker4.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gacon.julien.moodtracker4.controllers.fragments.PageFragment;

public class PageAdapter extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to PageFragment
    private int[] colors;

    // 1.1 - List of images
    private int[] images;

    // 2 - Default Constructor
    public PageAdapter(FragmentManager fm, int[] images, int[] colors) {
        super(fm);
        this.images = images;
        this.colors = colors;

    }

    @Override
    public int getCount() {
        return images.length; // 3 - Number of page to show = nbr of images
    }

    // Items : position + background color

    @Override
    public Fragment getItem(int position) {
        // 4 - Page to return
        return (PageFragment.newInstance(position, this.images[position], this.colors[position]));
    }

}
