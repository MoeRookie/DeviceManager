package com.igame.devicemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStart, mBtnLock, mBtnWipeData, mBtnUninstall;
    private ComponentName mDeviceAdminSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initData();
    }

    private void initView() {
        mBtnStart = findViewById(R.id.btn_start);
        mBtnLock = findViewById(R.id.btn_lock);
        mBtnWipeData = findViewById(R.id.btn_wipe_data);
        mBtnUninstall = findViewById(R.id.btn_uninstall);
    }

    private void setListener() {
        mBtnStart.setOnClickListener(this);
        mBtnLock.setOnClickListener(this);
        mBtnWipeData.setOnClickListener(this);
        mBtnUninstall.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "超级管理员");
                startActivity(intent);
                break;
            case R.id.btn_lock:
                break;
            case R.id.btn_wipe_data:
                break;
            case R.id.btn_uninstall:
                break;
        }
    }

    private void initData() {
        mDeviceAdminSample = new ComponentName(this, DeviceAdmin.class);
    }
}