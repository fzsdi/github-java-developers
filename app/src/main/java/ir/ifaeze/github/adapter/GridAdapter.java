package ir.ifaeze.github.adapter;

import android.content.Context;

import java.util.List;

import ir.ifaeze.github.R;
import ir.ifaeze.github.model.Item;

public class GridAdapter extends RecyclerAdapter {
    private List<Item> items;
    private Context context;
    public static final String DETAILS_FRAGMENT = "details_fragment";

    public GridAdapter(List<Item> items, Context context) {
        super(items, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grid_item;
    }
}
