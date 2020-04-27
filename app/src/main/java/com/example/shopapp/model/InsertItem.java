package com.example.shopapp.model;

import android.content.Context;
import android.os.AsyncTask;

public class InsertItem extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private Item user;

    public InsertItem(Context context, Item user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        AppDatabase.getInstance(this.context).itemDao().insertAll(user);
        return true;
    }
}