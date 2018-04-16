package com.example.administrator.ex18;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.nio.charset.*;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bn1 = (Button) findViewById(R.id.Write);
        Button bn2 = (Button) findViewById(R.id.Read);
        final EditText editText1 = (EditText)findViewById(R.id.schoolno);
        final EditText editText2 = (EditText)findViewById(R.id.name);

        bn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OutputStream out = null;
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput("MyFileName.txt", MODE_PRIVATE);
                    //openFileOutput将数据存到具体指定文件，第一个参数是文件名，第二个参数是文件的操作方式,这个方法返回的是FileOutputStream对象
                    out = new BufferedOutputStream(fileOutputStream);
                    String content1 = editText1.getText().toString();
                    String content2 = editText2.getText().toString();
                    String all = "用户名:"+content1 +",密码:"+ content2;

                    try {
                        out.write(all.getBytes(Charset.forName("UTF-8")));
                        editText1.setText("");
                        editText2.setText("");
                    } finally {
                        if (out != null) out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InputStream in = null;
                BufferedReader reader = null;
                String c = "";
                try{
                    FileInputStream fileInputStream = openFileInput("MyFileName.txt");
                    reader = new BufferedReader(new InputStreamReader(fileInputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    boolean judge=false;
                    try{
                        while((c=reader.readLine())!=null){
                                stringBuilder.append(c);
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }finally{
                        if(reader != null){
                            in.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }
}
