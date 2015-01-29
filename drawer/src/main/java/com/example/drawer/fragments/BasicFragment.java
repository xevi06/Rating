package com.example.drawer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawer.R;

/**
 * Created by Xevi on 29/01/2015.
 */
public class BasicFragment extends Fragment {
    boolean b;

    public BasicFragment(boolean b){
        this.b = b;
    }
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if(b) return inflater.inflate(R.layout.fragment_basic2, container,false);
            else return inflater.inflate(R.layout.fragment_basic, container,false);
    }
}
