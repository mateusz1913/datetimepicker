#import "RNDateTimeInputPicker.h"
#import "RNDateTimePicker.h"
#import "RNDateTimePickerDelegate.h"

#import <React/RCTBridge.h>
#import <React/RCTUITextField.h>

@interface RNDateTimeInputPicker (RNDateTimePickerDelegate)

@property (nonatomic, copy) RCTBubblingEventBlock onChange;

- (void)didChangeWithDate:(NSDate*)date;

@end

@implementation RNDateTimeInputPicker
{
  RCTUITextField *_backedTextInputView;
}

- (instancetype)initWithBridge:(RCTBridge *)bridge
{
  if (self = [super initWithBridge:bridge]) {
    RNDateTimePicker *picker = [RNDateTimePicker new];
    if (@available(iOS 14.0, *)) {
        picker.preferredDatePickerStyle = UIDatePickerStyleWheels;
        [picker sizeToFit];
    }

    // `blurOnSubmit` defaults to `true` for <TextInput multiline={false}> by design.
    self.blurOnSubmit = YES;
    _backedTextInputView = [[RCTUITextField alloc] initWithFrame:self.bounds];
    _backedTextInputView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    _backedTextInputView.textInputDelegate = self;
    _backedTextInputView.inputView = picker;
    picker.delegate = self;

    [self addSubview:_backedTextInputView];
  }

  return self;
}

- (id<RCTBackedTextInputViewProtocol>)backedTextInputView
{
  return _backedTextInputView;
}

- (void)didChangeWithDate:(NSDate *)date
{
  if (self.onChange) {
    self.onChange(@{ @"timestamp": @(date.timeIntervalSince1970 * 1000.0) });
  }
}

- (void)textInputDidEndEditing
{
  [super textInputDidEndEditing];
  [self.backedTextInputView resignFirstResponder];
}

@end
