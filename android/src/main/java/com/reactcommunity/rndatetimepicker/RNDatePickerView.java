package com.reactcommunity.rndatetimepicker;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Calendar;

public class RNDatePickerView extends DatePicker {
  public static final String TAG = "RNDatePickerViewAndroid";
  private ThemedReactContext mContext;
//  public DatePicker datePicker;
  private Calendar mCalendar;

  public RNDatePickerView(ThemedReactContext context) {
    super(context);
    mContext = context;
    init();
  }

  public void init() {
    mCalendar = Calendar.getInstance();
//    datePicker = new DatePicker(mContext);
//    inflate(mContext, R.layout.calendar_rn_date_picker, this);
//    datePicker = findViewById(R.id.calendar_rn_date_picker);
//    inflate(mContext, R.layout.spinner_rn_date_picker, this);
//    datePicker = findViewById(R.id.spinner_rn_date_picker);
    Log.d(TAG, mCalendar.get(Calendar.YEAR) + " " + mCalendar.get(Calendar.MONTH) + " " + mCalendar.get(Calendar.DAY_OF_MONTH));
//    datePicker.getTouchables().get(0).performClick();
//    datePicker.getTouchables().get(1).performClick();
    this.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
      @Override
      public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (year == mCalendar.get(Calendar.YEAR) && monthOfYear == mCalendar.get(Calendar.MONTH) && dayOfMonth == mCalendar.get(Calendar.DAY_OF_MONTH)) {
          return;
        }
        WritableMap event = Arguments.createMap();
        event.putInt("year", year);
        event.putInt("month", monthOfYear);
        event.putInt("day", dayOfMonth);
        Log.d(TAG, "onDateChanged:" + event.toString());
        ((ThemedReactContext)getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDateChange", event);
      }
    });
//    datePicker.requestLayout();
//    requestLayout();
  }

//  @Override
//  protected void onConfigurationChanged(Configuration newConfig) {
//    super.onConfigurationChanged(newConfig);
//    Log.d("RNDatePickerViewAndroid", newConfig.toString());
//    inflate(mContext, R.layout.calendar_rn_date_picker, this);
//    datePicker = findViewById(R.id.calendar_rn_date_picker);
//    datePicker.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
//      @Override
//      public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        if (year == mCalendar.get(Calendar.YEAR) && monthOfYear == mCalendar.get(Calendar.MONTH) && dayOfMonth == mCalendar.get(Calendar.DAY_OF_MONTH)) {
//          return;
//        }
//        WritableMap event = Arguments.createMap();
//        event.putInt("year", year);
//        event.putInt("month", monthOfYear);
//        event.putInt("day", dayOfMonth);
//        Log.d(TAG, "onDateChanged:" + event.toString());
//        ((ThemedReactContext)getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDateChange", event);
//      }
//    });
//    datePicker.requestLayout();
//    requestLayout();
//  }

  public void update(Bundle args) {
    Log.d(TAG, "Date: " + args.toString());
    final RNDate date = new RNDate(args);
    Log.d(TAG, "RNDate: " + date.toString() + " " + date.year() + " " + date.month() + " " + date.day());
    mCalendar.set(date.year(), date.month(), date.day());
    this.updateDate(date.year(), date.month(), date.day());
  }
}
