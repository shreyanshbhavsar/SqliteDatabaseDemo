package shreyansh.bhavsar.sqlitestudentdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shreyansh.bhavsar on 8/26/2017.
 */

public class StudentDBHelper {

    public final String DATABASE_NAME = "students.db";
    public final int DATABASE_VERSION = 1;
    public final String TABLE_NAME = "student";
    public final String COLUMN_ID = "id";
    public final String COLUMN_NAME = "name";
    public final String COLUMN_CITY = "city";
    public final String COLUMN_AGE = "age";


    public final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "+COLUMN_ID+" integer auto_increment,"+COLUMN_NAME+" text,"+COLUMN_CITY+" text,"+COLUMN_AGE+" integer);";


    StudentOpenHelper openHelper;
    Context context;
    SQLiteDatabase db;

    public StudentDBHelper(Context context) {
        this.context = context;
        openHelper = new StudentOpenHelper(context);
        db = openHelper.getWritableDatabase();
    }

    // add new student to table

    void addStudent(Student student)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,student.getName());
        values.put(COLUMN_CITY,student.getCity());
        values.put(COLUMN_AGE,student.getAge());

        long response = db.insert(TABLE_NAME,null,values);
        if(response != -1)
        {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    // Get All Students

    List<Student> getAllStudents()
    {

        List<Student> students = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        if(cursor!=null && cursor.moveToFirst())
        {
            do {

                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));

                students.add(new Student(name,city,age));


            }
            while (cursor.moveToNext());
        }

        return students;

    }

       // Get City by name

    String getCity(String name) {

        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_NAME + "=?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {

            return cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
        } else {
            return null;
        }


    }



















    // Inner Class
    class StudentOpenHelper extends SQLiteOpenHelper
    {


        public StudentOpenHelper(Context context) {
            super(context,DATABASE_NAME,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
