package com.matsuo.centum7.fragmentsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        int position = getIntent().getIntExtra(TitlesFragment.EXTRA_POSITION,0);
        DetailFragment detailFragment = DetailFragment.newInstance(position);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detailFrame,detailFragment)
                .commit();
    }

}
