package com.example.newsapp.newsap.OnBoarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ItemPagerBoardBinding;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.ViewHolder> {
    private NavigateListener navigateListener;
    private final String[] titles = new String[]{"привет ", "здесь вы можетье полчить ", "добра"};
    private final String[] desc = new String[]{"это приложения для новости", "много чего", "Пожаловать "};
    private final int[] lottie = new int[]{R.raw.news, R.raw.animation, R.raw.phone};

    public void setNavigateListener(NavigateListener navigateListener) {
        this.navigateListener = navigateListener;
    }

    public OnBoardAdapter() {


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPagerBoardBinding binding;


        public ViewHolder(@NonNull ItemPagerBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void bind(int position) {
            binding.TextTitle.setText(titles[position]);
            binding.desc.setText(desc[position]);
            binding.imageDots.setAnimation(lottie[position]);
            if (position == titles.length - 1) {
                binding.startFragment.setVisibility(View.VISIBLE);
            } else {
                binding.startFragment.setVisibility(View.INVISIBLE);
            }

            binding.startFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigateListener.btnStartOnClick();
                }
            });
        }


    }

    interface NavigateListener {
        void btnStartOnClick();
    }
}
