package com.example.newsapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentHomeBinding;
import com.example.newsapp.ui.App;
import com.example.newsapp.ui.NewModel;
import com.example.newsapp.ui.OnItemClickListener;
import com.example.newsapp.ui.newsapp.NewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private NewAdapter adapter;
    private List<NewModel> newModelArrayList;
    private FragmentHomeBinding binding;
    private boolean isChanged = false;
    private int position;
    private EditText searchBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        adapter = new NewAdapter();

        List<NewModel> list = App.getDataBase().newDao().getAll();
        adapter.addList(list);
        adapter.setOnItemSearchListener(new NewAdapter.OnItemClickForSearch() {
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.RecyclerView.setAdapter(adapter);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChanged = false;
                open(null);
            }

        });
        sortSearch();


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                NewModel newModel = adapter.getItem(position);
                isChanged = true;
                open(newModel);
                HomeFragment.this.position = position;

            }

            @Override
            public void OnItemLongClickListener(int position, NewModel newModel) {
                new AlertDialog.Builder(view.getContext()).setTitle("Удаление")
                        .setMessage("Вы точно хотите удалить?")
                        .setNegativeButton("нет", null)
                        .setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(view.getContext(), "Delete", Toast.LENGTH_LONG).show();
                                adapter.removeItem(newModel);
                                App.getDataBase().newDao().delete(newModel);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
            }
        });


        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("umar the coolest", "text");
                NewModel newModel = (NewModel) result.getSerializable("news");
                if (isChanged) {
                    adapter.updateItem(newModel, position);
                } else {
                    // adapter.addItem(newModel);
                }


            }
        });
    }

    private void sortSearch() {

        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.search.getText().toString().isEmpty()) {
                    YoYo.with(Techniques.Shake)
                            .duration(500)
                            .repeat(1)
                            .playOn(binding.search);
                }

                    newModelArrayList = App.getDataBase().newDao().findUserWithName(editable.toString());
                    /*  newModelArrayList=  App.getDataBase().newDao().getAll();*/
                    adapter.addList(newModelArrayList);


            }
        });
        binding.RecyclerView.setAdapter(adapter);
        newModelArrayList = App.getDataBase().newDao().getAll();
        adapter.addList(newModelArrayList);

    }

    private void open(NewModel newModel) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateTask", newModel);
        navController.navigate(R.id.newsFragment, bundle);
    }
//


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int textReturn = item.getItemId();
//        Log.e("Tag", "onOptionsItemSelected: ");
        if (item.getItemId() == R.id.sorted) {
//            Log.e("Tag", "onOptionsItemSelected: ");
            adapter.setList((ArrayList<NewModel>) App.getDataBase().newDao().sort());
            binding.RecyclerView.setAdapter(adapter);

        }
        return super.onOptionsItemSelected(item);

    }

}
