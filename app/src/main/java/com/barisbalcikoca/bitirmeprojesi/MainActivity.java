package com.barisbalcikoca.bitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.YemeklerCevap;
import com.barisbalcikoca.bitirmeprojesi.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}