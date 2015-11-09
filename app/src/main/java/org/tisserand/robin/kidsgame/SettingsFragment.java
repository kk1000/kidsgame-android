package org.tisserand.robin.kidsgame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private PreferenceCategory dbAuthCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        // Only display DB Authentication category if needed
        dbAuthCategory = (PreferenceCategory) findPreference("pref_db_auth");
        CheckBoxPreference dbAuthRequired = (CheckBoxPreference) findPreference("pref_db_auth_require");
        Log.d("RobSetting", "Checkbox is " + dbAuthRequired.isChecked());
        displayDbAuth(dbAuthRequired.isChecked());
    }

    private void displayDbAuth(Boolean display) {
        // Only display DB Authentication category if needed
        PreferenceScreen screen = getPreferenceScreen();
        if (!display) {
            screen.removePreference(dbAuthCategory);
        } else {
            screen.addPreference(dbAuthCategory);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("pref_db_auth_require")) {
            CheckBoxPreference dbAuthRequired = (CheckBoxPreference) findPreference("pref_db_auth_require");
            displayDbAuth(dbAuthRequired.isChecked());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
