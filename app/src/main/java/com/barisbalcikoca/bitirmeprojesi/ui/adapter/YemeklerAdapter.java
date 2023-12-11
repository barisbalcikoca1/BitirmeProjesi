package com.barisbalcikoca.bitirmeprojesi.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.AnasayfaCardTasarimBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.fragment.AnasayfaFragment;
import com.barisbalcikoca.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.AnasayfaCardTasarimTutucu> {

    private Context mContext;
    private List<Yemekler> yemeklerListesi;
    private DetayViewModel detayViewModel;
    private final String kullanici_adi = "baris_balcikoca";
    SepetYemekler sepetYemekler = new SepetYemekler();



    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerListesi,DetayViewModel detayViewModel) {
        this.mContext = mContext;
        this.yemeklerListesi = yemeklerListesi;
        this.detayViewModel = detayViewModel;
    }

    public class AnasayfaCardTasarimTutucu extends RecyclerView.ViewHolder{
        private AnasayfaCardTasarimBinding tasarim;

        public AnasayfaCardTasarimTutucu(AnasayfaCardTasarimBinding tasarim){
            super(tasarim.getRoot());
            this.tasarim = tasarim;

        }
    }

    @NonNull
    @Override
    public AnasayfaCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnasayfaCardTasarimBinding binding = AnasayfaCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new AnasayfaCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnasayfaCardTasarimTutucu holder, int position) {
        Yemekler yemek = yemeklerListesi.get(position);

        AnasayfaCardTasarimBinding t = holder.tasarim;

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi();
        Glide.with(mContext).load(url).into(t.ivYemek);

        t.tvYemekAdi.setText(yemek.getYemek_adi());
        t.tvYemekFiyat.setText(yemek.getYemek_fiyat()+" ₺");

        t.cardViewYemek.setOnClickListener(view -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });


    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }



}
