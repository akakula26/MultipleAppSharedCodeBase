package com.xfinity.simpsonsviewer.multipleappsharedcodebase.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.BuildConfig;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.R;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.activity.MainActivity;

public class DetailsFragment extends Fragment {

    private String titleValue, descriptionValue, imageValue;
    private TextView title, description;
    private ImageView image;
    private View mView;

    private TextView tv;
    private TextView mTitle;
    private boolean tabletSize= false;

    private Bundle bundle;
    public DetailsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.details_fragment, container, false);

        tabletSize = getResources().getBoolean(R.bool.isTablet );
        title = (TextView) mView.findViewById(R.id.title_view);
        description = (TextView) mView.findViewById(R.id.detail_view);
        image = (ImageView) mView.findViewById(R.id.image_view);
        bundle = this.getArguments();

        setHasOptionsMenu(true);

        return mView;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         final MainActivity act  = ((MainActivity)getActivity());

        Toolbar toolbar =act.findViewById(R.id.toolbar);

         mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);


        if(bundle!=null){
            titleValue  = bundle.getString("title");
            descriptionValue  = bundle.getString("description");
            imageValue  = bundle.getString("imageUrl");
            title.setText("Title :"+titleValue);
            description.setText("Description :"+descriptionValue);
            mTitle.setText(titleValue);
//            setHasOptionsMenu(false);
            Picasso.get().load(imageValue).resize(350, 250).into(image);
        }

        if(tabletSize==true) {
            if (BuildConfig.KEY.equals("simpsons+characters")) {
                mTitle.setText(R.string.app_name_simpsons);
            } else {
                mTitle.setText(R.string.app_name_wire);
            }
        }


    }

}
