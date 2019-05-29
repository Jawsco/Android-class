package com.example.superrobotwars;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    FileOutputStream fos;
    DataOutputStream dos;
    FileInputStream fis;
    DataInputStream dis;

    String url;

    List<String> list = new ArrayList<>();

    List<String> selectedList = new ArrayList<>();

    long lastBackPress = 0;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        //웹뷰 기본 웹 사이트 설정 및 즐겨찾기 리스트 추가
        try
        {
            fis = openFileInput("url.dat");
            dis = new DataInputStream(fis);
            webView.loadUrl(dis.readUTF());
            dis.close();

            fis = openFileInput("bookmark.dat");
            dis = new DataInputStream(fis);
            while (true)
            {
                url = dis.readUTF();
                if(url.equals(null))
                {
                    break;
                }
                list.add(url);
            }
            dis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        final EditText editText = new EditText(this);
        switch (item.getItemId())
        {
            case R.id.move:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("웹페이지 이동");
                builder.setMessage("이동할 웹페이지 주소를 입력해주세요");
                builder.setView(editText);
                builder.setPositiveButton("이동", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        webView.loadUrl(editText.getText().toString());
                        try
                        {
                            fos = openFileOutput("url.dat", Context.MODE_PRIVATE);
                            dos = new DataOutputStream(fos);
                            dos.writeUTF(editText.getText().toString());
                            dos.flush();
                            dos.close();

                            fos = openFileOutput("bookmark.dat", Context.MODE_APPEND);
                            dos = new DataOutputStream(fos);
                            dos.writeUTF(editText.getText().toString());
                            dos.flush();
                            dos.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });

                builder.show();
                break;
            }
            case R.id.back:
            {
                webView.goBack();
                break;
            }
            case R.id.go:
            {
                webView.goForward();
                break;
            }
            case R.id.bookmark:
            {
                final CharSequence[] items = list.toArray(new String[list.size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("즐겨찾기");
                builder.setItems(items, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        webView.loadUrl(items[which].toString());
                    }
                });
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle("즐겨찾기 삭제");
                        builder1.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            {
                                if(isChecked)
                                {
                                    selectedList.add(items[which].toString());
                                }
                                else
                                {
                                    selectedList.remove(items[which].toString());
                                }
                            }
                        });
                        builder1.setPositiveButton("삭제", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //삭제시 list 삭제 및 bookmark.dst 파일에서 내용 삭제
                                for(String temp : selectedList)
                                {
                                    list.remove(temp);
                                }

                                try
                                {
                                    fos = openFileOutput("bookmark.dat", Context.MODE_PRIVATE);
                                    dos = new DataOutputStream(fos);
                                    for(String temp : list)
                                    {
                                        dos.writeUTF(temp);
                                    }
                                    dos.flush();
                                    dos.close();
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                            }
                        });

                        builder1.setNegativeButton("취소", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });

                        builder1.show();
                    }
                });
                builder.show();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(System.currentTimeMillis() <= lastBackPress + 1500)
        {
            finish();
            return;
        }
        lastBackPress = System.currentTimeMillis();
        Toast.makeText(MainActivity.this, "뒤로가기 버튼을 한 번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
    }
}

