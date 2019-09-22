package com.example.employeedata;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.employeedata.EmployeeRoomDatabase.EmployeeDao;
import com.example.employeedata.EmployeeRoomDatabase.EmployeeEntity;
import com.example.employeedata.EmployeeRoomDatabase.EmployeeRoomDatabase;

public class EmployeeContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.employeedata";
    public static String DATABASE_NAME = "employee_database";
    public static final String contentUri ="content://"+ AUTHORITY + '/' + DATABASE_NAME + "/";
    public Context mcontext;
    public String s, s2;
    public static int getEmpByName = 1, getAllEmpByDep = 2, getAllEmpOlderThanAge = 3,
            getAllEmpByHighSalary = 4, getAllEmpByLowSalary =5; //this has to be static to be used in static "MATCHER" method below.
    public EmployeeContentProvider() {
    }
      /** The URI matcher. */

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        MATCHER.addURI(AUTHORITY, DATABASE_NAME + "/*", getEmpByName);
        MATCHER.addURI(AUTHORITY, DATABASE_NAME + "/*", getAllEmpByDep);
        MATCHER.addURI(AUTHORITY, DATABASE_NAME + "/*", getAllEmpOlderThanAge);
        MATCHER.addURI(AUTHORITY, DATABASE_NAME + "/*", getAllEmpByHighSalary);
        MATCHER.addURI(AUTHORITY, DATABASE_NAME + "/*", getAllEmpByLowSalary);
      //  MATCHER.addURI(AUTHORITY, DATABASE_NAME, delete);
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
       // throw new UnsupportedOperationException("Not yet implemented");
        if (mcontext == null){
            Log.d("myapp","context is null");
        } else {
            // TODO: Implement this to handle requests to insert a new row.
            // throw new UnsupportedOperationException("Not yet implemented");
            EmployeeDao employeeDao = EmployeeRoomDatabase.getDatabase(getContext()).employeeDao();
            int count = employeeDao.deleteEmployeeByName(selection);
            Log.d("myapp","record deleted aand remaining employees" +count);
            mcontext.getContentResolver().notifyChange(uri, null);

            return count;
        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        // throw new UnsupportedOperationException("Not yet implemented");

        final int code = MATCHER.match(uri);

        switch (code) {
            case 1:
                return "Content://" + AUTHORITY + "/" + DATABASE_NAME + "/getEmpByName";

            case 2:
                return "Content://" + AUTHORITY + "/" + DATABASE_NAME + "/getAllEmpByDep";

            case 3:
                return "Content://" + AUTHORITY + "/" + DATABASE_NAME + "/getAllEmpOlderThanAge";

            case 4:
                return "Content://" + AUTHORITY + "/" + DATABASE_NAME + "/getAllEmpByHighSalary";

            case 5:
                return "Content://" + AUTHORITY + "/" + DATABASE_NAME + "/getAllEmpByLowSalary";

            default:
                return "url not found";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (mcontext == null){
            Log.d("myapp","context is null");
        } else {
            // TODO: Implement this to handle requests to insert a new row.
            // throw new UnsupportedOperationException("Not yet implemented");
            EmployeeDao employeeDao = EmployeeRoomDatabase.getDatabase(getContext()).employeeDao();
            long id = employeeDao.insertEmployee(EmployeeEntity.fromContentValues(values));
            Log.d("myapp","record inserted at" +id);
            mcontext.getContentResolver().notifyChange(uri, null);

            return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mcontext = getContext();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        String s1 = (uri).toString();
        final int code = MATCHER.match(uri);
       EmployeeDao employeeDao = EmployeeRoomDatabase.getDatabase(getContext()).employeeDao();
         Cursor cursor = null;
        switch (code) {
            case 1:
               // String s = selection;
               // selection = selection + uri.getLastPathSegment();
                cursor = employeeDao.getEmployeeById(selection);
                break;

            case 2 :
              //  s = s + uri.getLastPathSegment();
                cursor = employeeDao.getEmployeeByDepartment(selection);
                break;
            case 3 :
              //  s = s + uri.getLastPathSegment();
                cursor = employeeDao.getMiddleAgedEmployees(Integer.parseInt(selection));
                break;
            case 4 :
                //s = s + uri.getLastPathSegment();
                cursor = employeeDao.getHighSalaryBandEmployees(Float.parseFloat(selection));
                break;
            case 5 :
                //s = s + uri.getLastPathSegment();
                cursor = employeeDao.geLowSalaryBandEmployees(Float.parseFloat(selection));
                break;
            default:
        }
        return cursor;
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
