package com.xfinity.simpsonsviewer.multipleappsharedcodebase.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.fragment.DetailsFragment;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.Model;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.R;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(Model item);
    }

    public List<Model> getListdetails() {
        return listdetails;
    }

    public void setListdetails(List<Model> listdetails) {
        this.listdetails = listdetails;
    }

    public Context getGetApplicationContext() {
        return getApplicationContext;
    }

    public void setGetApplicationContext(Context getApplicationContext) {
        this.getApplicationContext = getApplicationContext;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    private List<Model> listdetails;
    private Context getApplicationContext;
    private int rowLayout;
    private boolean isListView = true;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private FragmentManager fragmentManager;

    public ListAdapter(List<Model> listdetails, Context getApplicationContext, int rowLayout, FragmentManager fragmentManager, boolean isListView) {
        this.listdetails = listdetails;
        this.getApplicationContext = getApplicationContext;
        this.rowLayout = rowLayout;
        this.fragmentManager = fragmentManager;
        this.isListView = isListView;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout viewlist_layout, gridlist_layout;
        private RelativeLayout viewListener;
        private TextView title;
        private String imageUrl;
        private ImageView image;
        private  boolean tabletSize = false;

        public boolean isTabletSize() {
            return tabletSize;
        }

        public void setTabletSize(boolean tabletSize) {
            this.tabletSize = tabletSize;
        }

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            viewlist_layout = (RelativeLayout) itemView.findViewById(R.id.view_list);
            gridlist_layout = (RelativeLayout) itemView.findViewById(R.id.view_grid);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {

        String titleAndDescription  = listdetails.get(i).getText();

        final Resources resources = listViewHolder.itemView.getResources();

        try {
            if(resources!=null && resources.getBoolean(R.bool.isTablet)){
                listViewHolder.setTabletSize(true);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }

        final String [] divideTitleAndDescription = titleAndDescription.split("-");

        if(isListView) {
            listViewHolder.title.setText("Title : " + divideTitleAndDescription[0]);
        }
        // Enable to show Images in list View

        Iterator<Map.Entry<String,String>> it  = listdetails.get(i).getIcon().entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, String> pair = it.next();
            if(pair.getKey().equals("URL")) {
                if( !pair.getValue().equals("") && !pair.getValue().isEmpty()) {
                    if(!isListView) {
                        Picasso.get().load(pair.getValue()).resize(350,250).into(listViewHolder.image);
                    }
                    listViewHolder.imageUrl = pair.getValue();
                }else{
                    if(!isListView) {
                        Picasso.get().load("https://avatars1.githubusercontent.com/u/1539555?v=4").resize(350,250).into(listViewHolder.image);
                    }
                    listViewHolder.imageUrl = "https://avatars1.githubusercontent.com/u/1539555?v=4";
                }
            }
        }

        if(!isListView){
            listViewHolder.viewListener = listViewHolder.gridlist_layout;
        }
        else{
            listViewHolder.viewListener = listViewHolder.viewlist_layout;
        }

        listViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listViewHolder.tabletSize){
                    DetailsFragment detailsFragment = new DetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", divideTitleAndDescription[0]);
                    bundle.putString("description", divideTitleAndDescription[1]);
                    bundle.putString("imageUrl", listViewHolder.imageUrl);
                    detailsFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.detail_fragment, detailsFragment).addToBackStack( null).commit();

                }else{
                    DetailsFragment detailsFragment = new DetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", divideTitleAndDescription[0]);
                    bundle.putString("description", divideTitleAndDescription[1]);
                    bundle.putString("imageUrl", listViewHolder.imageUrl);
                    detailsFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, detailsFragment).addToBackStack(null).commit();
                    Toast.makeText(getApplicationContext, divideTitleAndDescription[0], Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdetails.size();
    }


}

