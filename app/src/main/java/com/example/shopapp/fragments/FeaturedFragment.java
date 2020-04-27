package com.example.shopapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.shopapp.R;
import com.example.shopapp.model.GetAllItems;
import com.example.shopapp.model.Item;
import com.example.shopapp.util.RVAdapter;
import com.example.shopapp.util.RequestQueueSingleton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FeaturedFragment extends Fragment implements RVAdapter.OnTapListener {
    private List<Item> items;
    private RVAdapter adapter = new RVAdapter(new Item[]{}, this);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        items = new ArrayList<>();
        try {
            final JsonArrayRequest jsonArrayRequest = getUserList();
            RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    private JsonArrayRequest getUserList() {
        return new JsonArrayRequest("https://my-json-server.typicode.com/taralesca/json-server/items/",
                response -> {
                    for (int index = 0; index < response.length(); index++) {
                        try {
                            final Item receivedItem = new Item().fromJSON(response.getJSONObject(index));
                            items.add(receivedItem);
                        } catch (JSONException ex) {
                            Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
                        }
                    }
                    updateRecyclerView();
                },
                error -> {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateRecyclerView() {
        adapter.swapDataSet(
                items.stream().toArray(Item[]::new)
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
