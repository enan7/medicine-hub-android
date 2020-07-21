package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.medic.Fragments.MedicineFragment;
import com.example.medic.R;

public class CustomiseToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_toolbar);

        Long categoryId = (getIntent().getExtras().getLong("CatID"));
        String clickid = (getIntent().getExtras().getString("ClickID"));

        Bundle bundle=new Bundle();
        bundle.putString("ClickID",clickid);
        bundle.putLong("CatID",categoryId);

        MedicineFragment ob=new MedicineFragment();
        ob.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,ob).addToBackStack(null).commit();

      /*  MedicineFragment medicinefragment =new MedicineFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,medicinefragment)
        .commit();*/
    }
}