package com.example.newsapp.ui.profileFragment.first_tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentFirsBinding;


import java.util.ArrayList;


public class FirsFragment extends Fragment {
    private FragmentFirsBinding binding;
    private FirstAdapter adapter;
    private ArrayList<Integer> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        adapter = new FirstAdapter(list);
        binding.RecyclerTab1.setAdapter(adapter);

    }

    private void loadData() {
        list = new ArrayList<>();
        list.add(R.drawable.img_1);
        list.add(R.drawable.img);
        list.add(R.drawable.img_1);
        list.add(R.drawable.img_2);
        list.add(R.drawable.img_1);
        list.add(R.drawable.img_2);
        list.add(R.drawable.img_1);
        list.add(R.drawable.img);
        list.add(R.drawable.img_2);
        list.add(R.drawable.img);
        list.add(R.drawable.img_1);
        list.add(R.drawable.img_2);
    }
}