package com.igame.devicemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStart, mBtnLock, mBtnWipeData, mBtnUninstall;
    private ComponentName mDeviceAdminSample;
    private DevicePolicyManager mDPM;

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
                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    mDPM.lockNow();
                    mDPM.resetPassword("", 0);
                }else{
                    Toast.makeText(this, "请先激活", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_wipe_data:
                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    mDPM.wipeData(0);
                }else{
                    Toast.makeText(this, "请先激活", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_uninstall: // todo -> 一键卸载
                break;
        }
    }

    private void initData() {
        mDeviceAdminSample = new ComponentName(this, DeviceAdmin.class);
        /**
         * 实例化设备的管理者对象
         */
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
    }
}