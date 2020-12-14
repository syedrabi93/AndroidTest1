package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    TextView sName,sPrice,sHours,fPrice,fHours;
    Spinner course;
    CheckBox cb1,cb2;
    RadioButton rb1,rb2;
    Button add;
    int MaxHours = 0,TotalHours = 0,TotalPrice = 0;
    String courseselected;



    String courselist[] = {"Java", "Swift", "iOS", "Android", "Database"};
    ArrayList <String> AddedCourse = new ArrayList<>();
    ArrayList<courseDetails> coursedetail = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Bundle intent = getIntent().getExtras(); // this is just for example purpose
        String name = intent.getString("uname");
        filldata();
        sName = findViewById(R.id.studentname);
        sName.setText(name);


        course = findViewById(R.id.course);
        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,courselist);
        course.setAdapter(aa);
        course.setOnItemSelectedListener(this);



        sPrice = findViewById(R.id.amount);
        sHours = findViewById(R.id.hours);
        fPrice = findViewById(R.id.FAmount);
        fHours = findViewById(R.id.FHours);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);

        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);

    }

    public void filldata() {
        coursedetail.add(new courseDetails("Java", "6", "1300"));
        coursedetail.add(new courseDetails("Swift", "5", "1500"));
        coursedetail.add(new courseDetails("iOS", "5", "1350"));
        coursedetail.add(new courseDetails("Android", "7", "1400"));
        coursedetail.add(new courseDetails("Database", "4", "1000"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        courseselected = courselist[position];
        sHours.setText(getHours(courseselected));
        sPrice.setText(getPrice(courseselected));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getHours(String course){
        for (int i=0;i<coursedetail.size();i++){
            if (course.equals(coursedetail.get(i).cName))
                return coursedetail.get(i).getcHours();
        }
        return null;
    }
    public String getPrice(String course){
        for (int i=0;i<coursedetail.size();i++){
            if (course.equals(coursedetail.get(i).cName))
                return coursedetail.get(i).getcPrice();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        int ctime = Integer.parseInt(sHours.getText().toString());
        int cprice = Integer.parseInt(sPrice.getText().toString());

        if (v.getId() == R.id.rb1){
            MaxHours = 21;
        }
        if (v.getId() == R.id.rb2){
            MaxHours = 19;
        }
        if(v.getId() == R.id.add){

            boolean check = true;
            TotalHours = TotalHours + ctime;
            TotalPrice = TotalPrice + cprice;
            System.out.println(courseselected);
            /*if (AddedCourse.isEmpty()){
                AddedCourse.add(courseselected);
            }
            else {
                for (int i = 0; i < AddedCourse.size(); i++) {
                    System.out.println(AddedCourse.get(i));
                    if (!AddedCourse.get(i).equals(courseselected)) {
                        AddedCourse.add(courseselected);
                    } else {
                        Toast.makeText(CourseList.this, "Course Already selected",
                                Toast.LENGTH_LONG).show();
                        check = false;
                    }

                }
            }*/

            if (TotalHours > MaxHours && check == true) {
                Toast.makeText(CourseList.this, "Exceeds the Course Max Limit" + MaxHours + "hours",
                        Toast.LENGTH_LONG).show();
            }
            else if  (check == true) {
                fHours.setText(String.valueOf(TotalHours) + "hrs");
                fPrice.setText(String.valueOf(TotalPrice));
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.cb1){
            if(cb1.isChecked()){
            TotalPrice = TotalPrice + 1000;
            }
            else {
                TotalPrice = TotalPrice - 1000;
            }
        }
        if (buttonView.getId() == R.id.cb2){
            if(cb2.isChecked()){
                TotalPrice = TotalPrice + 700;
            }
            else {
                TotalPrice = TotalPrice - 700;
            }
        }

        //fHours.setText(String.valueOf(TotalHours) + "hrs");
        fPrice.setText(String.valueOf(TotalPrice));
    }
}