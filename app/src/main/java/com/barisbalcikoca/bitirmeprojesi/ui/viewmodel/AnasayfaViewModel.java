package com.barisbalcikoca.bitirmeprojesi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {

    public MutableLiveData<List<Yemekler>> yemeklerListesi ;
    public YemeklerDaoRepository yrepo ;

    @Inject
    public AnasayfaViewModel(YemeklerDaoRepository yrepo){
        this.yrepo = yrepo;
        yemekleriYukle();
        yemeklerListesi = yrepo.yemeklerListesi;
    }
    public void yemekleriYukle(){
        yrepo.yemekleriYukle();

    }

}
