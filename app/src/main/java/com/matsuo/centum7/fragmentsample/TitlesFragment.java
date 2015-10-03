package com.matsuo.centum7.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by matsuotakurou on 15/09/23.
 */
public class TitlesFragment extends ListFragment {

    public final static String EXTRA_POSITION =
            "com.matsuo.centum7.fragmentsample.POSITION";

    private OnTitleSelectedListener listener;
    private int mSavedPosition;

    //２画面かどうかを判断する変数
    private boolean isDualPane;


    public TitlesFragment (){}

    public interface OnTitleSelectedListener{
         void onTitleSelected(int position);
    }


    /*fragmentがactivityにAttachされた時に、かならずonAttachが実装される。*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            listener = (OnTitleSelectedListener) activity;
        }catch (ClassCastException e){
            /*インターフェイスが実装されていなかった時に例外を投げる。*/
            throw new ClassCastException(activity.toString()+
                    "must implement onTitleSelected");
        }
    }

    /*
    fragmentがactivityから離された時の処理をonDetachに記述
    */

    @Override
    public void onDetach() {
        super.onDetach();
        /* Listenerを無効化*/
        listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                News.Titles
        ));

        /* Acitivty作られた時に　activityの中にdetialframeがあるか判断します。*/

        View detailFrame = getActivity().findViewById(R.id.detailFrame);

        /*
        * isDualPaneがtrueになるのは　detailFrameがnullではない場合とdetailFrameがない場合
        *
        * */
        isDualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;


        /*　Acitivityが再起動したときに、値を取り出す。*/

        if(isDualPane) {
            if(savedInstanceState != null) {
                mSavedPosition = savedInstanceState.getInt("save_position");
            } else {
                mSavedPosition = 0;
            }

            /*左側のリストをクリックした時に呼ばれる処理をmSavePositionで呼ぶ。*/
            listener.onTitleSelected(mSavedPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("save_position",mSavedPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mSavedPosition = position;

        if(isDualPane){
            listener.onTitleSelected(position);
        }else{


            Intent intent =new Intent(getActivity(),SubActivity.class);
            intent.putExtra(EXTRA_POSITION,position);
            startActivity(intent);
        }

    }

}
