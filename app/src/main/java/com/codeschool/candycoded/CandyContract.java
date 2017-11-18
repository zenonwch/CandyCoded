package com.codeschool.candycoded;

import static android.provider.BaseColumns._ID;
import static com.codeschool.candycoded.CandyContract.CandyEntry.*;

public class CandyContract {
    public static final String DB_NAME = "candycoded.db";
    public static final int DB_VERSION = 1;

    public static final String SQL_CREATE_CANDY_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_DESC + " TEXT, " +
                    COLUMN_IMAGE + " TEXT)";

    public static final String SQL_DROP_CANDY_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_SELECT_ALL =
            "SELECT * FROM " + TABLE_NAME;

    public static class CandyEntry {
        public static final String TABLE_NAME = "candy";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_IMAGE = "image";
    }
}
