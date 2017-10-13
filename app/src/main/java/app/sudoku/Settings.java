package app.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Pawel on 2017-09-12.
 */
public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

    }

    public static boolean UstawieniaMuzyka(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("muzyka", true);
    }
}
