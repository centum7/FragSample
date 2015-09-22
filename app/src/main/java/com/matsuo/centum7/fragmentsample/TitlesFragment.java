package com.matsuo.centum7.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

/**
 * Created by matsuotakurou on 15/09/23.
 */
public class TitlesFragment extends ListFragment {
   public TitlesFragment (){}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                News.Titles
        ));
    }
}
