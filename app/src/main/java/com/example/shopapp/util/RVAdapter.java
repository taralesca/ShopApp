package com.example.shopapp.util;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.R;
import com.example.shopapp.model.Item;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TextViewHolder> {
    private Item[] dataSet;
    private OnTapListener onTapListener;

    public RVAdapter(Item[] dataSet, OnTapListener onTapListener) {
        this.dataSet = dataSet;
        this.onTapListener = onTapListener;
    }

    public void swapDataSet(Item[] newData) {
        this.dataSet = newData;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new TextViewHolder(v, onTapListener);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.titleView.setText(dataSet[position].getTitle());
        NumberFormat formatter = new DecimalFormat("#0.00$");
        holder.priceView.setText(formatter.format(dataSet[position].getPrice()));
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public interface OnTapListener {
        void onUserClick(int position);
    }

    static class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView titleView;
        TextView priceView;
        OnTapListener onTapListener;

        TextViewHolder(LinearLayout v, OnTapListener onTapListener) {
            super(v);
            linearLayout = v;
            this.onTapListener = onTapListener;
            titleView = v.findViewById(R.id.title_card_field);
            priceView = v.findViewById(R.id.price_card_field);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTapListener.onUserClick(getAdapterPosition());
        }
    }
}

