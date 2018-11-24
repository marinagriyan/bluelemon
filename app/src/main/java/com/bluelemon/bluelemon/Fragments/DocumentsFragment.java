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
import com.bluelemon.bluelemon.Adapters.DocumentsAdapter;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.R;

import java.util.ArrayList;
import java.util.List;

public class DocumentsFragment extends Fragment {
    private MainActivity activity;
    private DocumentsMainFragment fragment;
    private RecyclerView recyclerView;
    private List<DocumentModel> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        fragment = (DocumentsMainFragment) getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documents, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        list.add(new DocumentModel(false, "LX01 Lense Cleaner", "January 30, 2019", "Health & safety committee meet", "Hera"));
        list.add(new DocumentModel(true, "LX01 Lense Cleaner", "January 30, 2019", "Health & safety committee meet", "Hera"));

        recyclerView.setAdapter(new DocumentsAdapter(activity, list));

        view.findViewById(R.id.add_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new NewDocumentFragment());
            }
        });
        return view;
    }
}
