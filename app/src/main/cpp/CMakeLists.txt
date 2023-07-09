# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)
project("ndkdaily")
add_library(
        ndkdaily
        SHARED
        native-lib.cpp
        ${CMAKE_SOURCE_DIR}/local/trans_opencv_util.h
        ${CMAKE_SOURCE_DIR}/local/trans_opencv_util.cpp
        )
#opencv
include_directories(include)
# 添加预编译库 （android 6.0 之后）
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}")
find_library(log-lib log)
# 如果让添加了动态库之后，那么在此方法中也要加入动态库名称
target_link_libraries(
        ndkdaily
        ${log-lib}
        opencv_java4
        android
)