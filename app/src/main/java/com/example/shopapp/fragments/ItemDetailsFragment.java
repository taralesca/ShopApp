package com.example.shopapp.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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
import com.example.shopapp.data.DeleteItem;
import com.example.shopapp.model.Item;
import com.example.shopapp.util.AlarmReceiver;

import java.util.Calendar;

import static android.app.AlarmManager.RTC;
import static android.content.Context.ALARM_SERVICE;

public class ItemDetailsFragment extends Fragment {
    private Item item;

    ItemDetailsFragment(Item item) {
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView itemTitle = view.findViewById(R.id.item_title);
        itemTitle.setText(item.getTitle());
        TextView itemDescription = view.findViewById(R.id.item_description);
        itemDescription.setText(item.getDescription());
        TextView itemPrice = view.findViewById(R.id.item_price);
        itemPrice.setText(item.getPrice().toString());

        Button buyButton = view.findViewById(R.id.buy_item);
        buyButton.setOnClickListener(v -> {
            new DeleteItem(getContext(), item.getId()).execute();
            setAlarmForNotification();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame, new HomeFragment());
            fragmentTransaction.commit();
        });
    }

    private void setAlarmForNotification() {
        final AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(getContext(), AlarmReceiver.class);
        intent.putExtra("message", item.getTitle());
        final PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final Calendar calendar = Calendar.getInstance();

        alarmManager.set(RTC, calendar.getTimeInMillis(), pendingIntent);
    }


}
