LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := fourteencoffee
LOCAL_SRC_FILES := fourteencoffee.c
include $(BUILD_SHARED_LIBRARY)