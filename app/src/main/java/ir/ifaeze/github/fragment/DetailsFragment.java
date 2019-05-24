package ir.ifaeze.github.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ir.ifaeze.github.R;

public class DetailsFragment extends Fragment {
    public static final String NAME = "name";
    public static final String AVATAR = "avatar";
    public static final String URL = "url";
    TextView mTextViewHeader, mTextViewLink;
    ImageView mImageViewHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Toast.makeText(getActivity(), getArguments().getString(NAME), Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        getActivity().setTitle(getArguments().getString(NAME));
        initView(view);
        mTextViewHeader.setText(getArguments().getString(NAME));
        mTextViewLink.setText(getArguments().getString(URL));
        Picasso.with(getContext())
                .load(getArguments().getString(AVATAR))
                .placeholder(R.drawable.load)
                .into(mImageViewHeader);
        Linkify.addLinks(mTextViewLink, Linkify.WEB_URLS);

        return view;
    }

    private void initView(View view) {
        mTextViewHeader = view.findViewById(R.id.text_view_header);
        mTextViewLink = view.findViewById(R.id.text_view_link);
        mImageViewHeader = view.findViewById(R.id.image_view_header);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
