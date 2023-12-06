package com.barisbalcikoca.bitirmeprojesi.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentDetayBinding;
import com.barisbalcikoca.bitirmeprojesi.databinding.FragmentSepetBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.SepetViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {

    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}