package ai.aiprog.template.utils.util;

import android.widget.EditText;

import ai.aiprog.template.base.BaseActivity;

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
public interface Resource {

    <A extends BaseActivity> boolean validateMobile(A activity, EditText editText);

    <A extends BaseActivity> boolean validateEmail(A activity, EditText editText);

    <A extends BaseActivity> boolean validateMobileEmail(A activity, EditText editText);

    <A extends BaseActivity> boolean validatePassword(A activity, EditText editText);

    <A extends BaseActivity> boolean validatePasswordVerify(A activity, EditText editText);

    <A extends BaseActivity> boolean validatePasswordConfirm(A activity, EditText passwordText, EditText confirmText);

    <A extends BaseActivity> boolean validateFirstName(A activity, EditText editText);

    <A extends BaseActivity> boolean validateLastName(A activity, EditText editText);

    <A extends BaseActivity> boolean validateName(A activity, EditText editText);

    <A extends BaseActivity> boolean validateOtp(A activity, EditText editText);

    <A extends BaseActivity> boolean validateDay(A activity, EditText editText);

    <A extends BaseActivity> boolean validateMonth(A activity, EditText editText);

    <A extends BaseActivity> boolean validateYear(A activity, EditText editText);

    <A extends BaseActivity> boolean validateDate(A activity, EditText editText);

    <A extends BaseActivity> boolean validatePinCode(A activity, EditText pinCode);
}
