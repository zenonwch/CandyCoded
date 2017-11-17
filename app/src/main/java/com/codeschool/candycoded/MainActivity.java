package com.codeschool.candycoded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final String newTitle = getString(R.string.products_title);
        textView.setText(newTitle);

        final List<String> candyList = new ArrayList<>();
        candyList.add("Tropical Wave");
        candyList.add("Berry Bouncer");
        candyList.add("Grape Gummer");
        candyList.add("Apple of My Eye");
        candyList.add("Much Minty");
        candyList.add("So Fresh");
        candyList.add("Sassy Sandwich Cookie");
        candyList.add("Uni-pop");
        candyList.add("Strawberry Surprise");
        candyList.add("Wish Upon a Star");
        candyList.add("Planetary Pops");
        candyList.add("Watermelon Like Whoa");
        candyList.add("Twist 'n' Shout ");
        candyList.add("Beary Squad Goals");
        candyList.add("ROYGBIV Spinner");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.list_item_candy, R.id.text_view_candy, candyList);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                // creating a toast
                final String text = String.valueOf(i);
                final Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
