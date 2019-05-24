package ir.ifaeze.github.adapter;

import android.content.Context;

import java.util.List;

import ir.ifaeze.github.R;
import ir.ifaeze.github.model.Item;

public class ListAdapter extends RecyclerAdapter {
    private List<Item> items;
    private Context context;

    public ListAdapter(List<Item> items, Context context) {
        super(items, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }
}
