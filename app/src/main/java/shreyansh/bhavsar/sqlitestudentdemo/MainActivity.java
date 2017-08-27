package shreyansh.bhavsar.sqlitestudentdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText nameet,ageet,cityet;
    Button addbtn,getbtn,changeBtn;
    StudentDBHelper dbHelper;
    List<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameet = (EditText)findViewById(R.id.nameet);
        cityet = (EditText)findViewById(R.id.cityet);
        ageet = (EditText)findViewById(R.id.ageet);

        addbtn = (Button)findViewById(R.id.addbtn);
        getbtn = (Button)findViewById(R.id.getbtn);
        changeBtn = (Button)findViewById(R.id.changebtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String name  = nameet.getText().toString();
                String city = cityet.getText().toString();
                int age = Integer.parseInt(ageet.getText().toString());


                dbHelper.addStudent(new Student(name,city,age));

            }
        });

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                students = dbHelper.getAllStudents();

                for(Student s: students)
                {

                    Toast.makeText(MainActivity.this,s.getName()+"  "+s.getCity()+"  "+s.getAge(),Toast.LENGTH_SHORT ).show();
                }

            }
        });

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = dbHelper.getCity("Kohli");
                Toast.makeText(MainActivity.this,city,Toast.LENGTH_SHORT).show();


            }
        });


        dbHelper = new StudentDBHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






    }


}
