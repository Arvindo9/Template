package ai.aiprog.template.utils.security;

/**
 * Author       : Arvindo Mondal
 * Created on   : 14-06-2019
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
public class Decryption implements Security{

    private static Security security;
    private Decryption(){}

    public static Security getInstance(){
        if(security == null){
            security = new Decryption();
        }
        return security;
    }

    @Override
    public String getEncryption(String data) {
        return null;
    }

    @Override
    public String getDecryption(String data) {
        return _getDecryption(data);
    }

    private String _getDecryption(String data) {
        return decryption(data);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String decryption(String data);
}
