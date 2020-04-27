package com.example.shopapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopapp.R;
import com.example.shopapp.model.InsertItem;
import com.example.shopapp.model.Item;

public class SellFragment extends Fragment {

    public SellFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleField = view.findViewById(R.id.title_field);
        TextView descriptionField = view.findViewById(R.id.description_field);
        TextView priceField = view.findViewById(R.id.price_field);
        Button sellButton = view.findViewById(R.id.sell_button);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertItem(getContext(),
                        new Item(1,
                                titleField.getText().toString(),
                                descriptionField.getText().toString(),
                                Double.valueOf(priceField.getText().toString()))).execute();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, new HomeFragment());
                fragmentTransaction.commit();
            }
        });
    }
}
