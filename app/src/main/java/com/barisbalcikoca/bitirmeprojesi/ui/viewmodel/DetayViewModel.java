package com.barisbalcikoca.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.barisbalcikoca.bitirmeprojesi.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {

    public YemeklerDaoRepository yrepo ;

    @Inject
    public DetayViewModel(YemeklerDaoRepository yrepo){
        this.yrepo = yrepo;
    }

    public void sepeteYemekEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        yrepo.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);

    }
}
