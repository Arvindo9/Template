package ai.aiprog.template.core.dialogs.date;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ai.aiprog.template.R;
import ai.aiprog.template.utils.setup.Logger;

import java.util.Calendar;

/**
 * Author       : Arvindo Mondal
 * Created on   : 24-06-2019
 * Email        : arvindo@aiprog.ai
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.ai
 */
public class DateDialog extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener {

    public static final String TAG = DateDialog.class.getSimpleName();
    private static DateInterface dateInterface;

    public interface DateInterface {

        /**
         *
         * @param date date will return in string format
         * @param params date will return in int format
         */
        void date(String date, String... params);
    }

    public static DateDialog newInstance(DateInterface object) {
        dateInterface = object;
        DateDialog fragment = new DateDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new android.app.DatePickerDialog(getContext(), R.style.DateDialog,
                this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month++;
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);

        switch (month) {
            case 1:
                monthStr = "Jan";
                break;
            case 2:
                monthStr = "Feb";
                break;
            case 3:
                monthStr = "Mar";
                break;
            case 4:
                monthStr = "Apr";
                break;
            case 5:
                monthStr = "May";
                break;
            case 6:
                monthStr = "Jun";
                break;
            case 7:
                monthStr = "Jul";
                break;
            case 8:
                monthStr = "Aug";
                break;
            case 9:
                monthStr = "Sep";
                break;
            case 10:
                monthStr = "Oct";
                break;
            case 11:
                monthStr = "Nov";
                break;
            case 12:
                monthStr = "Dec";
                break;
        }

        if (dayStr.length() < 2) {
            dayStr = "0" + day;
        }

        if (month < 10) {
            monthStr = "0" + month;
        }
        else {
            monthStr = String.valueOf(month);
        }

//        String date = monthStr + " " + dayStr + ", " + String.valueOf(year);
//        String date = month + "/" + dayStr + "/" + String.valueOf(year);
        String date = dayStr + "/" + monthStr + "/" + year;
        Logger.e("date select:" + date);
        dateInterface.date(date, dayStr, monthStr, String.valueOf(year));
        this.dismiss();
    }
}
