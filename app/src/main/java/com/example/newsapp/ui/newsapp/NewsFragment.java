package com.example.newsapp.ui.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentNewsBinding;
import com.example.newsapp.ui.App;
import com.example.newsapp.ui.NewModel;

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private NewModel newModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newModel = (NewModel) requireArguments().getSerializable("updateTask");
        if (newModel != null) {
            binding.editText.setText(newModel.getTextTitle());
        }
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        String text = binding.editText.getText().toString();
        if (newModel == null) {
            newModel = new NewModel(text, System.currentTimeMillis());
        } else {
            newModel.setTextTitle(text);
        }

        NewModel news = new NewModel(text, System.currentTimeMillis());
        App.getDataBase().newDao().insert(news);
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }
    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}