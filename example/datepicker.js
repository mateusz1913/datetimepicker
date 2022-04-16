import React from 'react';
import {requireNativeComponent} from 'react-native';

const NativeDatePicker = requireNativeComponent('RNDatePickerViewAndroid');

const DatePicker = ({value, minimumDate, maximumDate, ...props}) => {
  const valueTimestamp = value?.getTime();
  const minimumDateTimestamp = minimumDate?.getTime();
  const maximumDateTimestamp = maximumDate?.getTime();
  return (
    <NativeDatePicker
      {...props}
      value={valueTimestamp}
      minimumDate={minimumDateTimestamp}
      maximumDate={maximumDateTimestamp}
    />
  );
};

export default DatePicker;
