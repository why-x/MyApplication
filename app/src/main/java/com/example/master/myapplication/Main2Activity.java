package com.example.master.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity{

    private RadioButton mRadio1;
    private RadioButton mRadio2;
    private RadioButton mRadio3;
    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;
    private ThreeFragment mThreeFragment;
    private RadioGroup mRadiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRadiogroup = findViewById(R.id.radiogroup);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();
        mThreeFragment = new ThreeFragment();

        fragmentTransaction.add(R.id.fl, mOneFragment);
        fragmentTransaction.add(R.id.fl, mTwoFragment);
        fragmentTransaction.add(R.id.fl, mThreeFragment);
        fragmentTransaction.hide(mTwoFragment);
        fragmentTransaction.hide(mThreeFragment);
        fragmentTransaction.commit();
        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(mOneFragment);
                fragmentTransaction.hide(mTwoFragment);
                fragmentTransaction.hide(mThreeFragment);
                switch (i) {
                    case R.id.radio1:
//                fragmentTransaction.show(mOneFragment);
                        fragmentTransaction.show( mOneFragment).commit();

                        break;
                    case R.id.radio2:
//                fragmentTransaction.show(mTwoFragment);

                        fragmentTransaction.show(mTwoFragment).commit();

                        break;
                    case R.id.radio3:
//                fragmentTransaction.show(mThreeFragment);
                        fragmentTransaction.show(mThreeFragment).commit();

                        break;


                }

            }
        });

    }

}
