package com.gogovan.test.app.root;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.design.widget.RxNavigationView;
import com.shinetechina.demo.R;
import com.uber.rib.core.Initializer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView extends DrawerLayout implements RootInteractor.RootPresenter {

  public RootView(Context context) {
    this(context, null);
  }

  public RootView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RootView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  private ViewGroup mContentView;

  private NavigationView mNavigationView;

  private Toolbar mToolbar;
  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mToolbar = (Toolbar) findViewById(R.id.toolbar);

    mToolbar.setTitle(R.string.str_timer);
    mContentView = findViewById(R.id.content_layout);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            (Activity)getContext(), this, mToolbar, R.string.navigation_drawer_open, R.string
            .navigation_drawer_close);
    this.addDrawerListener(toggle);
    toggle.syncState();


  }

  public void setToolbarTitle(String title) {
     mToolbar.setTitle(title);

  }

  public void addPageView(View view) {

    mContentView.addView(view);

  }

  public void removePageView(View view) {
    mContentView.removeView(view);
  }



  @Override
  public Observable navItemSelectionRequest() {

    return RxNavigationView.itemSelections((NavigationView) findViewById(R.id.nav_view)).subscribeOn
            (AndroidSchedulers.mainThread()).doOnNext(o -> {
                setToolbarTitle(o.getTitle().toString());
                closeDrawer(Gravity.START);
             }).map(menuItem -> {
               int id = menuItem.getItemId();
              if(id == R.id.nav_time_task) {
                return NavMenuType.TIME;
              }else if(id == R.id.nav_history) {
                return NavMenuType.HISTORY;
              }else {
                return NavMenuType.NONE;
              }

    });

  }

}
