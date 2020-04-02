package ai.aiprog.template.utils.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static ai.aiprog.template.utils.setup.AppConstants.DATA_TYPE_IMAGE;

/**
 * Author       : Arvindo Mondal
 * Created on   : 21-06-2019
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
public class Params {

    private Params(){}

    public static JSONObject paramsUserLoginMobile(int loginMode, String countryCode, String mobile){
        return null;
    }

    public static JSONObject paramsUserLoginEmail(String loginMode, String email) throws JSONException {
        int dataSize = 2;
        String params = JObject.getParamsData(loginMode, email);
        return JObject.paramsJsonObject(params, dataSize);
    }

    public static JSONObject paramsLoginEmailPassword(String loginMode, String email, String password) throws JSONException {
        int dataSize = 3;
        String params = JObject.getParamsData(loginMode, email, password);
        return JObject.paramsJsonObject(params, dataSize);
    }

    public static JSONObject paramsRegistrationPart1(String loginMode, String firstName, String lastName,
                                                     String password, String email)
            throws JSONException {
        int dataSize = 5;
        String params = JObject.getParamsData(loginMode, firstName, lastName, password, email);
        return JObject.paramsJsonObject(params, dataSize);
    }

    public static JSONObject paramsRegistrationPart2(ArrayList<String> paramsList)
            throws JSONException, ArrayIndexOutOfBoundsException {
        int dataSize = 3;
        String params;
        JSONObject object = null;
        if(paramsList.size() >= 9) {
            params = JObject.getParamsData(paramsList.get(0), paramsList.get(1), paramsList.get(2));

            HashMap<String, String> map = new HashMap<>();
            map.put( "Country", paramsList.get(3));
            map.put( "PinCode", paramsList.get(4));
            map.put( "CountryCode", paramsList.get(5));
            map.put( "Latitude", paramsList.get(6));
            map.put( "Longitude", paramsList.get(7));
            map.put( "LatitudeRedian", paramsList.get(8));
            map.put( "LongitudeRedian", paramsList.get(9));

            object = JObject.paramsJsonObject(params, dataSize, map);
        }
        return object;
    }


    public static JSONObject paramsUploadMedia(String dataUserFor, String fileName, String fileFormat)
            throws JSONException, ArrayIndexOutOfBoundsException {

            HashMap<String, String> map = new HashMap<>();
            map.put( "DataType", DATA_TYPE_IMAGE);
            map.put( "FileName", fileName);
            map.put( "Format", fileFormat);
            map.put( "DataUseFor", dataUserFor);

        return JObject.getJsonObject(map);
    }

    public static JSONObject paramsRegistrationPart3(ArrayList<String> paramsList)
            throws JSONException, ArrayIndexOutOfBoundsException {

        JSONObject object = null;
        if(paramsList.size() >= 4) {
            HashMap<String, String> map = new HashMap<>();
            map.put( "LookingFor", paramsList.get(0));
            map.put( "InterestedIn", paramsList.get(1));
            map.put( "AgeBegin", paramsList.get(2));
            map.put( "AgeUpTo", paramsList.get(3));

            object = JObject.getJsonObject(map);
        }
        return object;
    }

    public static JSONObject paramsRegistrationPart4(ArrayList<String> paramsList)
            throws JSONException, ArrayIndexOutOfBoundsException {

        JSONObject object = null;
        if(paramsList.size() >= 5) {
            HashMap<String, String> map = new HashMap<>();
            map.put( "ShortDescription", paramsList.get(0));
            map.put( "About", paramsList.get(1));
            map.put( "Work", paramsList.get(2));
            map.put( "Organisation", paramsList.get(3));
            map.put( "Designation", paramsList.get(4));

            object = JObject.getJsonObject(map);
        }
        return object;
    }
}
