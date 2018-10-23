package com.example.mstudent;

/**
 * Created by 10753 on 2018/10/23.
 */
public class Student {
    int Sid=-1;
    String Sname;
    String Ssex;
    String Sclass;
    String Stel;
    String Saddress;

    public  String toString(){
        String result="";
        result +="ID："+this.Sid;
        result +="姓名："+this.Sname;
        result +="性别："+this.Ssex;
        result +="班级："+this.Sclass;
        result +="电话："+this.Stel;
        result +="地址："+this.Saddress;

        return result;
    }

}
