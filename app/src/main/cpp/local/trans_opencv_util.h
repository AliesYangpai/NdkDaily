/**
 * 本地转换的工具类
 */

#ifndef NDKDAILY_TRANS_OPENCV_UTIL_H
#define NDKDAILY_TRANS_OPENCV_UTIL_H
#include <jni.h>
#include <string>
#include "opencv2/opencv.hpp"
#include <android/log.h>

using namespace std;
using namespace cv;

/**
 * src_byte_array to matSrc
 * @param src_byte_array
 * @param src_length
 * @param matSrc
 */
void trans_byte_array_to_mat_src(jbyte* src_byte_array,int src_length,Mat* matSrc);

/**
 * src_byte_array to matSrc
 * @param src_byte_array
 * @param src_length
 * @param matSrc
 */
void trans_byte_array_to_mat_src(char* const src_byte_array,int src_length,Mat* matSrc);

/**
 * dst_byte_array to matRgba (感觉没必要，～～～后面再看吧)
 * @param dst_byte_array
 * @param matRgba
 */
void trans_mat_rgba_to_byte_array(char* const dst_byte_array,Mat* matRgba);
#endif //NDKDAILY_TRANS_OPENCV_UTIL_H
