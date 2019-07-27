package com.aiprog.template.utils.util;

import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.aiprog.template.R;
import com.aiprog.template.base.BaseActivity;

import javax.inject.Singleton;

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
@Singleton
public class ResourceUtils implements Resource {

    @Override
    public <A extends BaseActivity> boolean validateMobile(A activity, EditText editText) {
        String string = editText.getText().toString();
        String regex = "[0-9]+";
        if (string.trim().isEmpty()) {
            editText.setError(activity.getString(R.string.mobile_number_empty));
            activity.requestFocus(editText);
            return false;
        }
        else if (!string.matches(regex) ||
                string.length() != 10)  {
            editText.setError(activity.getString(R.string.errorMobile));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateEmail(A activity, EditText editText) {
        String string = editText.getText().toString();
        if (string.matches("[*a-zA-Z]") ||
                !string.contains("@") ||
                !string.contains(".") ||
                string.contains("@.")||
                string.startsWith("@")||
                string.endsWith(".")||
                (string.lastIndexOf(".") < string.indexOf("@"))) {
            editText.setError(activity.getString(R.string.invalid_email));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateMobileEmail(A activity, EditText editText) {
        String string = editText.getText().toString();
        String regex = "[0-9]+";
        if (string.trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalid_mobile_email));
            activity.requestFocus(editText);
            return false;
        }
        else if (!string.matches(regex) ||
                string.length() != 10)  {
            editText.setError(activity.getString(R.string.errorMobile));
            activity.requestFocus(editText);
            return false;
        }

        else if (string.matches("[*a-zA-Z]") ||
                !string.contains("@") ||
                !string.contains(".") ||
                string.contains("@.")||
                string.startsWith("@")||
                string.endsWith(".")||
                (string.lastIndexOf(".") < string.indexOf("@"))) {
            editText.setError(activity.getString(R.string.invalid_email));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validatePassword(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_password));
            activity.requestFocus(editText);
            return false;
        } else if (editText.getText().toString().trim().length()< 6) {
            editText.setError(activity.getString(R.string.invalide_password_length));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validatePasswordVerify(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_password));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validatePasswordConfirm(A activity, EditText passwordText, EditText confirmText) {
        if (!passwordText.getText().toString().equals(confirmText.getText().toString())) {
            confirmText.setError(activity.getString(R.string.invalide_password_confirm));
            activity.requestFocus(confirmText);
            return false;
        } else {
            confirmText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateFirstName(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_first_name));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateLastName(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_last_name));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateName(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_name));
            activity.requestFocus(editText);
            return false;
        }
        else if (editText.getText().toString().contains(" ")) {
            editText.setError(activity.getString(R.string.name_with_no_space));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateOtp(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.otp_empty));
            activity.requestFocus(editText);
            return false;
        }
        else if (editText.getText().toString().trim().length() < 4) {
            editText.setError(activity.getString(R.string.otp_length_short));
            activity.requestFocus(editText);
            return false;
        }
        else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateDay(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.enter_day));
            activity.requestFocus(editText);
            return false;
        }else {
            int day;
            try {
                day = Integer.parseInt(editText.getText().toString());
            }
            catch (NumberFormatException ignore){
                editText.setError(activity.getString(R.string.invalide_day));
                activity.requestFocus(editText);
            }
            catch (Exception ignore){
                editText.setError(activity.getString(R.string.invalide_day));
                activity.requestFocus(editText);
            }
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateMonth(A activity, EditText editText) {
        return false;
    }

    @Override
    public <A extends BaseActivity> boolean validateYear(A activity, EditText editText) {
        return false;
    }

    @Override
    public <A extends BaseActivity> boolean validateDate(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.invalide_date));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validatePinCode(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.pin_code_empty));
            activity.requestFocus(editText);
            return false;
        }else if (editText.getText().toString().trim().length() < 5) {
            editText.setError(activity.getString(R.string.pin_code_too_short));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateMinimumAge(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.select_min_age));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }

    @Override
    public <A extends BaseActivity> boolean validateMaximumAge(A activity, EditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.setError(activity.getString(R.string.select_max_age));
            activity.requestFocus(editText);
            return false;
        } else {
            editText.setError(null);
        }

        return true;
    }
}
