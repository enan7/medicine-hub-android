package com.example.medic.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.medic.R;



public class ContactUsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        LinearLayout call = view.findViewById(R.id.con_call);
        LinearLayout email = view.findViewById(R.id.con_mail);
        LinearLayout website = view.findViewById(R.id.con_web);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "123456789"));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "waleedaahmedd@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Test mail");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello test mai");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.navistop.com"));
                startActivity(intent);

            }
        });
        return view;
    }


  /*  @Override
    public void onResume() {
        super.onResume();
        final HomeActivity activity = (HomeActivity) getActivity();
        if (activity != null) {
            activity.showBackButton();
            activity.hideDrawerButton();
            activity.createTitle("Contact Us");

            activity.BackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChildrenFragment fragment = new ChildrenFragment();
                    assert getFragmentManager() != null;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.add(R.id.home_fragment, fragment, "Home");
                    transaction.commit();
                }
            });
        }

    }*/
}