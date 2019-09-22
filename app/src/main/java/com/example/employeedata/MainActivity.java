package com.example.employeedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText emp_name, emp_age, emp_dep, emp_salary;
    public Button create, delete, slice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emp_name = findViewById(R.id.et_emp_name);
        emp_age = findViewById(R.id.et_emp_age);
        emp_dep = findViewById(R.id.et_emp_dep);
        emp_salary = findViewById(R.id.et_salary);
      //  empData = findViewById(R.id.emp_rec_data);

        //Buttons
        create = findViewById(R.id.bt_create);
        delete = findViewById(R.id.bt_delete);
        slice = findViewById(R.id.bt_slice);

        create.setOnClickListener(this);
        delete.setOnClickListener(this);
        slice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_create :
                final Uri uri =Uri.parse("content://com.example.employeedata/employee_database") ;
                //create the employee record
                //  Toast.makeText(mcontext, "create employee record", Toast.LENGTH_SHORT).show();
              //  EmployeeContentProvider ep = new EmployeeContentProvider();
                final ContentValues cv = new ContentValues();
                cv.put("age",emp_age.getText().toString());
                cv.put("name",emp_name.getText().toString());
                cv.put("dep",emp_dep.getText().toString());
                cv.put("salary",emp_salary.getText().toString());
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getContentResolver().insert(uri,cv);
                    }
                });
                t.start();

                //  employeeViewModel.insert(new EmployeeEntity(emp_id.getText().toString(),emp_name.getText().toString(),emp_dep.getText().toString()));
                break;
            case R.id.bt_delete :
                final Uri uri2 =Uri.parse("content://com.example.employeedata/employee_database") ;
               // final ContentValues cv2 = new ContentValues();
               // cv2.put("name",emp_name.getText().toString());

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getContentResolver().delete(uri2,emp_name.getText().toString(),null);
                    }
                });
                t2.start();
            case R.id.bt_slice :

                final Uri uri3 =Uri.parse("content://com.example.employeedata/employee_database/getAllEmpByLowSalary") ;
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getContentResolver().query(uri3,null,emp_name.getText().toString(),null,null);
                    }
                });
                t3.start();
                break;

        }

    }
}
