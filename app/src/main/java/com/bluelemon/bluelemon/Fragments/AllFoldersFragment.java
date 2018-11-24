package com.bluelemon.bluelemon.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.FoldersAdapter;
import com.bluelemon.bluelemon.Models.FolderModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class AllFoldersFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<FolderModel> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_folders, container, false);

        activity.showBack(true);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        list.add(new FolderModel("Folder 1", "Count 1"));
        list.add(new FolderModel("Folder 1", "Count 1"));
        list.add(new FolderModel("Folder 1", "Count 1"));
        list.add(new FolderModel("Folder 1", "Count 1"));
        list.add(new FolderModel("Folder 1", "Count 1"));

        recyclerView.setAdapter(new FoldersAdapter(activity, list));

        return view;
    }

}
