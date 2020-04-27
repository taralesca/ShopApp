package com.example.shopapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.R;
import com.example.shopapp.data.GetAllItems;
import com.example.shopapp.model.Item;
import com.example.shopapp.util.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RVAdapter.OnTapListener {
    private List<Item> items;
    private RVAdapter adapter = new RVAdapter(new Item[]{}, this);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        items = new ArrayList<>();

        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            items.addAll(new GetAllItems(getContext()).execute().get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        configureRecyclerView(view);
        updateRecyclerView();
    }

    private void configureRecyclerView(@NonNull View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateRecyclerView() {
        adapter.swapDataSet(
                items.toArray(new Item[0])
        );
    }

    @Override
    public void onUserClick(int position) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, new ItemDetailsFragment(items.get(position)));
        fragmentTransaction.commit();
    }

}
