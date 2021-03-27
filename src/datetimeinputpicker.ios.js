import React, {useLayoutEffect, useRef} from 'react';
import {Platform} from 'react-native';

import RNDateTimeInputPicker from './inputpicker';

const TextAncestor = require('react-native/Libraries/Text/TextAncestor');
const TextInputState = require('react-native/Libraries/Components/TextInput/TextInputState');
const setAndForwardRef = require('react-native/Libraries/Utilities/setAndForwardRef');
const usePressability = require('react-native/Libraries/Pressability/usePressability');

function InternalDateTimeInputPicker(props) {
  const inputRef = useRef(null);

  useLayoutEffect(() => {
    const inputRefValue = inputRef.current;

    if (inputRefValue != null) {
      TextInputState.registerInput(inputRefValue);

      return () => {
        TextInputState.unregisterInput(inputRefValue);

        if (TextInputState.currentlyFocusedInput() === inputRefValue) {
          inputRefValue?.blur();
        }
      };
    }
  }, [inputRef]);

  function clear() {
    // if (inputRef.current != null) {
    //   viewCommands.setTextAndSelection(
    //     inputRef.current,
    //     mostRecentEventCount,
    //     '',
    //     0,
    //     0,
    //   );
    // }
  }

  function isFocused() {
    return TextInputState.currentlyFocusedInput() === inputRef.current;
  }

  function getNativeRef() {
    return inputRef.current;
  }

  const _setNativeRef = setAndForwardRef({
    getForwardedRef: () => props.forwardedRef,
    setLocalRef: (ref) => {
      inputRef.current = ref;

      if (ref) {
        ref.clear = clear;
        ref.isFocused = isFocused;
        ref.getNativeRef = getNativeRef;
      }
    },
  });

  const _onFocus = (event) => {
    TextInputState.focusInput(inputRef.current);
    if (props.onFocus) {
      props.onFocus(event);
    }
  };

  const _onBlur = (event) => {
    TextInputState.blurInput(inputRef.current);
    if (props.onBlur) {
      props.onBlur(event);
    }
  };

  const accessible = props.accessible !== false;
  const focusable = props.focusable !== false;

  const config = React.useMemo(
    () => ({
      onPress: (event) => {
        if (props.editable !== false) {
          inputRef.current?.focus();
        }
      },
      onPressIn: props.onPressIn,
      onPressOut: props.onPressOut,
      cancelable:
        Platform.OS === 'ios' ? !props.rejectResponderTermination : null,
    }),
    [
      props.editable,
      props.onPressIn,
      props.onPressOut,
      props.rejectResponderTermination,
    ],
  );

  // TextInput handles onBlur and onFocus events
  // so omitting onBlur and onFocus pressability handlers here.
  const {onBlur, onFocus, ...eventHandlers} = usePressability(config) || {};

  return (
    <TextAncestor.Provider value={true}>
      <RNDateTimeInputPicker
        ref={_setNativeRef}
        {...props}
        {...eventHandlers}
        accessible={accessible}
        blurOnSubmit={true}
        caretHidden={false}
        dataDetectorTypes={props.dataDetectorTypes}
        focusable={focusable}
        mostRecentEventCount={0}
        onBlur={_onBlur}
        // onChange={_onChange}
        onContentSizeChange={props.onContentSizeChange}
        onFocus={_onFocus}
        // onScroll={_onScroll}
        // onSelectionChange={_onSelectionChange}
        // onSelectionChangeShouldSetResponder={emptyFunctionThatReturnsTrue}
        // selection={selection}
        style={props.style}
        // text={text}
      />
    </TextAncestor.Provider>
  );
}
