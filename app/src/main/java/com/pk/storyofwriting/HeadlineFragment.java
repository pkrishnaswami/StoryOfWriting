package com.pk.storyofwriting;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pkris on 12/21/2017.
 */

public class HeadlineFragment extends ListFragment {
    OnHeadlineSelectedListener callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (OnHeadlineSelectedListener)activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString()+" must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        String[] data = Ipsum.Headlines;
        setListAdapter(new ArrayAdapter<>(getActivity(), layout,data));
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.article_fragment);
        ListView listView = getListView();
        if(fragment != null && listView != null){
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       callback.onArticleSelected(position);
       l.setItemChecked(position,true);
    }
}
