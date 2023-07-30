#include <jni.h>
#include <string>
#include "opencv2/opencv.hpp"
#include <android/log.h>
#include "local/trans_opencv_util.h"

#define TAG "ndkWork"
// __VA_ARGS__ 内置的宏，可进行替换
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__); //log i类型
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__); //log d类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__); //log e类型
using namespace std;
using namespace cv;

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork01(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jbyteArray src) {
    // 1.根据src byteArray -> matSrc
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, 0);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    LOGD("dailyWork01 matSrc.channel = %d allSize = %d", matSrc->channels(),
         matSrc->channels() * matSrc->total())
    delete pVt;

    // 2.将matSrc 转换格式为rgba
    Mat *matDst = new Mat();
    cvtColor(*matSrc, *matDst, COLOR_BGR2RGBA);
    LOGD("dailyWork01 matDst.channel = %d allSize = %d", matDst->channels(),
         matDst->channels() * matDst->total())
    delete matSrc;
    // 3.写回dst byteArray
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    int dstLength = matDst->total() * matDst->channels();
    memcpy(pDst, matDst->data, dstLength);
    delete matDst;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork02(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    // 1.将原图src字节数组转化为mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, 0);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    // 2.将mat转换为rgba
    Mat *matRgba = new Mat();
    cvtColor(*matSrc, *matRgba, COLOR_BGR2RGBA);

    for (int i = 0; i < matRgba->rows; ++i) {
        for (int j = 0; j < matRgba->cols; ++j) {
            Vec4b rgba = matRgba->at<Vec4b>(i, j);
            uchar r = matRgba->at<Vec4b>(i, j)[0];
            uchar g = matRgba->at<Vec4b>(i, j)[1];
            uchar b = matRgba->at<Vec4b>(i, j)[2];
            uchar a = matRgba->at<Vec4b>(i, j)[3];
            LOGD("dailyWork02 rgba r=%d,g=%d,b=%d,a=%d", r, g, b, a)
        }
    }


    delete matSrc;

    // 3.将data写回到dst中
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    int dstLength = matRgba->total() * matRgba->channels();
    memcpy(pDst, matRgba->data, dstLength);
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork03(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jint rows,
                                              jint cols) {

    // 1 初始化一张单通道图片并且像素值为20
    Scalar *scalar = new Scalar(20, 20, 20);
    Mat *mat = new Mat(rows, cols, CV_8UC3, &scalar);
    Mat *matRgba = new Mat();
    cvtColor(*mat, *matRgba, COLOR_BGR2RGBA);
    delete scalar;
    delete mat;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    int dstSize = matRgba->total() * matRgba->channels();
    memcpy(pDst, matRgba->data, dstSize);
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork04(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jint dst_row,
                                              jint dst_col,
                                              jbyteArray src) {
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pv = new vector<char>(srcLength);
    memcpy(pv->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pv, IMREAD_COLOR, matSrc);
    delete pv;

    Mat *matResize = new Mat();
    resize(*matSrc, *matResize, Size(dst_col, dst_row));
    delete matSrc;

    Mat *matDst = new Mat();
    cvtColor(*matResize, *matDst, COLOR_BGR2RGBA);
    delete matResize;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    int dstLength = matDst->total() * matDst->channels();
    memcpy(pDst, matDst->data, dstLength);
    delete matDst;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork05(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {

    //1.将src转化为mat
    //1.1读取src到mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize lengthSrc = env->GetArrayLength(src);
    vector<char> *vt = new vector<char>(lengthSrc);
    memcpy(vt->data(), pSrc, lengthSrc);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*vt, IMREAD_COLOR, matSrc);
    delete vt;

    Mat *matRgba = new Mat();
    cvtColor(*matSrc, *matRgba, COLOR_BGR2RGBA);
    delete matSrc;

    //2.将mat转化为dst写回
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork06(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jbyteArray src) {
    // 1.将src转化成为mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    LOGD("dailyWork06 srcLength = %d", srcLength)

    // 2.将mat转化成为matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matSrc, *matRgba, COLOR_BGR2RGBA);
    delete matSrc;
    // 3.将matRgba写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork07(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jbyteArray src) {
// =======先转化称Rgba 再卷积操作
//    // 1.将src转化成为mat
//    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
//    jsize srcLength = env->GetArrayLength(src);
//    vector<char> *pVt = new vector<char>(srcLength);
//    memcpy(pVt->data(), pSrc, srcLength);
//    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
//    Mat *matSrc = new Mat();
//    imdecode(*pVt, IMREAD_COLOR, matSrc);
//    delete pVt;
//
//    LOGD("dailyWork07 srcLength = %d", srcLength)
//
//    // 2.将mat转化成为matRgba
//    Mat *matRgba = new Mat();
//    cvtColor(*matSrc, *matRgba, COLOR_BGR2RGBA);
//    delete matSrc;
//
//
//    Mat* matFiltered = new Mat();
//    Mat matKernel = Mat::ones(5,5,CV_32F)/25;
//    filter2D(*matRgba,*matFiltered,-1,matKernel);
//
//    // 3.将matRgba写回到dst
//    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
//    memcpy(pDst, matFiltered->data, matFiltered->total() * matFiltered->channels());
//    delete matFiltered;
//    env->ReleaseByteArrayElements(dst, pDst, 0);



// 先卷积操作，再转化为rgba
    // 1.将src转化成为mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    LOGD("dailyWork07 srcLength = %d", srcLength)

    Mat *matFiltered = new Mat();
    Mat matKernel = Mat::ones(5, 5, CV_32F) / 25;
    filter2D(*matSrc, *matFiltered, -1, matKernel);
    delete matSrc;


    // 2.将mat转化成为matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matFiltered, *matRgba, COLOR_BGR2RGBA);
    delete matFiltered;
    // 3.将matRgba写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork08(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBlur = new Mat();
    blur(*matSrc, *matBlur, Size(5, 5));
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBlur, *matRgba, COLOR_BGR2RGBA);
    delete matBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork09(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBlur = new Mat();
    blur(*matSrc, *matBlur, Size(5, 5));
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBlur, *matRgba, COLOR_BGR2RGBA);
    delete matBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);


}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork10(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBlur = new Mat();
    blur(*matSrc, *matBlur, Size(5, 5));
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBlur, *matRgba, COLOR_BGR2RGBA);
    delete matBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork11(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jbyteArray src) {
    // 1.src-> mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    // 2.mat->matBlur
    Mat *matBlur = new Mat();
    blur(*matSrc, *matBlur, Size(5, 5));
    delete matSrc;

    // 3.matBlur->matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matBlur, *matRgba, COLOR_BGR2RGBA);
    delete matBlur;

    // 4.写回java数组
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    delete matRgba;
    env->ReleaseByteArrayElements(dst, pDst, 0);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork12(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pSrcData = new vector<char>(srcLength);
    memcpy(pSrcData->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pSrcData, IMREAD_COLOR, matSrc);
    delete pSrcData;

    Mat *matBlur = new Mat();
    blur(*matSrc, *matBlur, Size(5, 5));
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBlur, *matRgba, COLOR_BGR2RGBA);
    delete matBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork13(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    //1. src->matSrc
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    //2.matSrc-> matFiltered
    Mat *matFiltered = new Mat();
    Mat kernel = Mat::ones(5, 5, CV_32F) / 25;
    filter2D(*matSrc, *matFiltered, -1, kernel);
    delete matSrc;

    //3.matFiltered -> matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matFiltered, *matRgba, COLOR_BGR2RGBA);
    delete matFiltered;

    //4.写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork14(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    //1.src->matSrc
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    //2.matSrc->matFilter
    Mat *matFilter = new Mat();
    Mat kernel = Mat::ones(5, 5, CV_32F) / 25;
    filter2D(*matSrc, *matFilter, -1, kernel);
    delete matSrc;

    //3.matFilter->matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matFilter, *matRgba, COLOR_BGR2RGBA);
    delete matFilter;

    //4.写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork15(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    //1.src->matSrc
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    //2.matSrc-> matGaussianBlur
    Mat *matGaussianBlur = new Mat();
    GaussianBlur(*matSrc, *matGaussianBlur, Size(27, 27), 10);
    delete matSrc;

    //3.gaussianBlurMat-> matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matGaussianBlur, *matRgba, COLOR_BGR2RGBA);
    delete matGaussianBlur;

    //4.写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork16(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    //1.src->matSrc
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    //2.matSrc->matGaussianBlur
    Mat *matGaussianBlur = new Mat();
    GaussianBlur(*matSrc, *matGaussianBlur, Size(5, 5), 1);
    delete matSrc;

    //3.matGaussianBlur->matRgba
    Mat *matRgba = new Mat();
    cvtColor(*matGaussianBlur, *matRgba, COLOR_BGR2RGBA);
    delete matGaussianBlur;

    //4.写回到dst
    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork17(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to gaussianBlurMat
    // 3.gaussianBlurMat to matRgba
    // 4 matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matGaussianBlur = new Mat();
    GaussianBlur(*matSrc, *matGaussianBlur, Size(7, 7), 10);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matGaussianBlur, *matRgba, COLOR_BGR2RGBA);
    delete matGaussianBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork18(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    // 1 src to matSrc
    // 2 matSrc to matGaussian
    // 3 matGaussian to matRgba
    // 4 matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matGaussian = new Mat();
    GaussianBlur(*matSrc, *matGaussian, Size(7, 7), 10);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matGaussian, *matRgba, COLOR_BGR2RGBA);
    delete matGaussian;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork19(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src) {
    // 1 src to matSrc
    // 2 matSrc to matGaussianBlur
    // 3 matGaussian to matRgba
    // 4 matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matGaussianBlur = new Mat();
    GaussianBlur(*matSrc, *matGaussianBlur, Size(25, 25), 50);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matGaussianBlur, *matRgba, COLOR_BGR2RGBA);
    delete matGaussianBlur;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork20(JNIEnv *env,
                                              jobject thiz,
                                              jbyteArray dst,
                                              jint d, jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateralFilter
    // 3.matBilateralFilter to matRgba
    // 4.matRgba to dst
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateralFilter = new Mat();
    bilateralFilter(*matSrc, *matBilateralFilter, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateralFilter, *matRgba, COLOR_BGR2RGBA);
    delete matBilateralFilter;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork21(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork22(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    Mat *matSrc = new Mat();
    trans_byte_array_to_mat_src(pSrc, srcLength, matSrc);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);


    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork23(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    //1.src to matSrc
    //2.matSrc to matBilateral
    //3.matBilateral to matRgba
    //4.matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;

}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork24(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork25(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to mat
    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, srcLength);
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);

    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst, matRgba->data, matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst, pDst, 0);
    delete matRgba;

}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork26(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to  matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to dst

    jbyte *pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char> *pVt = new vector<char>(srcLength);
    memcpy(pVt->data(), pSrc, pVt->size());
    env->ReleaseByteArrayElements(src, pSrc, JNI_ABORT);
    Mat *matSrc = new Mat();
    imdecode(*pVt, IMREAD_COLOR, matSrc);
    delete pVt;

    Mat *matBilateral = new Mat();
    bilateralFilter(*matSrc, *matBilateral, d, sigma_color, sigma_space);
    delete matSrc;

    Mat *matRgba = new Mat();
    cvtColor(*matBilateral, *matRgba, COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte *pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst,matRgba->data,matRgba->total()* matRgba->channels());
    env->ReleaseByteArrayElements(dst,pDst,0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork27(JNIEnv *env, jobject thiz, jbyteArray dst, jint d,
                                              jdouble sigma_color, jdouble sigma_space,
                                              jbyteArray src) {
    // 1.src to matSrc
    // 2.matSrc to matBilateral
    // 3.matBilateral to matRgba
    // 4.matRgba to dst

    jbyte* pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char>* pVt = new vector<char>(srcLength);
    memcpy(pVt->data(),pSrc,srcLength);
    env->ReleaseByteArrayElements(src,pSrc,JNI_ABORT);
    Mat* matSrc = new Mat();
    imdecode(*pVt,IMREAD_COLOR,matSrc);
    delete pVt;

    Mat* matBilateral = new Mat();
    bilateralFilter(*matSrc,*matBilateral,d,sigma_color,sigma_space);
    delete matSrc;

    Mat* matRgba = new Mat();
    cvtColor(*matBilateral,*matRgba,COLOR_BGR2RGBA);
    delete matBilateral;

    jbyte* pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst,matRgba->data,matRgba->total() * matRgba->channels());
    env->ReleaseByteArrayElements(dst,pDst,0);
    delete matRgba;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_ndkdaily_NativeLoad_dailyWork28(JNIEnv *env, jobject thiz, jbyteArray dst,
                                              jbyteArray src, jdouble threshold1, jdouble threshold2) {
    // 1.src to matSrc
    // 2.matSrc to matCanny
    // 3.matCanny to matRgba
    // 4.matRgba to dst

    jbyte* pSrc = env->GetByteArrayElements(src, nullptr);
    jsize srcLength = env->GetArrayLength(src);
    vector<char>* pVt = new vector<char>(srcLength);
    memcpy(pVt->data(),pSrc,srcLength);
    env->ReleaseByteArrayElements(src,pSrc,JNI_ABORT);
    Mat* matSrc = new Mat();
    imdecode(*pVt,IMREAD_COLOR,matSrc);
    delete pVt;

    Mat* matCanny = new Mat();
    Canny(*matSrc,*matCanny,threshold1,threshold2);
    delete matSrc;

    Mat* matRgba = new Mat();
    cvtColor(*matCanny,*matRgba,COLOR_BGR2RGBA);
    delete matCanny;

    jbyte* pDst = env->GetByteArrayElements(dst, nullptr);
    memcpy(pDst,matRgba->data,matRgba->total()* matRgba->channels());
    env->ReleaseByteArrayElements(dst,pDst,0);
    delete matRgba;
}