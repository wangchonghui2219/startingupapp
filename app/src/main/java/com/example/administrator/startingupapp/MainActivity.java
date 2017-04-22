package com.example.administrator.startingupapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String brand = android.os.Build.BRAND;
        Toast.makeText(this, brand, Toast.LENGTH_SHORT).show();
       showWindow();
    }

    /**
     *
     */
    private void showWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("是否开启开机自启动")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selfStartManagerSettingIntent(MainActivity.this);
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void selfStartManagerSettingIntent(Context context){

//        String system = EquipmentSystemUtils.getSystem();
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName componentName = new ComponentName("com.huawei.systemmanager","com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        intent.setComponent(componentName);
        try{
            context.startActivity(intent);
        }catch (Exception e){//抛出异常就直接打开设置页面
            intent=new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }

    }
//    private boolean checkWriteExternalPermission()
//    {
//
//        String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
//        int res =this.checkCallingOrSelfPermission(permission);
//        return (res == PackageManager.RECEIVE_BOOT_COMPLETED);
//    }
}
