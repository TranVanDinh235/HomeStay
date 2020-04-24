package com.example.homestay.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.homestay.R;
import com.example.homestay.data.DataManager;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.info.InfoActivity;
import com.example.homestay.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

@PerActivity
public class ProfileFragment extends BaseFragment implements ProfileView{

    @Inject
    ProfilePresenter<ProfileView> mPresenter;

    @BindView(R.id.layout_profile_pic)
    CircleImageView picImageView;

    @BindView(R.id.layout_profile_navigate_info)
    ConstraintLayout infoNavigateLayout;

    @BindView(R.id.layout_profile_login)
    CardView loginCardView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


                ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
        }
        setUp(root);

        return root;
    }

    @Override
    protected void setUp(View root) {
        if(mPresenter.isUserLoggedInMode()){
            loginCardView.setVisibility(View.GONE);
            infoNavigateLayout.setVisibility(View.VISIBLE);
        }

        infoNavigateLayout.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            startActivityForResult(intent, 100);
        });
    }
}
