// NIM   : 10119033
// Nama  : Dandi Ahmadin
// Kelas : IF-1

package com.dandiahmadin.notesapp.fragment.viewpager2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dandiahmadin.notesapp.R;

public class FirstViewPagerFragment extends Fragment {

    public FirstViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_view_pager, container, false);
    }
}