package com.codeschool.candycoded;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CandyCursorAdapter extends CursorAdapter {

    public CandyCursorAdapter(final Context context, final Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup parentView) {
        return LayoutInflater.from(context)
                .inflate(R.layout.list_item_candy, parentView, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        final TextView textView = view.findViewById(R.id.text_view_candy);
        final int colInd = cursor.getColumnIndexOrThrow("name");
        final String candyName = cursor.getString(colInd);
        textView.setText(candyName);
    }
}
