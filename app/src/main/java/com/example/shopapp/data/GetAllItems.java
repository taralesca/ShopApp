package com.example.shopapp.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.shopapp.model.Item;

import java.util.List;

public class GetAllItems extends AsyncTask<Void, Void, List<Item>> {
    private Context context;

    public GetAllItems(Context context) {
        this.context = context;
    }

    @Override
    protected List<Item> doInBackground(Void... voids) {
        return AppDatabase.getInstance(context).itemDao().getAll();
    }
}

