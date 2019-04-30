package com.example.smarthome.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smarthome.BlueTooth.Bluetooth;
import com.example.smarthome.LivingRoom.LivingRoom;
import com.example.smarthome.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    Button livingRoom;

    String[] permissionList =
            {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN};

    private static final int REQUEST_ENABLE_BT = 10;

    Bluetooth bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        livingRoom = findViewById(R.id.button);

        checkPermission();

        bluetooth = new Bluetooth(MainActivity.this);

        if(bluetooth.CheckUseBluetooth()) //블루투스 사용확인
        {
            if(bluetooth.CheckBluetoothEnable())
            {
                bluetooth.selectBluetoothDevice();
            }
            else //블루투스 비활성화 일때
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        else //블루투스 사용불가
        {
            finish();
        }

        livingRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, LivingRoom.class);
                startActivity(intent);
            }
        });
    }

    public void checkPermission()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return;
        }
        for (String permission : permissionList)
        {
            int chk = checkCallingOrSelfPermission(permission);
            if (chk == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions(permissionList, 0);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_ENABLE_BT :
                if(requestCode == RESULT_OK) // '사용'을 눌렀을 때
                {
                    bluetooth.selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
                }
                break;
        }
    }
}
