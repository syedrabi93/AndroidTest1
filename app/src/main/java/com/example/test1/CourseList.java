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
        //to display welcome Message with the logged in user name read from the main activity.
        String name = intent.getString("uname");
        filldata();
        sName = findViewById(R.id.studentname);
        sName.setText("welcome "+ name);

        //initialise and add list of courses to the array list
        course = findViewById(R.id.course);
        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,courselist);
        course.setAdapter(aa);
        //initialise the listerner
        course.setOnItemSelectedListener(this);


        //Initialise text boxes
        sPrice = findViewById(R.id.amount);
        sHours = findViewById(R.id.hours);
        fPrice = findViewById(R.id.FAmount);
        fHours = findViewById(R.id.FHours);

        //initialise radio buttons and set listereners
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);

        //add button initialisation and set listener
        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        //checkbox initialisation and set listener
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);

    }

    //fill the arrayblist of courselist object
    public void filldata() {
        coursedetail.add(new courseDetails("Java", "6", "1300"));
        coursedetail.add(new courseDetails("Swift", "5", "1500"));
        coursedetail.add(new courseDetails("iOS", "5", "1350"));
        coursedetail.add(new courseDetails("Android", "7", "1400"));
        coursedetail.add(new courseDetails("Database", "4", "1000"));
    }

    //on selecting the course in spinner, displaying corresponding hours and price in the text box
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        courseselected = courselist[position];
        sHours.setText(getHours(courseselected));
        sPrice.setText(getPrice(courseselected));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //function to get hours of selected item
    public String getHours(String course){
        for (int i=0;i<coursedetail.size();i++){
            if (course.equals(coursedetail.get(i).cName))
                return coursedetail.get(i).getcHours();
        }
        return null;
    }

    //function to get price of selected item
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

        //if graduated radio button is selected set the max hours of course to 21
        if (v.getId() == R.id.rb1){
            MaxHours = 21;
        }
        //if non graduate elegible max course hours is 19 hours
        if (v.getId() == R.id.rb2){
            MaxHours = 19;
        }

        //on clicking add button
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
            //check if the total hours excceds max hours and toast
            if (TotalHours > MaxHours && check == true) {
                Toast.makeText(CourseList.this, "Exceeds the Course Max Limit" + MaxHours + "hours",
                        Toast.LENGTH_LONG).show();
            }
            //else add price and hours and display in final text box
            else if  (check == true) {
                fHours.setText(String.valueOf(TotalHours) + "hrs");
                fPrice.setText(String.valueOf(TotalPrice));
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //if accomodation check box is selected add 1000$ to total price
        if (buttonView.getId() == R.id.cb1){
            if(cb1.isChecked()){
            TotalPrice = TotalPrice + 1000;
            }
            else {
                TotalPrice = TotalPrice - 1000;
            }
        }
        //if medical is selected add 700$
        if (buttonView.getId() == R.id.cb2){
            if(cb2.isChecked()){
                TotalPrice = TotalPrice + 700;
            }
            else {
                TotalPrice = TotalPrice - 700;
            }
        }

        //fHours.setText(String.valueOf(TotalHours) + "hrs");
        //display final course fee
        fPrice.setText(String.valueOf(TotalPrice));
    }
}