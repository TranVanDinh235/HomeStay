package com.example.homestay.ui.search;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.data.network.entity.SearchResult;
import com.example.homestay.ui.base.BaseActivity;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;
import com.otaliastudios.autocomplete.AutocompletePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchView{

    @Inject
    SearchPresenter<SearchView> mPresenter;

    @Inject
    AutocompletePresenter<SearchResult> mSearchBarPresenter;

    @BindView(R.id.layout_search_edt_search)
    EditText mSearchEditText;

    @BindView(R.id.layout_search_icon_search)
    ImageView mSearchIconImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        float elevation = 6f;
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        AutocompleteCallback<SearchResult> callback = new AutocompleteCallback<SearchResult>() {
            @Override
            public boolean onPopupItemClicked(@NonNull Editable editable, @NonNull SearchResult item) {
                editable.clear();
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {}
        };

        Autocomplete mAutocomplete = Autocomplete.<SearchResult>on(mSearchEditText)
                .with(elevation)
                .with(backgroundDrawable)
                .with(mSearchBarPresenter)
                .with(callback)
                .build();

        mSearchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) mSearchIconImageView.setVisibility(View.GONE);
                else mSearchIconImageView.setVisibility(View.VISIBLE);
            }
        });
    }
}
