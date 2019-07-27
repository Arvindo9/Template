/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.aiprog.template.utils;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */

public enum  AppConstants {

    INSTANCE;

    public static final String FIREBASE_NOTIFICATION_TOPIC = "NOTIFICATION_TOPIC_GENERAL";
    AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final String DB_NAME = "aiprog.db";

    public static final String PASS_PHRASE_FIELD = "PASS_PHRASE_FIELD.db";

    public static final String PREF_NAME = "aiprog_pref";

    public static final String GENERAL_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_TIME_NAME_FORMAT = "yyyyMMdd_HHmmss";

    public static final String DATE_FORMAT_INTEGER = "yyyyMMdd";

    public static final int LOG_DELETE_DATE_INCREMENT = 1;

    //Default initialisation-----------

    public static final int GALLERY_ADAPTER_ITEM_SIZE = 6;

    //API result code-----------------

    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILURE = 0;

    //Connection type data-----------

    public static final String HOOKUPS = "1";
    public static final String FRIENDSHIP = "2";
    public static final String SHORT_TERM_DATING = "3";
    public static final String LONG_TERM_DATING = "4";
    public static final String MARRIAGE = "5";

    //Format-----------------------------

    public static final String GALLERY_MEDIA_STORAGE_PATH = "file://";
    public static final String IMAGE_NAME_DEFAULT = "Happly_Img_";
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";

    //Designation and job type-----------

    public static final String WORK = "WORK";
    public static final String STUDENT = "Student";
    public static final String PRIVATE_JOB = "Private job";
    public static final String GOVERNMENT_JOB = "Government job";
    public static final String BUSINESS = "Business";
    public static final String SELF_EMPLOYED = "Self-employee";

    //Sex----------------------------

    public static final String MALE = "M";
    public static final String FEMALE = "F";
    public static final String OTHERS = "O";

    //Verifications-------------------

    public static final int VERIFICATION_PASSWORD = 0;
    public static final int VERIFICATION_MOBILE = 1;
    public static final int VERIFICATION_EMAIL = 2;

    //DataType------------------------

    public static final String DATA_TYPE_IMAGE = "1001";
    public static final String DATA_TYPE_VIDEO = "1002";

    //DataUSe For---------------------

    public static final String BINARY_DATA = "BinaryData";

    public static final int IMAGE_FRONT = 100;
    public static final int IMAGE_LEFT = 200;
    public static final int IMAGE_RIGHT = 300;
    public static final int IMAGE_PROFILE_PIC = 500;

    //Logging mode--------------------

    public static final String LOGGING_MODE_USER_PASSWORD = "100";
    public static final String LOGGING_MODE_DIRECT_MOBILE = "101";
    public static final String LOGGING_MODE_DIRECT_PASSWORD = "102";
    public static final String LOGGING_MODE_GOOGLE = "103";
    public static final String LOGGING_MODE_FACEBOOK = "104";
    public static final String LOGGING_MODE_TWEETER = "105";
    public static final String LOGGING_MODE_INSTRAGRAM = "106";

    //API data code-------------------

    public static final String DATA_PARAMS = "Data";
    public static final String DATA_SIZE_PARAMS = "DataSize";
    public static final String DATE_TIME = "DateTime";
    public static final String LOG_CODE = "LogCode";
    public static final String REPORT = "Report";
    public static final String LOGGING = "Logging";

    //App code------------------------

    public static final int SRART_APPLICATION = 112;

    public static final int APPLICATION_ID_EMPTY_DB = 100;
    public static final int APPLICATION_ID_ERROR_DB = 101;
    public static final int APPLICATION_ID = 103;
    public static final int APPLICATION_ID_EMPTY_API = 104;
    public static final int APPLICATION_ID_ERROR_API = 105;
    public static final int APPLICATION_ID_SAVE_EMPTY_DB = 113;
    public static final int APPLICATION_ID_SAVE_ERROR_DB = 114;

    public static final int FLAG_GET_SUCCESS_DB = 132;
    public static final int FLAG_GET_SUCCESS_API = 146;
    public static final int FLAG_GET_EMPTY_DB = 133;
    public static final int FLAG_GET_ERROR_DB = 134;
    public static final int FLAG_ERROR_API = 106;
    public static final int FLAG_EMPTY_API = 107;
    public static final int FLAG_EMPTY_DB = 108;
    public static final int FLAG_ERROR_DB = 109;
    public static final int FLAG_UPDATE_ERROR_DB = 110;
    public static final int FLAG_UPDATE_EMPTY_DB = 111;

    public static final int LOGGING_GET_EMPTY_DB = 115;
    public static final int LOGGING_GET_ERROR_DB = 116;
    public static final int LOGGING_SAVE_EMPTY_DB = 124;
    public static final int LOGGING_SAVE_ERROR_DB = 125;
    public static final int LOGGING_PUSH_EMPTY_API = 126;
    public static final int LOGGING_PUSH_ERROR_API = 127;
    public static final int LOGGING_SUCCESS_DB = 117;
    public static final int LOGGING_DELETE_EMPTY_DB = 118;
    public static final int LOGGING_DELETE_ERROR_DB = 119;
    public static final int LOGGING_DATE_SAVE_EMPTY_PREF = 120;
    public static final int LOGGING_DATE_SAVE_ERROR_PREF = 121;
    public static final int LOGGING_DATE_GET_SUCCESS_PREF = 124;
    public static final int LOGGING_DATE_GET_EMPTY_PREF = 122;
    public static final int LOGGING_DATE_GET_ERROR_PREF = 123;

    public static final int APP_VERSION_EMPTY = 130;
    public static final int APP_VERSION_ERROR = 131;

    public static final int JSON_ARRAY_ERROR = 128;
    public static final int JSON_OBJECT_ERROR = 129;
    public static final int JSON_OBJECT_ERROR_REGISTRATION = 135;

    public static final int REGISTRATION_EMPTY_API = 136;
    public static final int REGISTRATION_ERROR_API = 137;
    public static final int REGISTRATION_INVALID_API = 146;
    public static final int JSON_OBJECT_REGISTRATION = 138;
    public static final int JSON_OBJECT_EMAIL_PASS_VERIFY = 142;
    public static final int JSON_OBJECT_UPLOAD_SECURITY_IMAGE = 146;
    public static final int JSON_OBJECT_UPLOAD_PROFILE_IMAGE = 147;
    public static final int JSON_OBJECT_MOBILE_PASS_VERIFY = 142;

    public static final int LOGIN_INVALID_API = 139;
    public static final int LOGIN_EMPTY_API = 140;
    public static final int LOGIN_ERROR_API = 141;
    public static final int EMAIL_PASS_VERIFY_INVALIDE_API = 145;
    public static final int EMAIL_PASS_VERIFY_ERROR_API = 143;
    public static final int EMAIL_PASS_VERIFY_EMPTY_API = 144;

    public static final int MEDIAL_DATA_EMPTY_API = 148;
    public static final int MEDIAL_DATA_ERROR_API = 149;
    public static final int MEDIAL_DATA_INVALIDE_API = 150;
    public static final int MEDIAL_DATA_SUCCESS_API = 151;

    public static final int MULTIPIC_PIC_MEDIA_ADAPTER_INVALID = 152;
    public static final int MULTIPIC_PIC_MEDIA_ADAPTER_EMPTY = 153;
    public static final int MULTIPIC_PIC_MEDIA_ADAPTER_ERROR = 154;
}