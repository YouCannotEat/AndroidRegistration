package com.example.myapplication;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        String info = intent.getStringExtra("info");
//        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("个人信息确认");
        dialogBuilder.setMessage(info);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("确认",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBAdapter dbAdapter = new DBAdapter(this);
                dbAdapter.open();
                dbAdapter.insert(Student.getStudent());
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialogBuilder.show();
    }
}
