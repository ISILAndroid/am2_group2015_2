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

        2. Configuración Android Manifest

        2. xxx

## Referencias


* Google Console API [Link](https://console.developers.google.com/)