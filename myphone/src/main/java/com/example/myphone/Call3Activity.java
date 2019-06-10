package com.example.myphone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myphone.call2.CallLogInfo;
import com.example.myphone.call2.ContactsMsgUtils;

import java.util.Collections;
import java.util.List;

/**
 * 获取到当前最近的电话号码
 */
public class Call3Activity extends AppCompatActivity {

    private TextView mMynumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call3);
        ContactsMsgUtils contactsMsgUtils = new ContactsMsgUtils();
        List<CallLogInfo> infos = contactsMsgUtils.getCallLog(this);

        Collections.reverse(infos);//倒序显示 存入adapter
        String number = infos.get(0).number;//0 第一个
        mMynumber = findViewById(R.id.mynumber);
        mMynumber.setText(number);
    }
}
