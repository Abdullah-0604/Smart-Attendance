package com.example.joker.smartattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 1/3/2018.
 */

public class CompanyDAO {

    public static final String TAG = "CompanyDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { DBHelper.COLUMN_DEPARTMENT_ID,
            DBHelper.COLUMN_DEPARTMENT_NAME, DBHelper.COLUMN_YEAR,
            DBHelper.COLUMN_SEMESTER,
            DBHelper.COLUMN_COMPANY_PHONE_NUMBER };

    public CompanyDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Company createCompany(String name, String yr, String sem,
                                 String phoneNumber) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DEPARTMENT_NAME, name);
        values.put(DBHelper.COLUMN_YEAR, yr);
        values.put(DBHelper.COLUMN_SEMESTER, sem);
        values.put(DBHelper.COLUMN_COMPANY_PHONE_NUMBER, phoneNumber);
        long insertId = mDatabase
                .insert(DBHelper.TABLE_DEPARTMENT, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_DEPARTMENT, mAllColumns,
                DBHelper.COLUMN_DEPARTMENT_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Company newCompany = cursorToCompany(cursor);
        return newCompany;
    }

    public void deleteCompany(Company company) {
        long id = company.getdId();
        // delete all employees of this company
        System.out.println("the deleted company has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_DEPARTMENT, DBHelper.COLUMN_DEPARTMENT_ID
                + " = " + id, null);
    }

    public List<Company> getAllCompanies() {
        List<Company> listCompanies = new ArrayList<Company>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_DEPARTMENT, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Company company = cursorToCompany(cursor);
                listCompanies.add(company);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompanies;
    }

    public Company getCompanyById(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_DEPARTMENT, mAllColumns,
                DBHelper.COLUMN_DEPARTMENT_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Company company = cursorToCompany(cursor);
        return company;
    }

    protected Company cursorToCompany(Cursor cursor) {
        Company company = new Company();
        company.setdId(cursor.getLong(0));
        company.setdName(cursor.getString(1));
        company.setYear(cursor.getString(2));
        company.setSemester(cursor.getString(3));
        company.setmPhoneNumber(cursor.getString(4));
        return company;
    }

}
