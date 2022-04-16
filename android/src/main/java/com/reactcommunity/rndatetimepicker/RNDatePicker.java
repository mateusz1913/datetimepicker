package com.reactcommunity.rndatetimepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;

public class RNDatePicker extends DatePicker {
  public RNDatePicker(Context context) {
    super(context);
  }

  public RNDatePicker(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RNDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

//  @Override
//  public boolean onInterceptTouchEvent(MotionEvent ev) {
//    ViewParent parentView = getParent();
//
//    if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
//      if (parentView != null) {
//        parentView.requestDisallowInterceptTouchEvent(true);
//      }
//    }
//
//    return false;
//  }
//
//  @Override
//  protected void onConfigurationChanged(Configuration newConfig) {
//    super.onConfigurationChanged(newConfig);
//    Log.d("RNDatePickerViewAndroid", newConfig.toString());
//    requestLayout();
//  }
}
