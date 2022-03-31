package com.example.newsapp.ui.profileFragment.tab_loyaut;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsapp.ui.profileFragment.first_tab.FirsFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {

            case 2:
                return  new SecondFragment();
        }
        return new FirsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
