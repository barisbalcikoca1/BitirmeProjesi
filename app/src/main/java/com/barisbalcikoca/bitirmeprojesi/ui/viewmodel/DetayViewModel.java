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
}
