package com.example.homestay.ui.info;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.homestay.R;
import com.google.android.material.appbar.AppBarLayout;

import okhttp3.Interceptor;


public class ToolbarBehavior extends CoordinatorLayout.Behavior<AppBarLayout> {
    private final String TAG = "Scroll";

    private FrameLayout toolbar;
    private LinearLayout backLayout;
    private LinearLayout titleLayout;
    private ImageView backImageView;

    private float toolbarOriginHeight = -1f;
    private float toolbarCollapsedHeight = -1f;
    private float minScale = 0.4f;

    private boolean viewsSet = false;

    private void getView(AppBarLayout child){
        if(viewsSet) return;
        viewsSet = true;

        toolbar = child.findViewById(R.id.layout_info_appbar_container);
        backLayout = child.findViewById(R.id.layout_info_back);
        titleLayout = child.findViewById(R.id.layout_info_title);
        backImageView = child.findViewById(R.id.layout_info_back_icon);

        toolbarOriginHeight = (float) toolbar.getLayoutParams().height;
        toolbarCollapsedHeight = toolbarOriginHeight * minScale;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        getView(child);
        return axes == View.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        getView(child);

        // vuot len
        if(dyConsumed > 0){


            if(toolbar.getLayoutParams().height > toolbarCollapsedHeight){

                float height = toolbar.getLayoutParams().height - dyConsumed;
                toolbar.getLayoutParams().height = Math.round(Math.max(height, toolbarCollapsedHeight));
                toolbar.requestLayout();

                float translate1 = (toolbarOriginHeight - toolbar.getLayoutParams().height)/(toolbarOriginHeight - toolbarCollapsedHeight);
                translate1 *= toolbarOriginHeight;
                backLayout.setTranslationY(-translate1);

                float translate2 = (toolbarOriginHeight - toolbar.getLayoutParams().height)/(toolbarOriginHeight - toolbarCollapsedHeight);
                translate2 *= toolbarCollapsedHeight;
                backImageView.setTranslationX(translate2);

                float translate3 = (toolbarOriginHeight - toolbar.getLayoutParams().height)/(toolbarOriginHeight - toolbarCollapsedHeight);
                translate3 *= toolbarCollapsedHeight;
                titleLayout.setTranslationX(translate3);

//                float alpha = (toolbar.getLayoutParams().height - toolbarCollapsedHeight)/(toolbarOriginHeight - toolbarCollapsedHeight);
//                backImageView.animate().alpha(alpha);


            }
        } else if(dyUnconsumed < 0){
            if(toolbar.getLayoutParams().height < toolbarOriginHeight){
                float height = toolbar.getLayoutParams().height - dyUnconsumed;
                toolbar.getLayoutParams().height = Math.round(Math.min(toolbarOriginHeight, height));
                toolbar.requestLayout();

                float translate1 = (toolbarOriginHeight - toolbar.getLayoutParams().height) / (toolbarOriginHeight - toolbarCollapsedHeight);
                translate1 *= toolbarOriginHeight;
                backLayout.setTranslationY(-translate1);

                float translate2 = (toolbarOriginHeight - toolbar.getLayoutParams().height)/(toolbarOriginHeight - toolbarCollapsedHeight);
                translate2 *= toolbarCollapsedHeight;
                backImageView.setTranslationX(translate2);

                float translate3 = (toolbarOriginHeight - toolbar.getLayoutParams().height)/(toolbarOriginHeight - toolbarCollapsedHeight);
                translate3 *= toolbarCollapsedHeight;
                titleLayout.setTranslationX(translate3);

                //--- title

//                float alpha = (toolbar.getLayoutParams().height - toolbarCollapsedHeight)/(toolbarOriginHeight - toolbarCollapsedHeight);
//                backImageView.animate().alpha(alpha);

            }
        }
    }
}
