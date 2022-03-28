package com.example.newsapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.ui.App;
import com.example.newsapp.ui.Prefs;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.newsapp.databinding.ActivityMainBinding;

import java.util.Base64;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(this);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        if (!prefs.isBoardShown()) {
            navController.navigate(R.id.onBoardFragment);
        }
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.onBoardFragment) {
                    binding.navView.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {
                    binding.navView.setVisibility(View.VISIBLE);
                    getSupportActionBar().show();
                }
            }
        });

    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu_cash,menu);
    return true;

}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId()==R.id.clear_text){
//            Toast.makeText(this, "dsgdfgfdg", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else {
        return super.onOptionsItemSelected(item);
    }

}