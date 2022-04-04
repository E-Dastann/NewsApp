package com.example.newsapp.ui.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentNewsBinding;
import com.example.newsapp.ui.App;
import com.example.newsapp.ui.NewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private NewModel newModel;
    ArrayList<NewModel> list;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        App.getDataBase().newDao().insert(newModel);
        saveToFireStore(newModel);
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", newModel);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
    }

    private void saveToFireStore(NewModel news) {
        db.collection("news")
                .add(news)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        close();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}