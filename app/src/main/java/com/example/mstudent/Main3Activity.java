package com.example.mstudent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends ActionBarActivity {
    private EditText editid1;
    private Button btquerry1;
    private Button btcancle1;
    private DBDefinition dboperation;
    private Context context=this;
    private TextView tvmsg;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btquerry1=(Button)findViewById(R.id.btquery1);
        Button btcancle1=(Button)findViewById(R.id.btcancle1);
        editid1=(EditText)findViewById(R.id.editid1);
        tvmsg=(TextView)findViewById(R.id.tvmsg);

        dboperation =new DBDefinition(this);
        dboperation.open();

        //查询指定id

        btquerry1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Sid=Integer.parseInt(editid1.getText().toString());
                Student[] students=dboperation.queryData(Sid);
                tvmsg.setText(students[0].toString());
            }
        });


        btcancle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
