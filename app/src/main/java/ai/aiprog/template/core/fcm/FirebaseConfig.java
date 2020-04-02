package ai.aiprog.template.core.fcm;

/**
 * Author : Arvindo Mondal
 * Created on 12-02-2020
 */
public class FirebaseConfig {

    private static FirebaseConfig config;

    public static FirebaseConfig getInstance(){
        if(config == null){
            config = new FirebaseConfig();
        }
        return config;
    }
}
