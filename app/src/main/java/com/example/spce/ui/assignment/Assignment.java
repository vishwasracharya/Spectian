package com.example.spce.ui.assignment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spce.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Assignment extends Fragment {

    private AssignmentViewModel mViewModel;

    RecyclerView sem5Recycler, sem6Recycler;
    AssignmentAdapter adapter;

    DatabaseReference reference;

    public static Assignment newInstance() {
        return new Assignment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assignment_fragment, container, false);

        sem5Recycler = view.findViewById(R.id.sem5Recycler);
        sem6Recycler = view.findViewById(R.id.sem6Recycler);

        reference = FirebaseDatabase.getInstance().getReference().child("assignment");

        getsem5Image();

        getsem6Image();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        // TODO: Use the ViewModel
    }

    private void getsem6Image() {
        reference.child("6").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new AssignmentAdapter(getContext(), imageList);
                sem6Recycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                sem6Recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void getsem5Image() {
        reference.child("5").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter = new AssignmentAdapter(getContext(), imageList);
                sem5Recycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                sem5Recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}