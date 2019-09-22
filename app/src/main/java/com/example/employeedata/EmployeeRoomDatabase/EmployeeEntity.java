package com.example.employeedata.EmployeeRoomDatabase;

import android.content.ContentValues;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EmployeeData")
public class EmployeeEntity {

    /** The name of the name column. */
    public static final String COLUMN_NAME = "name";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    //this field(primary key) should not be private or static
    public long id;

    @ColumnInfo(name = COLUMN_NAME)
     String name ;

    @ColumnInfo(name = "department")
     String department ;

    @ColumnInfo(name = "age")
     int age ;

    @ColumnInfo(name = "salary")
     float salary ;


    public static EmployeeEntity fromContentValues(ContentValues values){

       EmployeeEntity employeeEntity = new EmployeeEntity();
        //id = values.getA("id");
        employeeEntity.name = values.getAsString("name");
        employeeEntity.department = values.getAsString("dep");
        employeeEntity.age = values.getAsInteger("age");
        employeeEntity.salary = values.getAsFloat("salary");
        return employeeEntity;
    }

}
