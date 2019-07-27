#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_aiprog_template_ui_launcher_splash_SplashActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}