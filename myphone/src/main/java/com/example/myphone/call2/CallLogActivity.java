package com.example.myphone.call2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myphone.R;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class CallLogActivity extends Activity {
    private ListView lv;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        lv = (ListView) findViewById(R.id.callView);
        ContactsMsgUtils contactsMsgUtils = new ContactsMsgUtils();
        List<CallLogInfo> infos = contactsMsgUtils.getCallLog(this);

        Collections.reverse(infos);//倒序显示 存入adapter

        adapter = new MyAdapter(infos);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                CallLogInfo info = (CallLogInfo) adapter.getItem(arg2);
                final String number = info.number;
                String[] items = new String[] { "复制号码到拨号盘, 拨号, 发送短信 "};
                new AlertDialog.Builder(CallLogActivity.this).setTitle("操作")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    case 0:
                                        startActivity(new Intent(
                                                Intent.ACTION_DIAL, Uri
                                                .parse("tel:" + number)));
                                        break;
                                    case 1:
                                        startActivity(new Intent(
                                                Intent.ACTION_CALL, Uri
                                                .parse("tel:" + number)));
                                        break;
                                    case 2:
                                        startActivity(new Intent(
                                                Intent.ACTION_SENDTO, Uri
                                                .parse("sms:" + number)));
                                        break;

                                    default:
                                        break;
                                }

                            }
                        }).show();

                return false;
            }
        });

    }
    private class MyAdapter extends BaseAdapter {
        private List<CallLogInfo> infos;
        private LayoutInflater inflater;

        public MyAdapter(List<CallLogInfo> infos) {
            super();
            this.infos = infos;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {

            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.slv_impower_item, null);
            TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
            TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
            TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
            CallLogInfo info = infos.get(0);
            tv_number.setText(info.number);
            Toast.makeText(CallLogActivity.this,info.number+"--------",Toast.LENGTH_LONG).show();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss");
            String dateStr = format.format(info.date);
            tv_date.setText(dateStr);
            String typeStr = null;
            int color = 0;
            switch (info.type) {
                case CallLog.Calls.INCOMING_TYPE:
                    typeStr = "来电";
                    color = Color.BLUE;

                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    typeStr = "去电";
                    color = Color.GREEN;

                    break;
                case CallLog.Calls.MISSED_TYPE:
                    typeStr = "未接";
                    color = Color.RED;

                    break;

                default:
                    break;
            }
            tv_type.setText(typeStr);
            tv_type.setTextColor(color);
            return view;
        }

    }
}