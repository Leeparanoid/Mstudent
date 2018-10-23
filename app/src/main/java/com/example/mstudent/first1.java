package com.example.mstudent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class first1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first1);

        Button btdl=(Button)findViewById(R.id.btdl);
        btdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = ((EditText) findViewById(R.id.account)).getText().toString();
                String pwd = ((EditText) findViewById(R.id.password)).getText().toString();
                if ("2040".equals(user)&&"lsz".equals(pwd)){             //预设账号admin，密码admin

                    Intent intent=new Intent(first1.this,MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(first1.this, "请输入正确的账号密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
