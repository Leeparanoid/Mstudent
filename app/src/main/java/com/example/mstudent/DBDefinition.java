package com.example.mstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

/**
 * Created by Leeparanoid on 2018/10/19.
 */
public class DBDefinition {
    private static final String DB_NAME="student.db";
    private static final String DB_TABLE="studentinfot";
    private static final int DB_VERSION=1;
    private static final String KEY_ID="Sid";
    private static final String KEY_NAME="Sname";
    private static final String KEY_SEX="Ssex";
    private static final String KEY_CLASS="Sclass";
    private static final String KEY_TEL="Stel";
    private static final String KEY_ADDRESS="Saddress";

    private SQLiteDatabase db;
    private Context context;
    private DBOpenHelper dbOpenHelper;

    public DBDefinition(Context _context){
        context=_context;
    }

    //数据库打开
    public void open(){
        dbOpenHelper=new DBOpenHelper(context,DB_NAME,null,DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }
        catch (SQLiteException e){
            db=dbOpenHelper.getReadableDatabase();
        }
    }

    //db关闭

    public  void close(){
        db.close();
    }

    //db插入数据

    public long insert(Student student){

        //将学生添加的信息属性写入到ContenValues对象

        ContentValues newValues=new ContentValues();
        newValues.put(KEY_ID,student.Sid);
        newValues.put(KEY_NAME,student.Sname);
        newValues.put(KEY_SEX,student.Ssex);
        newValues.put(KEY_CLASS,student.Sclass);
        newValues.put(KEY_TEL,student.Stel);
        newValues.put(KEY_ADDRESS,student.Saddress);
        return db.insert(DB_TABLE,null,newValues);
    }

    public long upadateData(long Sid ,Student student){
        //更新数据
        ContentValues newValues=new ContentValues();
        newValues.put(KEY_ID,student.Sid);
        newValues.put(KEY_NAME,student.Sname);
        newValues.put(KEY_SEX,student.Ssex);
        newValues.put(KEY_CLASS,student.Sclass);
        newValues.put(KEY_TEL,student.Stel);
        newValues.put(KEY_ADDRESS,student.Saddress);
        return db.update(DB_TABLE,newValues,KEY_ID+"="+Sid,null);}


    //查询指定ID信息

    public Student[]
    queryData(long Sid){
        Cursor results=db.query(
                DB_TABLE,new String[]{
                        KEY_ID,KEY_NAME,KEY_SEX,KEY_CLASS,KEY_TEL,KEY_ADDRESS
                },KEY_ID+"="+Sid,null,null,null,null
        );
        return ConcertToStudent(results);
    }

    //显示查询数据

    public Student[]ConcertToStudent(Cursor cursor) {
        int resultCounts = cursor.getCount();                                         //获取查询数据的记录数
        if (resultCounts == 0 || !cursor.moveToFirst()) {                                //指针移动到第一条数据不成功
            return null;
        }
        Student[] student = new Student[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            student[i]=new Student();
            student[i].Sid=cursor.getInt(0);                                        //获取每行数据的ID
            student[i].Sname=cursor.getString(cursor.getColumnIndex(KEY_NAME));    //获取每行数据的姓名
            student[i].Ssex=cursor.getString(cursor.getColumnIndex(KEY_SEX));       //获取每行数据的性别
            student[i].Sclass=cursor.getString(cursor.getColumnIndex(KEY_CLASS));       //获取每行数据的班级
            student[i].Stel=cursor.getString(cursor.getColumnIndex(KEY_TEL));       //获取每行数据的电话
            student[i].Saddress=cursor.getString(cursor.getColumnIndex(KEY_ADDRESS));       //获取每行数据的地址

            cursor.moveToNext();
        }
        return student;
    }

    public class DBOpenHelper extends SQLiteOpenHelper{

        public DBOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
            super(context, name, factory, version);
        }

        public  void onCreate(SQLiteDatabase db){
            db.execSQL("create table "+ DB_TABLE + "(" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " varchar," + KEY_SEX + " varchar," + KEY_CLASS + " varchar, " + KEY_TEL +" integer, " + KEY_ADDRESS + " varchar)");
        }

        public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){        //数据库升级
            db.execSQL("alter table student add  column ranking integer");
            onCreate(db);
        }
    }

}
