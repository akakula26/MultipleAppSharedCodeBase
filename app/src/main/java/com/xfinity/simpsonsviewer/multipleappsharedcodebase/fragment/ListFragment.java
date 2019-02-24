package com.xfinity.simpsonsviewer.multipleappsharedcodebase.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.xfinity.simpsonsviewer.multipleappsharedcodebase.BuildConfig;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.R;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.adapter.ListAdapter;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.Model;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.ModelClass;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest.RestApiClient;
import com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest.RestApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private List<Model> myDataSource = new ArrayList<>();
    private RecyclerView.Adapter myAdapter;
    private ToggleButton toggleButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.list_fragment, container, false);
        return mView;
    }



}
