package com.example.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragments.R;

/**
 * Created by Propietario on 29/01/2015.
 */
public class BasicFragment extends Fragment {
    boolean foto;

    public BasicFragment(boolean foto){
        this.foto = foto;
    }
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if(foto) return inflater.inflate(R.layout.fragment_basic2, container,false);
            else return inflater.inflate(R.layout.fragment_basic, container,false);
    }
}
