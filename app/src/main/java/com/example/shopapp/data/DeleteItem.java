package com.example.shopapp.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.shopapp.model.Item;

public class DeleteItem extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private int id;

    public DeleteItem(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        final Item user = AppDatabase.getInstance(this.context).itemDao().findById(id);
        if (user == null) {
            return false;
        }
        AppDatabase.getInstance(this.context).itemDao().delete(user);
        return true;
    }
}