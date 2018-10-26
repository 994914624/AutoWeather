package test.yang.com.apptest;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;



public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    private  static String TAG="SettingsActivity";
    SwitchPreference switchPreference;
    CheckBoxPreference checkBoxPreference1;
    CheckBoxPreference checkBoxPreference2;
    CheckBoxPreference checkBoxPreference3;
    CheckBoxPreference checkBoxPreference4;
    private ArrayList<CheckBoxPreference> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.setting_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        switchPreference = (SwitchPreference) findPreference("pref_auto");
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d(TAG,"key"+preference.getKey()+"value:"+newValue.toString());
                return true;
            }
        });

        checkBoxPreference1= (CheckBoxPreference) findPreference("pref_1");
        checkBoxPreference2= (CheckBoxPreference) findPreference("pref_2");
        checkBoxPreference3= (CheckBoxPreference) findPreference("pref_3");
        checkBoxPreference4= (CheckBoxPreference) findPreference("pref_4");
        checkBoxPreference1.setOnPreferenceChangeListener(this);
        checkBoxPreference2.setOnPreferenceChangeListener(this);
        checkBoxPreference3.setOnPreferenceChangeListener(this);
        checkBoxPreference4.setOnPreferenceChangeListener(this);


        list=new ArrayList<>();
        list.add(checkBoxPreference1);
        list.add(checkBoxPreference2);
        list.add(checkBoxPreference3);
        list.add(checkBoxPreference4);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d(TAG,"key"+preference.getKey()+"value:"+newValue.toString());
        String key=preference.getKey();
        boolean value =Boolean.parseBoolean(newValue.toString());
        if(!value){// false 取消当前
            return true;
        }
        for(int i=0;i<list.size();i++){
            if(!key.equals(list.get(i).getKey())){
                list.get(i).setChecked(false);
            }
        }
        return true;
    }




}
