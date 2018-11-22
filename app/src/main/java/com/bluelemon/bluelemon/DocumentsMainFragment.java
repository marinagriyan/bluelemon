package com.bluelemon.bluelemon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DocumentsMainFragment extends Fragment {
    private BaseActivity activity;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documents_main, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        view.findViewById(R.id.add_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
