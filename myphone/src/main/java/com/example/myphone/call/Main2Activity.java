package com.example.myphone.call;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myphone.R;

import java.util.List;

public class Main2Activity extends BaseActivity {
    private Main2Activity mActivity;
    private int callLogRequestCode = 1;
    TextView tvContent;
    Button jilu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvContent=findViewById(R.id.tv_content);
        mActivity = this;
       jilu = findViewById(R.id.btn_get_call_log);
       jilu.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.M)
           @Override
           public void onClick(View v) {
               tvContent.setText("");

               boolean b = checkPermission(Manifest.permission.READ_CALL_LOG);
               if (b) {
                   readCallLog();

               } else {
                   requestPerssion(new String[]{Manifest.permission.READ_CALL_LOG}, callLogRequestCode);
               }
           }
       });



    }

    /**
     * 读取通话记录
     */
    private void readCallLog() {
        List<CallLogInfoBean> callLogInfos = CallLogUtils.getCallLogInfos(mActivity, 0);
        tvContent.setText(callLogInfos.toString());
    }
}
