package com.codeschool.candycoded;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import static com.codeschool.candycoded.CandyContract.CandyEntry.*;
import static com.codeschool.candycoded.CandyContract.SQL_SELECT_ALL;

public class MainActivity extends AppCompatActivity {
    private static final String CONTENT_URL = "https://s3.amazonaws.com/courseware.codeschool.com/super_sweet_android_time/API/CandyCoded.json";
    private Candy[] candies;
    private CandyCursorAdapter adapter;
    private final CandyDBHelper helper = new CandyDBHelper(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(R.string.products_title);

        final SQLiteDatabase db = helper.getWritableDatabase();
        final Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);

        adapter = new CandyCursorAdapter(this, cursor);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                final Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                //noinspection NestedMethodCall
                detailIntent.putExtra("position", i);
                startActivity(detailIntent);
            }
        });

//        cursor.close();

        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(CONTENT_URL, new TextHttpResponseHandler() {
            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String responseString, final Throwable throwable) {
                Log.e("AsyncHttpClient", "response = " + responseString);
            }

            @Override
            public void onSuccess(final int statusCode, final Header[] headers, final String responseString) {
                Log.d("AsyncHttpClient", "response = " + responseString);

                final Gson gson = new GsonBuilder().create();
                candies = gson.fromJson(responseString, Candy[].class);
                addCandiesToDB(candies);

                final SQLiteDatabase sqLiteDB = helper.getWritableDatabase();
                final Cursor c = sqLiteDB.rawQuery(SQL_SELECT_ALL, null);
                adapter.changeCursor(c);
//                c.close();
            }
        });
    }

    private void addCandiesToDB(final Candy[] candies) {
        if (candies == null || candies.length == 0) return;

        final SQLiteDatabase sqLiteDB = helper.getWritableDatabase();
        sqLiteDB.delete(TABLE_NAME, null, null);

        for (final Candy candy : candies) {
            final ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, candy.getName());
            values.put(COLUMN_PRICE, candy.getPrice());
            values.put(COLUMN_DESC, candy.getDescription());
            values.put(COLUMN_IMAGE, candy.getImage());

            sqLiteDB.insert(TABLE_NAME, null, values);
        }
    }
}
