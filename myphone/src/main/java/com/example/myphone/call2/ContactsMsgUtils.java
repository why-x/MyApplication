package com.example.myphone.call2;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyApplication
 * @date 2019/6/10 14:39
 */
public class ContactsMsgUtils {
    public List<CallLogInfo> getCallLog(Context context) {
        List<CallLogInfo> infos = new ArrayList<CallLogInfo>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        String[] projection = new String[] { CallLog.Calls.NUMBER, CallLog.Calls.DATE,
                CallLog.Calls.TYPE };
        Cursor cursor = cr.query(uri, projection, null, null, null);
        while (cursor.moveToNext()) {
            String number = cursor.getString(0);
            long date = cursor.getLong(1);
            int type = cursor.getInt(2);
            infos.add(new CallLogInfo(number, date, type));
        }
        cursor.close();
        return infos;
    }

}