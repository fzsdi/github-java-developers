package ir.ifaeze.github.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.ifaeze.github.R;
import ir.ifaeze.github.fragment.DetailsFragment;
import ir.ifaeze.github.model.Item;

public abstract class RecyclerAdapter extends RecyclerView.Adapter {
    private List<Item> items;
    private Context context;
    public static final String DETAILS_FRAGMENT = "details_fragment";

    public RecyclerAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        return new ListViewHolder(view);
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextViewName, mTextViewGithubLink;
        private ImageView mImageViewAvatar;
        private int mIndex;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewGithubLink = itemView.findViewById(R.id.text_view_github_link);
            mImageViewAvatar = itemView.findViewById(R.id.image_view_avatar);
            itemView.setOnClickListener(this);
        }

        public void bindView(int posotion) {

            mTextViewName.setText(items.get(posotion).getLogin());
            mTextViewGithubLink.setText(items.get(posotion).getUrl());
            Picasso.with(context)
                    .load(items.get(posotion).getAvatar())
                    .placeholder(R.drawable.load)
                    .into(mImageViewAvatar);
        }

        @Override
        public void onClick(View v) {
            mIndex = getAdapterPosition();
            DetailsFragment fragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailsFragment.NAME, items.get(mIndex).getLogin());
            bundle.putString(DetailsFragment.AVATAR, items.get(mIndex).getAvatar());
            bundle.putString(DetailsFragment.URL, items.get(mIndex).getUrl());
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.place_holder, fragment, DETAILS_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
