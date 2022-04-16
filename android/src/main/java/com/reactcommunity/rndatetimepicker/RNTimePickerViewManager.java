package com.reactcommunity.rndatetimepicker;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class RNTimePickerViewManager extends SimpleViewManager<RNTimePickerView> {
  @VisibleForTesting
  public static final String TAG = "RNTimePickerViewAndroid";

  private ReactApplicationContext mReactContext;

  public RNTimePickerViewManager(ReactApplicationContext reactContext) {
    mReactContext = reactContext;
  }
  @NonNull
  @Override
  public String getName() {
    return TAG;
  }

  @NonNull
  @Override
  protected RNTimePickerView createViewInstance(@NonNull final ThemedReactContext reactContext) {
    RNTimePickerView timePicker = new RNTimePickerView(reactContext);
    return timePicker;
  }

  @Nullable
  @Override
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        "onTimeChange",
        MapBuilder.of("registrationName", "onTimeChange")
    );
  }

  @ReactProp(name = RNConstants.ARG_VALUE)
  public void setValue(RNTimePickerView view, final double value) {
    Log.d(TAG, "value = " + value + " " + Integer.MAX_VALUE);
//    if (value == null) {
//      return;
//    }
    final Bundle args = new Bundle();
    args.putLong(RNConstants.ARG_VALUE, (long)value);
    view.update(args);
  }

  @ReactProp(name = RNConstants.ARG_DISABLED)
  public void setDisabled(RNTimePickerView view, final boolean disabled) {
//    view.timePicker.setEnabled(!disabled);
    view.setEnabled(!disabled);
  }

  @ReactProp(name = RNConstants.ARG_IS24HOUR)
  public void setIs24Hour(RNTimePickerView view, final boolean is24Hour) {
//    DateFormat.is24HourFormat(mReactContext);
    view.setIs24HourView(is24Hour);
  }
}
