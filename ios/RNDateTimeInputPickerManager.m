#import "RNDateTimeInputPickerManager.h"
#import "RNDateTimeInputPicker.h"
#import "RNDateTimePicker.h"

#import <React/RCTBaseTextInputShadowView.h>

@implementation RCTConvert(UIDatePicker)

RCT_ENUM_CONVERTER(UIDatePickerMode, (@{
  @"time": @(UIDatePickerModeTime),
  @"date": @(UIDatePickerModeDate),
  @"datetime": @(UIDatePickerModeDateAndTime),
}), UIDatePickerModeTime, integerValue)

RCT_ENUM_CONVERTER(UIDatePickerStyle, (@{
    @"default": @(UIDatePickerStyleAutomatic),
    @"compact": @(UIDatePickerStyleCompact),
    @"spinner": @(UIDatePickerStyleWheels),
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= 140000
    @"inline": @(UIDatePickerStyleInline),
#endif
}), UIDatePickerStyleAutomatic, integerValue)

@end

@implementation RNDateTimeInputPickerManager

RCT_EXPORT_MODULE()

- (RCTShadowView *)shadowView
{
  RCTBaseTextInputShadowView *shadowView =
    (RCTBaseTextInputShadowView *)[super shadowView];

  shadowView.maximumNumberOfLines = 1;

  return shadowView;
}

- (UIView *)view
{
  return [[RNDateTimeInputPicker alloc] initWithBridge:self.bridge];
}

RCT_CUSTOM_VIEW_PROPERTY(date, NSDate, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).date = [RCTConvert NSDate:json];
}

RCT_CUSTOM_VIEW_PROPERTY(locale, NSLocale, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).locale = [RCTConvert NSLocale:json];
}

RCT_CUSTOM_VIEW_PROPERTY(minimumDate, NSDate, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).minimumDate = [RCTConvert NSDate:json];
}

RCT_CUSTOM_VIEW_PROPERTY(maximumDate, NSDate, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).maximumDate = [RCTConvert NSDate:json];
}

RCT_CUSTOM_VIEW_PROPERTY(minuteInterval, NSInteger, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).minuteInterval = [json intValue];
}

RCT_CUSTOM_VIEW_PROPERTY(mode, UIDatePickerMode, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).datePickerMode = [RCTConvert UIDatePickerMode:json];
}

RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)

RCT_CUSTOM_VIEW_PROPERTY(timeZoneOffsetInMinutes, NSTimeZone, RNDateTimeInputPicker)
{
  ((RNDateTimePicker*)view.backedTextInputView.inputView).timeZone = [RCTConvert NSTimeZone:json];
}

RCT_CUSTOM_VIEW_PROPERTY(textColor, UIColor, RNDateTimeInputPicker)
{
  if (@available(iOS 14.0, *) && ((RNDateTimePicker*)view.backedTextInputView.inputView).datePickerStyle != UIDatePickerStyleWheels) {
    // prevents #247
    return;
  }
  if (json) {
    [((RNDateTimePicker*)view.backedTextInputView.inputView) setValue:[RCTConvert UIColor:json] forKey:@"textColor"];
    [((RNDateTimePicker*)view.backedTextInputView.inputView) setValue:@(NO) forKey:@"highlightsToday"];
  } else {
    UIColor* defaultColor;
    if (@available(iOS 13.0, *)) {
        defaultColor = [UIColor labelColor];
    } else {
        defaultColor = [UIColor blackColor];
    }
    [((RNDateTimePicker*)view.backedTextInputView.inputView) setValue:defaultColor forKey:@"textColor"];
    [((RNDateTimePicker*)view.backedTextInputView.inputView) setValue:@(YES) forKey:@"highlightsToday"];
  }
}

@end
