package ir.ifaeze.github.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ir.ifaeze.github.R;
import ir.ifaeze.github.adapter.GridAdapter;
import ir.ifaeze.github.api.Client;
import ir.ifaeze.github.api.Service;
import ir.ifaeze.github.model.Item;
import ir.ifaeze.github.model.ItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridFragment extends Fragment {
    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initViews(view);
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark);
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUsers();
                // Toast.makeText(getContext(), "Refreshed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        loadUsers();
        return view;
    }

    private void initViews(View view) {
        mSwipeContainer = view.findViewById(R.id.container);
        mProgressBar = new ProgressBar(getContext());
        mRecyclerView = view.findViewById(R.id.recycler_view);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumn = (int) (dpWidth / 200);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numColumn));
        mRecyclerView.smoothScrollToPosition(0);
        loadUsers();
    }

    private void loadUsers() {
        try {
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            Call<ItemResponse> call = service.getItems("language:java");
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    if (response.body() != null) {
                        List<Item> items = response.body().getItems();
                        mRecyclerView.setAdapter(new GridAdapter(items, getActivity()));
                        mRecyclerView.smoothScrollToPosition(0);
                        mSwipeContainer.setRefreshing(false);
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getActivity(), "Error while fetching data", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
