package com.codeschool.candycoded;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.codeschool.candycoded.CandyContract.*;

public class CandyDBHelper extends SQLiteOpenHelper {
    public CandyDBHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL(SQL_CREATE_CANDY_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDB, final int oldV, final int newV) {
        sqLiteDB.execSQL(SQL_DROP_CANDY_TABLE);
        onCreate(sqLiteDB);
    }
}
