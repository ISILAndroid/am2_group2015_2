# am2_group2015_2
Aplicaciones Móviles II - Android ISIL

## Examen Parcial

- Utilizamos de referencia la sesión 6 para el uso de navigation drawer
- Creamos la vista "LoginActivity" en modo landscape (horizontal).
- En el elemento AndroidManifest.xml configuramos que LoginActivity sea la primera actividad.

{% highlight xml %}

    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.isil.am2lesson3" >
        <application
            android:allowBackup="true"
            android:icon="@drawable/ic_launcher"
            android:label="@string/app_name"
            android:theme="@style/AppTheme" >
            <activity
                android:name=".view.NavigationDrawerActivity"
                android:label="@string/app_name"
                android:screenOrientation="landscape">
            </activity>
            <activity
                android:name=".LoginActivity"
                android:label="@string/title_activity_login"
                android:screenOrientation="landscape">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    </manifest>

{% endhighlight %}