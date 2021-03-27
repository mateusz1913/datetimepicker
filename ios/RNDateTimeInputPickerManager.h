#import <React/RCTConvert.h>
#import <React/RCTBaseTextInputViewManager.h>

@interface RCTConvert(UIDatePicker)

+ (UIDatePickerMode)UIDatePickerMode:(id)json;

@end

@interface RNDateTimeInputPickerManager : RCTBaseTextInputViewManager

@end
