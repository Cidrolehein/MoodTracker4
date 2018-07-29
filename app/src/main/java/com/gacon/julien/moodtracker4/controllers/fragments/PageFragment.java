package com.gacon.julien.moodtracker4.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.gacon.julien.moodtracker4.R;

public class PageFragment extends Fragment {

    private RelativeLayout rootView;
    private ImageView imageView;

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION = "KEY_POSITION";
    private static final String KEY_COLOR = "KEY_COLOR";
    private static final String KEY_IMAGE = "KEY_IMAGE";

    public PageFragment() {
        // Required empty public constructor
    }

    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position, int image, int color) {

        // 2.1 Create new fragment
        PageFragment fragment = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_IMAGE, image);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        // 4 - Get widgets from layout and serialise it
        rootView = (RelativeLayout) result.findViewById(R.id.fragment_page_rootview);
        imageView = (ImageView) result.findViewById(R.id.fragment_page_mood_img);

        // 5 - Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);
        int images = getArguments().getInt(KEY_IMAGE, -1);

        rootView.setBackgroundColor(color);
        imageView.setImageResource(images);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }
}
