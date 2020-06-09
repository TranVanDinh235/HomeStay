package com.example.homestay.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.entity.User;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.house.HouseActivity;
import com.example.homestay.ui.info.InfoActivity;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.ui.setting.SettingActivity;
import com.example.homestay.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

@PerActivity
public class ProfileFragment extends BaseFragment implements ProfileView{

    @Inject
    ProfilePresenter<ProfileView> mPresenter;

    @BindView(R.id.layout_profile_photo)
    CircleImageView mPhotoImageView;

    @BindView(R.id.layout_profile_user_name)
    TextView mUsernameTextView;

    private User mUser;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


                ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
        }
        return root;
    }

    @Override
    protected void setUp(View root) {
        mPresenter.getUserData();
    }

    @OnClick(R.id.layout_profile_user_name)
    void onUserNameClick(View view){
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(AppConstants.TAG_DATA_USER, new Gson().toJson(mUser));
        startActivity(intent);
    }

    @OnClick(R.id.layout_setting_help)
    void onSettingClick(View view){
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadData(User user) {
        this.mUser = user;
        if(user.getPic() != null) {
            Glide.with(getActivity())
                    .load(user.getPic())
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoImageView);
        }

        if(user.getName() != null){
            mUsernameTextView.setText(user.getName());
        }
    }
}
