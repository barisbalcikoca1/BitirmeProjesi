package com.barisbalcikoca.bitirmeprojesi.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentDetayBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.SepetViewModel;
import com.bumptech.glide.Glide;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {

    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;
    private int urunSayisi = 0;
    private double total = 0;
    private final String kullanici_adi = "baris_balcikoca";

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetayBinding.inflate(inflater, container, false);
        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler yemek = bundle.getYemek();
        SepetYemekler sepetYemekler = new SepetYemekler();

        binding.toolbarDetay.setTitle("Yemek Detay");

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();
        Glide.with(this).load(url).into(binding.imageViewYemek);

        binding.tvYemekAdi.setText(yemek.getYemek_adi());

        binding.tvYemekFiyat.setText(yemek.getYemek_fiyat() + " ₺");
        binding.tvAdet.setText(String.valueOf(urunSayisi));
        binding.tvTotal.setText(String.valueOf(total));

        binding.btnEkle.setOnClickListener(view -> {
            urunSayisi++;
            binding.tvAdet.setText(String.valueOf(urunSayisi));
            binding.btnSepeteEkle.setEnabled(true);
            binding.btnSepeteEkle.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.kapaliSari));
            updateTotal();

        });
        binding.btnCikar.setOnClickListener(view -> {
            if (urunSayisi > 0) {
                urunSayisi--;
                binding.tvAdet.setText(String.valueOf(urunSayisi));
                updateTotal();
            }
            if(urunSayisi<=0){
                binding.btnSepeteEkle.setEnabled(false);
                binding.btnSepeteEkle.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
                binding.btnSepeteEkle.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.acikBeyaz));
            }
        });
        if(urunSayisi<=0){
            binding.btnSepeteEkle.setEnabled(false);
            binding.btnSepeteEkle.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
            binding.btnSepeteEkle.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.acikBeyaz));
        }

        binding.btnSepeteEkle.setOnClickListener(view -> {

            if (urunSayisi > 0) {

                sepetYemekler.setYemek_adi(yemek.getYemek_adi());
                sepetYemekler.setYemek_resim_adi(yemek.getYemek_resim_adi());
                sepetYemekler.setYemek_fiyat(yemek.getYemek_fiyat());
                sepetYemekler.setYemek_siparis_adet(urunSayisi);
                sepetYemekler.setKullanici_adi(kullanici_adi);
                viewModel.sepeteYemekEkle(sepetYemekler.getYemek_adi(), sepetYemekler.getYemek_resim_adi(), sepetYemekler.getYemek_fiyat(), urunSayisi,kullanici_adi);

                DetayFragmentDirections.SepetGecis gecis = DetayFragmentDirections.sepetGecis(sepetYemekler);
                Navigation.findNavController(view).navigate(gecis);
            }
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