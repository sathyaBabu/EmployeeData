package com.example.employeedata.EmployeeRoomDatabase;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.employeedata.EmployeeRoomDatabase.EmployeeEntity;

@Dao
public interface EmployeeDao {

    //insert a new employee record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertEmployee(EmployeeEntity employeeEntity);


    //delete the employee record by name
    @Query("DELETE FROM EmployeeData WHERE name = :name")
    int deleteEmployeeByName(String name);

    //get all the employees data
    @Query("SELECT * FROM EmployeeData")
    Cursor getEmployeeData();


    //get the employee data by his name//uri code =1
    @Query("SELECT * FROM EmployeeData WHERE name = :name")
    Cursor getEmployeeById(String name);


    //returns the total number of employees of that department//uri code =2
    @Query("SELECT * FROM EmployeeData WHERE department = :dep")
    Cursor getEmployeeByDepartment(String dep);

    //returns number of employees in the database
    @Query("SELECT COUNT(*) FROM EmployeeData")
    int count();

    //return all the employees greater than the age//uri code =3
    @Query("SELECT * FROM EmployeeData WHERE age > :age")
    Cursor getMiddleAgedEmployees(int age);

    //return all the employees with salary higher than given salary//uri code =4
    @Query("SELECT * FROM EmployeeData WHERE salary > :salary")
    Cursor getHighSalaryBandEmployees(float salary);

    //return all the employyes with salary lower than the given salary//uri code =5
    @Query("SELECT * FROM EmployeeData WHERE salary < :salary")
    Cursor geLowSalaryBandEmployees(float salary);


}
