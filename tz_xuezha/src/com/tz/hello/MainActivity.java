package com.tz.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.hello.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    Button conLog;
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        conLog = (Button) findViewById(R.id.conLog);//ͨ��id�ҵ��İ�ť
        MyLog.isDebug = true;//debugģʽ����
////        System.out.println("view loaded");
////        Log.i("INFO", "view loaded");
//        Log.println(Log.INFO, "INFO", "view loaded");
//        MyLog.i("INFO", "hello debug");
       conLog.setOnClickListener(this);
    }
	public void onClick(View v) {
		//��ť��������ˣ��ռ��ռ���־
		try {
			readLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Danny QQ 858030348  
	 * @throws IOException 
	 * 
	 */
	private void readLog() throws IOException {
		MyLog.i("INFO", "start connectLog");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//�ռ�һ����־ֹͣ
		cmdLine.add("-s");//����
		cmdLine.add("INFO");
      //  cmdLine.add("ERROR") //�ռ�������Ϣ
	  // cmdLine.add("DEBUG")  //�ռ�debug��Ϣ
	//	cmdLine.add("WARN")   //�ռ�������Ϣ
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//��ȡִ��������������
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);//װ����ģʽ
		BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);//ֱ�Ӷ��ַ���
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			sb.append(str);//ÿ��һ��ƴ�ӵ�sb����ȥ
			sb.append("\n");//ÿһ��һ�����з�
		}
		//��˾
		Toast.makeText(this, sb.toString(), 1000).show();
	}
}