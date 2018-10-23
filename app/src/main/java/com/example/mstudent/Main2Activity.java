package com.example.mstudent;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends ActionBarActivity {
    private EditText idText;
    private EditText nameText;
    private EditText sexText;
    private EditText classText;
    private EditText telText;
    private EditText addressText;
    private Button btbc;
    private Button btcancle;
    private DBDefinition dboperation;
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        idText=(EditText)findViewById(R.id.editid);
        nameText=(EditText)findViewById(R.id.editname);
        sexText=(EditText)findViewById(R.id.editsex);
        classText=(EditText)findViewById(R.id.editclass);
        telText=(EditText)findViewById(R.id.edittel);
        addressText=(EditText)findViewById(R.id.editaddress);


        //找按钮

        Button btbc=(Button)findViewById(R.id.btbc);
        Button btcancle=(Button)findViewById(R.id.btcancle);

        //创建并打开数据库

        dboperation= new DBDefinition(this);
        dboperation.open();

        btbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.Sid = Integer.parseInt(idText.getText().toString());
                student.Sname = nameText.getText().toString();
                student.Ssex = sexText.getText().toString();
                student.Sclass = classText.getText().toString();
                student.Stel = telText.getText().toString();
                student.Saddress = addressText.getText().toString();
                long column = dboperation.insert(student);
                if (column == -1) {
                    Toast.makeText(context, "插入数据失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "插入数据成功", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
