package com.barisbalcikoca.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.repo.YemeklerDaoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi ;
    public MutableLiveData<Integer> tutar = new MutableLiveData<>(0);

    public String kullanici_adi = "baris_balcikoca";
    public YemeklerDaoRepository yrepo ;

    @Inject
    public SepetViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        sepettekiYemekleriGetir(kullanici_adi);
        sepetYemeklerListesi = yrepo.sepetYemeklerListesi;
    }
    public void sepettekiYemekleriGetir(String kullanici_adi){
        yrepo.sepettekiYemekleriGetir(kullanici_adi);
    }
    public void sepettenYemekSil(int sepet_yemek_id,String kullanici_adi ){
        yrepo.sepettenYemekSil(sepet_yemek_id,kullanici_adi);

        // Listeden de elemanı kaldır
        List<SepetYemekler> newList = new ArrayList<>(sepetYemeklerListesi.getValue());
        for (SepetYemekler sepetYemek : newList) {
            if (sepetYemek.getSepet_yemek_id() == sepet_yemek_id) {
                newList.remove(sepetYemek);
                break; // Bu satır, ilk bulunan elemanı kaldırdıktan sonra döngüden çıkması için
            }
        }
        sepetYemeklerListesi.postValue(newList);

    }

    public int tutarHesapla(int yemek_fiyati, int yemek_siparis_adet){
        tutar.setValue(yemek_fiyati*yemek_siparis_adet);

        return tutar.getValue();
    }

}
