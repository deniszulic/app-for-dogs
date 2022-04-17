package com.example.dog_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class home extends Fragment {
    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2=view.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        int[] images={R.drawable.firstpic,R.drawable.secondpic,R.drawable.thirdpic};
        String[] heading ={"Dobrodošao na aplikaciju za udomljavanje i prijavu nestanka psa","Registracija","Oglasi"};
        String[] desc={"","U ovoj aplikaciji se mogu registrirati korisnici i azili",
        "Pregledajte ili postavite oglase za udomljavanje ili prijavu nestanka psa"};
        viewPagerItemArrayList=new ArrayList<>();
        for(int i=0;i<images.length;i++){
            ViewPagerItem viewPagerItem=new ViewPagerItem(images[i],heading[i],desc[i]);
            viewPagerItemArrayList.add(viewPagerItem);
        }
        VPAdapter vpAdapter=new VPAdapter(viewPagerItemArrayList);
        viewPager2.setAdapter(vpAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {}
        ).attach();

        return view;
    }
}