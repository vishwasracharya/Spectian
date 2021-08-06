package com.example.spce.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.spce.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    private SliderLayout sliderLayout;
    private ImageView map;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_it, "Information Technology", "The Department of Information Technology has been strengthening its resources and providing state of the art facilities to foster the spirit of learning and development"));
        list.add(new BranchModel(R.drawable.ic_computer, "Computer Engineering", "Manufacture a solid research and instructing condition that reacts quickly to the difficulties of the 21st century. To make the most helpful environment"));
        list.add(new BranchModel(R.drawable.ic_mechanical, "Mechanical Engineering", "To enhance our Quality teaching and Create an education system with Innovative skills."));
        list.add(new BranchModel(R.drawable.ic_civil, "Civil Engineering", "The Department of Civil Engineering will provide unique educational and research opportunities for students and faculty that promote fundamental understanding"));
        list.add(new BranchModel(R.drawable.ic_electrical, "Electrical Engineering", "The department emphasizes towards imparting quality education, rigorous learning, hands-on expertise and helping students to shape their all-round personality"));

        adapter = new BranchAdapter(getContext(), list);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        setSliderViews();
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Sardar Patel College Of Engineering Bakrol Anand");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {
        for (int i=0; i<4; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/adminapp-f11cf.appspot.com/o/home-imgs%2F1.jpeg?alt=media&token=769739c6-4896-4e0a-8dc8-c0bba91f9d47");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/adminapp-f11cf.appspot.com/o/home-imgs%2F2.jpeg?alt=media&token=50e204af-e781-4708-979c-165d7c221d10");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/adminapp-f11cf.appspot.com/o/home-imgs%2F3.jpeg?alt=media&token=b083a8ce-c747-4fae-8190-c6c19dd8cb79");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/adminapp-f11cf.appspot.com/o/home-imgs%2F4.jpeg?alt=media&token=0ba1e5e4-93e8-43ea-849f-dd4e9e5b6538");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/adminapp-f11cf.appspot.com/o/home-imgs%2F5.jpeg?alt=media&token=2b97baab-939a-4616-bec4-360b10cf0e50");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            sliderLayout.addSliderView(sliderView);

        }
    }

}