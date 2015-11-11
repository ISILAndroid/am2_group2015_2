# am2_group2015_2
Aplicaciones Móviles II - Android ISIL

## Lesson 10

* Google Maps
    - Configuración
        1. Crear un proyecto en [Google Console API](https://console.developers.google.com/)
        ![1](https://github.com/ISILAndroid/am2_group2015_2/blob/Lesson10/images/gm1.png)
        2. Agregamos el API de Google Maps Android API V2.
        ![2](https://github.com/ISILAndroid/am2_group2015_2/blob/Lesson10/images/gm2.png)
        3. Luego creamos las credenciales para nuestro proyecto Android.
         ![3](https://github.com/ISILAndroid/am2_group2015_2/blob/Lesson10/images/gm3.png)
        4. Agregamos una clave de API de Android y lo guardamos.
         ![4](https://github.com/ISILAndroid/am2_group2015_2/blob/Lesson10/images/gm4.png)


    - Proyecto Android
        1. Agregamos la dependencia de Google Play services   
        ```  
        dependencies {
            compile fileTree(dir: 'libs', include: ['*.jar'])
            compile 'com.android.support:appcompat-v7:22.2.0'
            compile 'com.google.android.gms:play-services:6.1.71'
        }

        ```

        2. Configuración del AndroidManifest
            Permisos requeridos
            Habilitar OpenGL
            Metadata requerida para google play services y google maps

        ```
        <permission
        android:name="com.emedinaa.am2googlemaps.permission.MAPS_RECEIVE"
        android:protectionLevel="signature" >
        </permission>

        <uses-permission android:name="com.emedinaa.am2googlemaps.permission.MAPS_RECEIVE" />
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

        ```

        ```
        <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true" />
        ```

        ```
        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/gmaps_key" />
        ```
        
        3. Agregar al XML del activity el fragment de Google Maps
        ```
        <fragment
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        class="com.google.android.gms.maps.SupportMapFragment"
        android:id="@+id/map"/>
        
        ```

## Referencias


* Google Console API [Link](https://console.developers.google.com/)