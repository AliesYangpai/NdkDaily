//
// Created by 果爸 on 2023/7/9.
//

#include "trans_opencv_util.h"

void trans_byte_array_to_mat_src(
        jbyte* src_byte_array,
        int src_length,
        Mat* matSrc)
{
    vector<char>* pVt = new vector<char>(src_length);
    memcpy(pVt->data(),src_byte_array,src_length);
    imdecode(*pVt,IMREAD_COLOR,matSrc);
    delete pVt;
}

void trans_byte_array_to_mat_src(
        char* const src_byte_array,
        int src_length,
        Mat* matSrc)
{
    vector<char>* pVt = new vector<char>(src_length);
    memcpy(pVt->data(),src_byte_array,src_length);
    imdecode(*pVt,IMREAD_COLOR,matSrc);
    delete pVt;
}

void trans_mat_rgba_to_byte_array(char* const dst_byte_array,Mat* matRgba) {
    memcpy(dst_byte_array,matRgba->data,matRgba->total() * matRgba->channels());
}
