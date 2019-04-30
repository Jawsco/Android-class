package com.example.smarthome.BlueTooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Bluetooth
{
    Context context;
    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터
    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋
    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스
    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓
    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림
    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림
    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드
    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼
    private int readBufferPosition; // 버퍼 내 문자 저장 위치

    private TextView textViewReceive; // 수신 된 데이터를 표시하기 위한 텍스트 뷰
    private EditText editTextSend; // 송신 할 데이터를 작성하기 위한 에딧 텍스트
    private Button buttonSend; // 송신하기 위한 버튼
    int pariedDeviceCount;

    public Bluetooth(Context context)
    {
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean CheckUseBluetooth()
    {

        if (bluetoothAdapter == null) //비지원
        {
            Toast.makeText(context, "블루투스가 지원되지 않습니다.", Toast.LENGTH_LONG).show();
            SystemClock.sleep(1000);
            return false;
        }
        else //지원
        {
            return true;
        }
    }

    public boolean CheckBluetoothEnable()
    {
        if (bluetoothAdapter.isEnabled()) //활성화
        {
            return true;
        }
        else //비활성화
        {
            return false;
        }
    }

    public void selectBluetoothDevice()
    {
        devices = bluetoothAdapter.getBondedDevices();// 이미 페어링 되어있는 블루투스 기기를 찾습니다
        pariedDeviceCount = devices.size(); // 페어링 된 디바이스의 크기를 저장

        if (pariedDeviceCount == 0)// 페어링 되어있는 장치가 없는 경우
        {
            // 페어링을 하기위한 함수 호출
        }

        else // 페어링 되어있는 장치가 있는 경우
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context); // 디바이스를 선택하기 위한 다이얼로그 생성
            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");
            List<String> list = new ArrayList<>();// 페어링 된 각각의 디바이스의 이름과 주소를 저장
            // 모든 디바이스의 이름을 리스트에 추가
            for (BluetoothDevice bluetoothDevice : devices)
            {
                list.add(bluetoothDevice.getName());
            }
            list.add("취소");
            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);// List를 CharSequence 배열로 변경
            list.toArray(new CharSequence[list.size()]);
            // 해당 아이템을 눌렀을 때 호출 되는 이벤트 리스너
            builder.setItems(charSequences, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    // 해당 디바이스와 연결하는 함수 호출
                    connectDevice(charSequences[which].toString());
                }
            });

            // 뒤로가기 버튼 누를 때 창이 안닫히도록 설정
            builder.setCancelable(false);
            // 다이얼로그 생성
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void connectDevice(String deviceName)
    {
        // 페어링 된 디바이스들을 모두 탐색
        for (BluetoothDevice tempDevice : devices)
        {
            // 사용자가 선택한 이름과 같은 디바이스로 설정하고 반복문 종료
            if (deviceName.equals(tempDevice.getName()))
            {
                bluetoothDevice = tempDevice;
                break;
            }
        }
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // UUID 생성
        // Rfcomm 채널을 통해 블루투스 디바이스와 통신하는 소켓 생성
        try
        {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
            // 데이터 송,수신 스트림을 얻어옵니다.
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
            // 데이터 수신 함수 호출
            receiveData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void receiveData()
    {
        final Handler handler = new Handler();
        readBufferPosition = 0;
        readBuffer = new byte[1024];// 데이터를 수신하기 위한 버퍼를 생성
        workerThread = new Thread(new Runnable()// 데이터를 수신하기 위한 쓰레드 생성
        {
            @Override
            public void run()
            {
                while (!(Thread.currentThread().isInterrupted()))
                {
                    try
                    {
                        int byteAvailable = inputStream.available();// 데이터를 수신했는지 확인합니다.
                        if (byteAvailable > 0)// 데이터가 수신 된 경우
                        {
                            byte[] bytes = new byte[byteAvailable];// 입력 스트림에서 바이트 단위로 읽어 옵니다.
                            inputStream.read(bytes);
                            for (int i = 0; i < byteAvailable; i++)// 입력 스트림 바이트를 한 바이트씩 읽어 옵니다.
                            {
                                byte tempByte = bytes[i];
                                if (tempByte == '\n') // 개행문자를 기준으로 받음(한줄)
                                {

                                    byte[] encodedBytes = new byte[readBufferPosition];// readBuffer 배열을 encodedBytes로 복사
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String text = new String(encodedBytes, "US-ASCII");  // 인코딩 된 바이트 배열을 문자열로 변환
                                    readBufferPosition = 0;
                                    handler.post(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            textViewReceive.append(text + "\n");// 텍스트 뷰에 출력
                                        }
                                    });
                                }
                                else// 개행 문자가 아닐 경우
                                {
                                    readBuffer[readBufferPosition++] = tempByte;
                                }
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        Thread.sleep(1000);// 1초마다 받아옴
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        workerThread.start();
    }
}
