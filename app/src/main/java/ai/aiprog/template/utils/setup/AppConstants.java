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

package ai.aiprog.template.utils.setup;

import ai.aiprog.template.BuildConfig;

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

public class AppConstants {

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

    //Notifications-------------------

    public static final String FIREBASE_NOTIFICATION_TOPIC = "NOTIFICATION_TOPIC_GENERAL";

    public static final int PUSH_NOTIFICATION_ID = 1201;
    public static final String CHANNEL_NAME = BuildConfig.APPLICATION_ID + " Service";
    public static final String CHANNEL_ID = BuildConfig.APPLICATION_ID;

    public static final String FIREBASE_NOTIFICATION_TOPIC_GROUP = "NOTIFICATION_TOPIC_GROUP";
    public static final String NOTIFICATION_TOPIC_GENERAL = "NOTIFICATION_TOPIC_GENERAL";
    public static final String NOTIFICATION_TOPIC_LOGOUT = "NOTIFICATION_TOPIC_LOGOUT";
    //    public static final String NOTIFICATION_TOPIC_MESSAGE = "NOTIFICATION_TOPIC_MESSAGE";
    public static final String NOTIFICATION_TOPIC_MESSAGE = "message";
    public static final String NOTIFICATION_TOPIC_MATCH = "NOTIFICATION_TOPIC_MATCH";
    public static final String NOTIFICATION_TOPIC_ROSE = "NOTIFICATION_TOPIC_ROSE";


    //Base activity-------------------

    public static final String KEY_DEFAULT_ACTIVITY_BUNDLE = "KEY_DEFAULT_ACTIVITY_BUNDLE";
    public static final int REQUEST_PERMISSIONS = 20;

    //API result code-----------------

    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILURE = 0;

    public static final String DATA_TYPE_IMAGE = "1001";
    public static final String DATA_TYPE_VIDEO = "1002";

    //API data code-------------------

    public static final String DATA_PARAMS = "Data";
    public static final String DATA_SIZE_PARAMS = "DataSize";
    public static final String DATE_TIME = "DateTime";
    public static final String LOG_CODE = "LogCode";
    public static final String REPORT = "Report";
    public static final String LOGGING = "Logging";

    //App code------------------------

    public static final String ERROR_RESPONSE_DEFAULT = "N/A";

    public static final String API_SUCCESS = "API_SUCCESS";
    public static final String API_EMPTY = "API_EMPTY";
    public static final String API_ERROR = "API_ERROR";
    public static final String API_INVALID = "API_INVALID";

    public static final String DB_SUCCESS = "DB_SUCCESS";
    public static final String DB_EMPTY = "DB_EMPTY";
    public static final String DB_ERROR = "DB_ERROR";
    public static final String DB_INVALID = "DB_INVALID";

    public static final String JSON_ARRAY_ERROR = "JSON_ARRAY_ERROR";
    public static final String JSON_OBJECT_ERROR = "JSON_OBJECT_ERROR";

    public static final String NETWORK_ISSUE = "NETWORK_ISSUE";

}