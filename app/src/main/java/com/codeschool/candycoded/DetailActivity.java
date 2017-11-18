package com.codeschool.candycoded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import static com.codeschool.candycoded.CandyContract.CandyEntry.*;
import static com.codeschool.candycoded.CandyContract.SQL_SELECT_ALL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent intent = getIntent();

        if (intent != null && intent.hasExtra("position")) {
            final int pos = intent.getIntExtra("position", 0);
            final CandyDBHelper helper = new CandyDBHelper(this);
            final SQLiteDatabase sqLiteDB = helper.getWritableDatabase();
            final Cursor cursor = sqLiteDB.rawQuery(SQL_SELECT_ALL, null);
            cursor.moveToPosition(pos);

            int colInd = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            final String candyName = cursor.getString(colInd);
            colInd = cursor.getColumnIndexOrThrow(COLUMN_PRICE);
            final double candyPrice = cursor.getDouble(colInd);
            colInd = cursor.getColumnIndexOrThrow(COLUMN_DESC);
            final String candyDescription = cursor.getString(colInd);
            colInd = cursor.getColumnIndexOrThrow(COLUMN_IMAGE);
            final String candyImageUrl = cursor.getString(colInd);

            cursor.close();

            final ImageView imageView = (ImageView) findViewById(R.id.image_view_candy);
            Picasso.with(this).load(candyImageUrl).into(imageView);

            ((TextView) findViewById(R.id.text_view_name)).setText(candyName);
            ((TextView) findViewById(R.id.text_view_price)).setText(String.valueOf(candyPrice));
            ((TextView) findViewById(R.id.text_view_desc)).setText(candyDescription);

            Log.d("DetailActivity", String.format("Intent Data: %s, %.2f, %s, %s", candyName, candyPrice, candyDescription, candyImageUrl));
        }
    }
}
