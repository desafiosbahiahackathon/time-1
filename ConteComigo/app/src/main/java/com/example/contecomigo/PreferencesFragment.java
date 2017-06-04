package com.example.contecomigo;


import android.support.v7.preference.PreferenceFragmentCompat;
import android.os.Bundle;

/**
 * Created by bpmsilva on 04/06/17.
 */

public class PreferencesFragment extends PreferenceFragmentCompat {

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // add the layout
        addPreferencesFromResource(R.xml.preferences);
    }
}
