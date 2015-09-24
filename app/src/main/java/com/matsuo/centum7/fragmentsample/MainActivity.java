package com.matsuo.centum7.fragmentsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TitlesFragment.OnTitleSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onTitleSelected(int position) {
        DetailFragment detailFragment = DetailFragment.newInstance(position);

        /*右側に配置*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailFrame,detailFragment)
                .commit();
    }
}
