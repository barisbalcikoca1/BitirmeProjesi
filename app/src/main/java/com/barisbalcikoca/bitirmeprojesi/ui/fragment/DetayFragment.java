package com.barisbalcikoca.bitirmeprojesi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentDetayBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.bumptech.glide.Glide;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {

    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;
    private int urunSayisi = 0;
    private double total = 0;
    private final String kullanici_adi = "baris_balcikoca";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetayBinding.inflate(inflater, container, false);
        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler yemek = bundle.getYemek();

        binding.toolbarDetay.setTitle("Yemek Detay");

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();
        Glide.with(this).load(url).into(binding.imageViewYemek);

        binding.tvYemekAdi.setText(yemek.getYemek_adi());

        binding.tvYemekFiyat.setText(yemek.getYemek_fiyat() + " ₺");
        binding.tvAdet.setText(String.valueOf(urunSayisi));

        binding.btnEkle.setOnClickListener(view -> {
            urunSayisi++;
            binding.tvAdet.setText(String.valueOf(urunSayisi));
            updateTotal();

        });
        binding.btnCikar.setOnClickListener(view -> {
            if (urunSayisi > 0) {
                urunSayisi--;
                binding.tvAdet.setText(String.valueOf(urunSayisi));
                updateTotal();
            }
        });

        binding.btnSepeteEkle.setOnClickListener(view -> {
            String url2 = "http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php";


        });

        return binding.getRoot();
    }

    private void updateTotal() {
        String fiyatText = binding.tvYemekFiyat.getText().toString();
        String fiyatReplace = fiyatText.replace(" ₺", "");
        total = urunSayisi * Double.parseDouble(fiyatReplace);
        binding.tvTotal.setText(String.valueOf(total));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);
    }
}