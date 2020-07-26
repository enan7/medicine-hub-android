package com.example.medic.Activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medic.Fragments.MedicineDetailFragment;
import com.example.medic.Fragments.MedicineFragment;
import com.example.medic.R;

public class CustomiseToolbar extends AppCompatActivity {

    ImageView CartImageBtn,BackButton;
    static TextView CartCountTv;
    private static int count = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_toolbar);



        BackButton = findViewById(R.id.back_btn);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomiseToolbar.super.onBackPressed();
            }
        });

        Long categoryId = (getIntent().getExtras().getLong("CatID"));
        String clickid = (getIntent().getExtras().getString("ClickID"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*toolbar.setLogo(R.drawable.icon);*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        Bundle bundle=new Bundle();
        bundle.putString("ClickID",clickid);
        bundle.putLong("CatID",categoryId);

        MedicineFragment medicine=new MedicineFragment();
        medicine.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,medicine)
                /*.addToBackStack(null)*/
                .commit();

      /*  MedicineFragment medicinefragment =new MedicineFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,medicinefragment)
        .commit();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem = menu.findItem(R.id.cart_count_menu_item);
        View actionView = mCartIconMenuItem.getActionView();
        if (actionView != null) {
            CartImageBtn = actionView.findViewById(R.id.cart_image_button);

            CartCountTv = actionView.findViewById(R.id.count_tv_layout);
            CartCountTv.setText(String.valueOf(count));
        }


        //Cart toolbar image view
        CartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    public void increaseCount() {
        ++count;
        CartCountTv.setText(String.valueOf(count));

    }
}