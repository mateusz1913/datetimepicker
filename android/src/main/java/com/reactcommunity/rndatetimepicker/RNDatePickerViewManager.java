package com.reactcommunity.rndatetimepicker;

import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class RNDatePickerViewManager extends ViewGroupManager<RNDatePickerView> {
  @VisibleForTesting
  public static final String TAG = "RNDatePickerViewAndroid";

  @NonNull
  @Override
  public String getName() {
    return TAG;
  }

  @NonNull
  @Override
  protected RNDatePickerView createViewInstance(@NonNull final ThemedReactContext reactContext) {
    RNDatePickerView datePicker = new RNDatePickerView(reactContext);
    return datePicker;
  }

  @Override
  public boolean needsCustomLayoutForChildren() {
    return true;
  }

  @Nullable
  @Override
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        "onDateChange",
        MapBuilder.of("registrationName", "onDateChange")
    );
  }

  @ReactProp(name = RNConstants.ARG_VALUE)
  public void setValue(RNDatePickerView view, final double value) {
    final Bundle args = new Bundle();
    args.putLong(RNConstants.ARG_VALUE, (long)value);
    view.update(args);
  }

  @Override
  public void setBackgroundColor(@NonNull RNDatePickerView view, int backgroundColor) {
    // DO NOTHING;
  }

  //  @ReactProp(name = RNConstants.ARG_MINDATE, defaultDouble = RNConstants.DEFAULT_MIN_DATE)
//  public void setMinimumDate(RNDatePickerView view, final double value) {
//    view.datePicker.setMinDate((long)value);
//  }
//
//  @ReactProp(name = RNConstants.ARG_MAXDATE, defaultDouble = -1.0)
//  public void setMaximumDate(RNDatePickerView view, final double value) {
//    if (value == -1.0) {
//      return;
//    }
//    view.datePicker.setMaxDate((long) value);
//  }

  @ReactProp(name = RNConstants.ARG_FIRST_DAY_OF_WEEK)
  public void setFirstDayOfWeek(RNDatePickerView view, final int value) {
    // Originally that prop was added to Windows implementation where days
    // are enumerated starting from 0 instead of 1
    view.setFirstDayOfWeek(value + 1);
  }

  @ReactProp(name = RNConstants.ARG_DISABLED, defaultBoolean = false)
  public void setDisabled(RNDatePickerView view, final boolean disabled) {
    view.setEnabled(!disabled);
  }

//  @ReactProp(name = RNConstants.ARG_DISPLAY)
//  public void setDisplay(RNDatePickerView view, final String pickerDisplay) {
//    RNDatePickerDisplay display = RNDatePickerDisplay.valueOf(pickerDisplay.toUpperCase(Locale.US));
////    switch (display) {
////      case CALENDAR:
////
////    }
//  }
}
