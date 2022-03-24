package com.example.newsapp.newsap.OnBoarding;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentOnBoardBinding;
import com.example.newsapp.ui.OnItemClickListener;
import com.example.newsapp.ui.Prefs;
import com.example.newsapp.ui.notifications.NewModel;
;


public class OnBoardFragment extends Fragment implements OnBoardAdapter.NavigateListener {

    private FragmentOnBoardBinding binding;
    private ViewPager2 viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = binding.viewPager;
        OnBoardAdapter adapter = new OnBoardAdapter();
        binding.viewPager.setAdapter(adapter);
        binding.dotsIndicator.setViewPager2(viewPager);
        positionViewPager();
        adapter.setNavigateListener(this);
        btnStart();
        finshDispatcher();

    }

    private void finshDispatcher() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void btnStart() {
        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs prefs = new Prefs(requireContext());
                prefs.saveBoardState();
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigateUp();
            }
        });
    }
    private void positionViewPager() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    binding.skip.setVisibility(View.INVISIBLE);
                }
                else {
                    binding.skip.setVisibility(View.VISIBLE);
                }
                super.onPageSelected(position);
            }
        });
    }

    @Override
    public void btnStartOnClick() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}