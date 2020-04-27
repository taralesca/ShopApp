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
import com.example.shopapp.model.GetAllItems;
import com.example.shopapp.model.Item;
import com.example.shopapp.util.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RVAdapter.OnTapListener {
    private List<Item> users;
    private RVAdapter adapter = new RVAdapter(new String[]{}, this);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        users = new ArrayList<>();
//        try {
//            final JsonArrayRequest jsonArrayRequest = getUserList();
//            RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            users.addAll(new GetAllItems(getContext()).execute().get());
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

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private JsonArrayRequest getUserList() {
//        return new JsonArrayRequest("https://my-json-server.typicode.com/taralesca/json-server/items/",
//                response -> {
//                    for (int index = 0; index < response.length(); index++) {
//                        try {
//                            final Item receivedItem = new Item().fromJSON(response.getJSONObject(index));
//                            if ((new GetUserById(getContext(), receivedItem.getId()).execute().get()) == null) {
//                                new InsertItem(getContext(), receivedItem).execute();
//                            }
//
//                        } catch (JSONException | InterruptedException | ExecutionException ex) {
//                            Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    updateRecyclerView();
//                },
//                error -> {
//                    Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
//                }
//        );
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateRecyclerView() {
        adapter.swapDataSet(
                users.stream()
                        .map(user -> user.getTitle() + " " + user.getDescription() + " " + user.getPrice())
                        .toArray(String[]::new)
        );
    }

    @Override
    public void onUserClick(int position) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, new ItemDetailsFragment(users.get(position)));
        fragmentTransaction.commit();
    }

}
