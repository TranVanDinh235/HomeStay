package com.example.homestay.ui.info;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.User;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.utils.AppConstants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends BaseActivity implements InfoView{

    @Inject
    InfoPresenter<InfoView> mPresenter;

    @BindView(R.id.layout_info_photo)
    CircleImageView mPhotoImageView;

    @BindView(R.id.layout_info_name)
    EditText mUsernameTextView;

    @BindView(R.id.layout_info_email)
    EditText mEmailTextView;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(InfoActivity.this);

        setUp();
    }
    @Override
    protected void setUp() {
        if (getIntent() == null) return;
        String json = getIntent().getStringExtra(AppConstants.TAG_DATA_USER);
        Type type = new TypeToken<User>() {
        }.getType();
        this.mUser = new Gson().fromJson(json, type);

        initData();
    }

    void initData(){
        if(mUser.getPic() != null) {
            Glide.with(this)
                    .load(mUser.getPic())
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoImageView);
        }

        if(mUser.getName() != null){
            mUsernameTextView.setText(mUser.getName());
        }

        if(mUser.getEmail() != null){
            mEmailTextView.setText(mUser.getEmail());
        }
    }
}
