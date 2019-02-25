package com.xfinity.simpsonsviewer.multipleappsharedcodebase.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xfinity.simpsonsviewer.multipleappsharedcodebase.BuildConfig;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.Model;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.ModelClass;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.R;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest.RestApiClient;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest.RestApiInterface;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView emptyText;
    private RecyclerView mRecyclerView;
    private List<Model> myDataSource = new ArrayList<>();
    private RecyclerView.Adapter myAdapter;
    private ToggleButton toggleButton;
    private boolean tabletSize= false;
    public Toolbar toolbar;

    TextView mTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyText = (TextView) findViewById(R.id.textView);
        tabletSize = getResources().getBoolean(R.bool.isTablet );

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null){
         actionBar.setDisplayShowTitleEnabled(false);
         mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if(BuildConfig.KEY.equals("simpsons+characters") && tabletSize == false) {
                mTitle.setText(R.string.app_name_simpsons);
             }else{
                mTitle.setText(R.string.app_name_wire);
            }
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        LinearLayoutManager verticalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(verticalLayoutManagaer);
        myAdapter = new ListAdapter(myDataSource, getApplicationContext(), R.layout.view_list,getSupportFragmentManager(),true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(myAdapter);

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        if(netInfo!=null){
            if(netInfo.isConnected()){
                fetchData();
            }
        }else{
            emptyText.setText("Please Check Your Internet Connection");
        }

    }


    public void fetchData(){

        final RestApiInterface restApiInterface = RestApiClient.getApiClient().create(RestApiInterface.class);
        HashMap<String, String> apiFilters = new HashMap<>();
        apiFilters.put("format", "json");
        Call<ModelClass> call = restApiInterface.dataSearch(BuildConfig.KEY,apiFilters);
        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                Log.v(TAG,"receivedList toString :"+response.toString());
                Log.v(TAG,"receivedList body:"+response.body());

                myDataSource.clear();
                myDataSource.addAll(response.body().getList());
                myAdapter.notifyDataSetChanged();

                List<Model> models = response.body().getList();
                for (Model search : models) {

                }
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                Log.e(TAG,"Failure Response");

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(tabletSize==false){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
            MenuItem item = menu.findItem(R.id.menu_toogle);
            item.setActionView(R.layout.actionbar_layout);
            toggleButton = item.getActionView().findViewById(R.id.toogle);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (isChecked) {
                        Toast.makeText(getApplication(), "Grid View Checked", Toast.LENGTH_LONG).show();
                        mRecyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
                        GridLayoutManager verticalLayoutManagaer = new GridLayoutManager(getApplicationContext(), 2);
                        mRecyclerView.setLayoutManager(verticalLayoutManagaer);
                        myAdapter = new ListAdapter(myDataSource, getApplicationContext(), R.layout.view_grid, getSupportFragmentManager(), false);
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                        mRecyclerView.setAdapter(myAdapter);
                    } else {
                        Toast.makeText(getApplication(), "List View Checked", Toast.LENGTH_LONG).show();
                        mRecyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
                        LinearLayoutManager verticalLayoutManagaer = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(verticalLayoutManagaer);
                        myAdapter = new ListAdapter(myDataSource, getApplicationContext(), R.layout.view_list, getSupportFragmentManager(), true);
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                        mRecyclerView.setAdapter(myAdapter);
                    }

                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(BuildConfig.KEY.equals("simpsons+characters")) {
            mTitle.setText(R.string.app_name_simpsons);
        }else{
            mTitle.setText(R.string.app_name_wire);
        }
    }
}
