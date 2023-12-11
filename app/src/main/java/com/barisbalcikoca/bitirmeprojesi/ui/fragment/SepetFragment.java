package com.barisbalcikoca.bitirmeprojesi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentDetayBinding;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentSepetBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.adapter.SepetAdapter;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.SepetViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment  {

    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    private int toplamTutar;
    private final String kullanici_adi = "baris_balcikoca";
    private SepetAdapter sepetAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);
        SepetFragmentArgs bundle = SepetFragmentArgs.fromBundle(getArguments());
        SepetYemekler sepetYemekler = bundle.getSepetYemek();

        binding.toolbarSepet.setTitle("Sepetim");
        binding.sepetrv.setLayoutManager(new LinearLayoutManager(requireContext()));



        viewModel.sepetYemeklerListesi.observe(getViewLifecycleOwner(),sepetYemeklerListesi ->{

            toplamTutar = 0; // bu eklenmezse sıkıntı oluyor.
            viewModel.tutar.observe(getViewLifecycleOwner(),s ->{
                binding.tvSepetToplamTutar.setText(String.valueOf(s));
            });

            for(int i = 0; i<sepetYemeklerListesi.size();i++){
                toplamTutar += sepetYemeklerListesi.get(i).getYemek_fiyat()*sepetYemeklerListesi.get(i).getYemek_siparis_adet();

            }
            binding.tvSepetToplamTutar.setText(String.valueOf(toplamTutar));

            if(toplamTutar<=0){
                binding.btnSepetiOnayla.setEnabled(false);
                binding.btnSepetiOnayla.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
                binding.btnSepetiOnayla.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.acikBeyaz));
            }
            else{
                binding.btnSepetiOnayla.setEnabled(true);
                binding.btnSepetiOnayla.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.kapaliSari));
            }

            sepetAdapter = new SepetAdapter(requireContext(),sepetYemeklerListesi,viewModel);
            binding.sepetrv.setAdapter(sepetAdapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= new ViewModelProvider(this).get(SepetViewModel.class);
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.sepettekiYemekleriGetir(kullanici_adi);
    }

}