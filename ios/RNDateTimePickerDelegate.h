#import <Foundation/Foundation.h>

@protocol RNDateTimePickerDelegate <NSObject>

- (void)didChangeWithDate:(NSDate*)date;

@end
