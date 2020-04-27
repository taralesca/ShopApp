package com.example.shopapp.model;

import android.content.Context;
import android.os.AsyncTask;

public class GetUserById extends AsyncTask<Void, Void, Item> {
    private Context context;
    private int id;

    public GetUserById(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected Item doInBackground(Void... voids) {
        final Item user = AppDatabase.getInstance(this.context).itemDao().findById(id);
        if (user == null) {
            return null;
        }

        return user;
    }
}
