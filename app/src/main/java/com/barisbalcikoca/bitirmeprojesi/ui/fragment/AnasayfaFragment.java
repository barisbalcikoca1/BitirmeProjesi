package com.barisbalcikoca.bitirmeprojesi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentAnasayfaBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.adapter.YemeklerAdapter;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class AnasayfaFragment extends Fragment {

    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    private DetayViewModel detayViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Yemekler");
        binding.yemekrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),yemeklerListesi -> {
            YemeklerAdapter yemeklerAdapter = new YemeklerAdapter(requireContext(),yemeklerListesi,detayViewModel);
            binding.yemekrv.setAdapter(yemeklerAdapter);
        } );

        SepetYemekler sepetYemekler = new SepetYemekler();
        binding.sepet.setOnClickListener(view -> {
            AnasayfaFragmentDirections.AnasayfaSepetGecis sepetGecis = AnasayfaFragmentDirections.anasayfaSepetGecis(sepetYemekler);
            Navigation.findNavController(view).navigate(sepetGecis);
        });
        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= new ViewModelProvider(this).get(AnasayfaViewModel.class);
        detayViewModel= new ViewModelProvider(this).get(DetayViewModel.class);
    }
}