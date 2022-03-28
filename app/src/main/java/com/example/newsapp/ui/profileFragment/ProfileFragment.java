package com.example.newsapp.ui.profileFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.newsapp.ui.MainActivity;
import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentProfileBinding;
import com.example.newsapp.ui.Prefs;


public class ProfileFragment extends Fragment {
    private ActivityResultLauncher<String> addPhoto;
    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addImageLouncher();
        saveText();
            binding.dataName.setText(MainActivity.prefs.getEdit());

    }

    private void saveText() {
        binding.dataName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.prefs.editSave(binding.dataName.getText().toString());
            }
        });
    }


    private void addImageLouncher() {
        ActivityResultLauncher<Intent> imageGet = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                Intent data = result.getData();
                                binding.ImageAdd.setImageURI(data.getData());
                                MainActivity.prefs = new  Prefs(requireContext());
                                MainActivity.prefs.saveImageUri(data.getData());
                            }
                        });
        binding.ImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imageGet.launch(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (MainActivity.prefs.getImageUri() != null) {
            Uri uri = MainActivity.prefs.getImageUri();
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.ImageAdd);
            binding.dataName.setText(MainActivity.prefs.getEdit());
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu ,MenuInflater inflater) {
       inflater.inflate(R.menu.menu_cash,menu);
        menu.removeItem(R.id.clear_text);
      super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int textReturn= item.getItemId();
        if (R.id.clear_cash==textReturn){
         MainActivity.prefs.clearCash();
         binding.ImageAdd.setImageURI(MainActivity.prefs.getImageUri());
         binding.dataName.setText(MainActivity.prefs.getEdit());
            return true;

        }
     return false;

    }
}