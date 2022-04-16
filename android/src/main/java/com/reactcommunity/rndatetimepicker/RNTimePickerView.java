package com.reactcommunity.rndatetimepicker;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Calendar;

public class RNTimePickerView extends TimePicker {
  public static final String TAG = "RNTimePickerViewAndroid";
  private ThemedReactContext mContext;
//  public TimePicker timePicker;
  private Calendar mCalendar;

  public RNTimePickerView(ThemedReactContext context) {
    super(context);
    mContext = context;
    init();
  }

  public void init() {
    mCalendar = Calendar.getInstance();
//    inflate(mContext, R.layout.clock_rn_time_picker, this);
//    timePicker = findViewById(R.id.clock_rn_time_picker);
//    inflate(mContext, R.layout.spinner_rn_date_picker, this);
//    datePicker = findViewById(R.id.spinner_rn_date_picker);
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    this.setLayoutParams(layoutParams);
    Log.d(TAG, mCalendar.get(Calendar.YEAR) + " " + mCalendar.get(Calendar.MONTH) + " " + mCalendar.get(Calendar.DAY_OF_MONTH));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      this.setHour(mCalendar.get(Calendar.HOUR_OF_DAY));
      this.setMinute(mCalendar.get(Calendar.MINUTE));
    } else {
      this.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
      this.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
    }
    this.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
      @Override
      public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (hourOfDay == mCalendar.get(Calendar.HOUR_OF_DAY) && minute == mCalendar.get(Calendar.MINUTE)) {
          return;
        }
        WritableMap event = Arguments.createMap();
        event.putInt("minute", minute);
        event.putInt("hour", hourOfDay);
        Log.d(TAG, "onTimeChanged:" + event.toString());
        ((ThemedReactContext)getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onTimeChange", event);
      }
    });
  }

  public void update(Bundle args) {
    Log.d(TAG, "Date: " + args.toString());
    final RNDate date = new RNDate(args);
    Log.d(TAG, "RNDate: " + date.toString() + " " + date.hour() + " " + date.minute());
    mCalendar.set(date.year(), date.month(), date.day(), date.hour(), date.minute());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      this.setHour(mCalendar.get(Calendar.HOUR_OF_DAY));
      this.setMinute(mCalendar.get(Calendar.MINUTE));
    } else {
      this.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
      this.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
    }
  }
}
