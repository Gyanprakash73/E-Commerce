package com.gyan.ecommerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gyan.ecommerce.Adapter.CartAdapter;
import com.gyan.ecommerce.LocalDatabase.SqLiteHelper;
import com.gyan.ecommerce.Model.LocalDataModel;
import com.gyan.ecommerce.databinding.ActivityCartBinding;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    SqLiteHelper sqLiteHelper;
    List<LocalDataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sqLiteHelper=new SqLiteHelper(this);

        //set recyclerView
        binding.reycler.setHasFixedSize(true);
        binding.reycler.setLayoutManager(new LinearLayoutManager(this));

        dataList=sqLiteHelper.getData();
        binding.reycler.setAdapter(new CartAdapter(dataList,this));
    }
}