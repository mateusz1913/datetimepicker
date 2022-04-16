import React from 'react';
import {requireNativeComponent} from 'react-native';

const NativeTimePicker = requireNativeComponent('RNTimePickerViewAndroid');

const TimePicker = ({value, minimumDate, maximumDate, ...props}) => {
  const valueTimestamp = value?.getTime();
  return <NativeTimePicker {...props} value={valueTimestamp} />;
};

export default TimePicker;
