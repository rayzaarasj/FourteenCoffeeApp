#include "jni.h"
#include "stdlib.h"

JNIEXPORT jint JNICALL Java_id_ac_ui_cs_mobileprogramming_rayzaarasj_fourteencoffee_fragments_OrderDetailFragment_sumIntArr (JNIEnv* env, jobject obj, jintArray arr) {

    jint *c_array;
    jint sum = 0;
    c_array = (*env)->GetIntArrayElements(env, arr, NULL);

    if (c_array == NULL) {
        return -1;
    }

    jsize len = (*env)->GetArrayLength(env, arr);
    for (int i = 0; i < len; i++) {
        sum += c_array[i];
    }

    (*env)->ReleaseIntArrayElements(env, arr, c_array, 0);

    return sum;
}