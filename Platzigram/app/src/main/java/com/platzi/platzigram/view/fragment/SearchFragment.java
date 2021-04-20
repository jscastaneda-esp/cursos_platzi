package com.platzi.platzigram.view.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.view.adapter.PictureAdapterRecyclerView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Crashlytics.log("Initialize class " + TAG);

        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        SearchView searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureSearch);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(gridLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(this.buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);
        picturesRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if ((parent.getChildLayoutPosition(view) % 2) == 0) {
                    outRect.right = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
                }
            }
        });

        return view;
    }

    private List<Picture> buildPictures() {
        String textDays = getResources().getString(R.string.text_days);

        return Arrays.asList(
                new Picture("https://www.ecestaticos.com/imagestatic/clipping/e95/e16/e95e161ea3f4d75cc491e6657488c48d/veinte-paisajes-incomparables-de-google-maps-para-viajar-sin-levantarte-del-sillon.jpg?mtime=1471015070",
                        "Uriel Ramírez", String.format("4 %s", textDays), 3),
                new Picture("https://cupe.ca/sites/cupe/files/persons_with_disabilities_2016.jpg",
                        "Juan Pablo", String.format("3 %s", textDays), 10),
                new Picture("https://lh3.googleusercontent.com/l7JT-E53VzSCBR1XaPqLg8sMUYlu_VIxWAU4FxbdCS3aBgxldW3_-zzXNg1FxjDBT46q=w720-h310",
                        "Jonathan Castañeda", String.format("2 %s", textDays), 9),
                new Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3a9NVEGRV6ElRwLZItl_LrAe1sv9I27qvOy8VGtOPW_UM5M_jeQ",
                        "Prueba Scroll", String.format("10 %s", textDays), 17),
                new Picture("https://www.ecestaticos.com/imagestatic/clipping/e95/e16/e95e161ea3f4d75cc491e6657488c48d/veinte-paisajes-incomparables-de-google-maps-para-viajar-sin-levantarte-del-sillon.jpg?mtime=1471015070",
                        "Uriel Ramírez", String.format("4 %s", textDays), 3),
                new Picture("https://cupe.ca/sites/cupe/files/persons_with_disabilities_2016.jpg",
                        "Juan Pablo", String.format("3 %s", textDays), 10),
                new Picture("https://lh3.googleusercontent.com/l7JT-E53VzSCBR1XaPqLg8sMUYlu_VIxWAU4FxbdCS3aBgxldW3_-zzXNg1FxjDBT46q=w720-h310",
                        "Jonathan Castañeda", String.format("2 %s", textDays), 9),
                new Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3a9NVEGRV6ElRwLZItl_LrAe1sv9I27qvOy8VGtOPW_UM5M_jeQ",
                        "Prueba Scroll", String.format("10 %s", textDays), 17),
                new Picture("https://www.ecestaticos.com/imagestatic/clipping/e95/e16/e95e161ea3f4d75cc491e6657488c48d/veinte-paisajes-incomparables-de-google-maps-para-viajar-sin-levantarte-del-sillon.jpg?mtime=1471015070",
                        "Uriel Ramírez", String.format("4 %s", textDays), 3),
                new Picture("https://cupe.ca/sites/cupe/files/persons_with_disabilities_2016.jpg",
                        "Juan Pablo", String.format("3 %s", textDays), 10),
                new Picture("https://lh3.googleusercontent.com/l7JT-E53VzSCBR1XaPqLg8sMUYlu_VIxWAU4FxbdCS3aBgxldW3_-zzXNg1FxjDBT46q=w720-h310",
                        "Jonathan Castañeda", String.format("2 %s", textDays), 9),
                new Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3a9NVEGRV6ElRwLZItl_LrAe1sv9I27qvOy8VGtOPW_UM5M_jeQ",
                        "Prueba Scroll", String.format("10 %s", textDays), 17)
        );
    }
}
