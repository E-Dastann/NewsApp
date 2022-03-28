package com.example.newsapp.newsap.OnBoarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ItemPagerBoardBinding;

import java.util.ArrayList;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.ViewHolder> {
    private ArrayList<BoardModel> list;
    private NavigateListener navigateListener;

    public void setNavigateListener(NavigateListener navigateListener) {
        this.navigateListener = navigateListener;
    }

    public OnBoardAdapter() {

        list = new ArrayList<>();
        list.add(new BoardModel("это наша первая приложения ", "мы в сфеере It  первый раз ",R.drawable.img));
        list.add(new BoardModel("Мы верим в с нами ! ", "мы дабавим новое функции каждый день ",R.drawable.img_1));
        list.add(new BoardModel(" Что бы вам было комфортно и удобно", "с уважением администрация ",R.drawable.img_2));

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
            BoardModel boardModel = list.get(position);

            binding.TextTitle.setText(boardModel.getTitle());
            binding.desc.setText(boardModel.getDesc());
            binding.imageDots.setImageResource(boardModel.getImage());
            if (position == list.size() - 1) {
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
    interface NavigateListener{
        void btnStartOnClick();
    }
}
