package com.example.asynctask

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var time = System.currentTimeMillis()
            textView.text= "버튼 클릭 : ${time}"
        }
        var sync = AsyncTaskClass()
        sync.execute(10.0, 20.0)
    }

    inner class AsyncTaskClass : AsyncTask<Double, Double, String>()
    {
        override fun onPreExecute()
        {
            super.onPreExecute()
            textView2.text = "AsynceTask 가동"
        }

        override fun doInBackground(vararg params: Double?): String
        {
            var a1 = params[0]!!
            var a2 = params[1]!!
            var past = System.currentTimeMillis()
            for(idx in 0..9)
            {
                SystemClock.sleep(1000)
                a1 += 1.1
                a2 += 2.2
                Log.d("test1", "${idx} : ${a1}, ${a2}")
                var time = SystemClock.currentThreadTimeMillis()
                publishProgress(a1, a2)
            }
            return "${past}로 부터 시작했었습니다"
        }

        override fun onProgressUpdate(vararg values: Double?)
        {
            super.onProgressUpdate(*values)
            textView2.text = "a1 : ${values[0]}\na2 : ${values[1]}"
        }

        override fun onPostExecute(result: String?)
        {
            super.onPostExecute(result)
            textView2.text = result
        }
    }
}
