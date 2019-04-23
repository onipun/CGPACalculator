package com.example.shaufyq.labtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultDB extends SQLiteOpenHelper {

    static final  String TAG = ResultDB.class.getSimpleName();
    public static  final String dbName = "dbMyExpense";
    public static  final String tblNameExpense = "subject";
    public static  final String subCode = "code";
    public static  final String subName = "name";
    public static  final String subHour = "hour";
    public static  final String subGrade = "grade";

    public static final String strCrtTblExpenses = "CREATE TABLE "+ tblNameExpense + " ("+ subCode + " TEXT PRIMARY KEY, "+ subName + " TEXT, "+ subHour + " TEXT,"+ subGrade + " TEXT)";
    public static final String strDropTblExpenses = " DROP TABLE IF EXISTS "+ tblNameExpense;

    public ResultDB(Context context){
        super(context,dbName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCrtTblExpenses);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(strDropTblExpenses);
        onCreate(db);
    }

    public float fnInsertExpense(ResultDbModel expenseDBModel){
        float retResult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(subName, expenseDBModel.getSubName());
        values.put(subCode, expenseDBModel.getSubCode());
        values.put(subHour, expenseDBModel.getSubHour());
        values.put(subGrade, expenseDBModel.getSubGrade());

        retResult = db.insert(tblNameExpense, null, values);
        return retResult;
    }

    public ResultDbModel fbGetResult(int strCodeId){
        ResultDbModel resultDbModel = new ResultDbModel();
        String strSelQry = "SELECT * FROM "+ tblNameExpense + " WHERE "+ subCode + " = "+ strCodeId;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelQry, null );
        if (cursor != null){
            cursor.moveToFirst();
        }
        resultDbModel.setSubCode(cursor.getString(cursor.getColumnIndex(subCode)));
        resultDbModel.setSubName(cursor.getString(cursor.getColumnIndex(subName)));
        resultDbModel.setSubHour(cursor.getString(cursor.getColumnIndex(subHour)));
        resultDbModel.setSubGrade(cursor.getString(cursor.getColumnIndex(subGrade)));

        return resultDbModel;
    }

    public double fnGetAllresult(){

        double total = 0;
        double gred = 0;
        double cred = 0;
        List<ResultDbModel> listExp = new ArrayList<>();
        String strSelAll = "SELECT * FROM "+ tblNameExpense;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll,null);
        if (cursor.moveToFirst()){
            do{
                Integer hour = new Integer(cursor.getColumnIndex(subHour));
                if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("A") ){
                    gred = 4;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("A-")){
                    gred = 3.70;

                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("B+")){

                    gred = 3.30;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("B")){

                    gred =3.00;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("B-")){

                    gred =2.70;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("C+")){

                    gred = 2.30;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("C")){

                    gred = 2.00;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("C-")){

                    gred =1.70;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("D+")){

                    gred =1.30;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("D")){
                    gred = 1 ;
                }else if ((cursor.getString(cursor.getColumnIndex(subGrade))).equals("E")){
                    gred =0 ;
                }


                Log.d(TAG, "fnGetAllresult: " + gred);
                total += (gred*hour);
                cred  += hour + cred;


            }while (cursor.moveToNext());
        }

        total = total/cred;
        return total;
    }

}
