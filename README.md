# am2_group2015_2
Aplicaciones Móviles II - Android ISIL

## Examen Parcial

- Utilizamos de referencia la sesión 6 para el uso de navigation drawer
- Creamos la vista "LoginActivity" en modo landscape (horizontal).
- En el elemento AndroidManifest.xml configuramos que LoginActivity sea la primera actividad.

```
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
```

- Cambiamos los items del navigation drawer en Strings.xml.

```
    <string-array name="planets_array">
        <item>Perfil</item>
        <item>Notas</item>
        <item>Facultades</item>
        <item>Cerrar sesión</item>
    </string-array>
```

- La funcionalidad de cerrar sesión.

```java
    private void closeSession() {
        PreferencesHelper.signOut(this);
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
```

- Creamos el fragment "GradeFragment" , que debe tener solamente un ListView.
- Creamos el layout que nos servirá para la celda.
- Creamos el adapter


```java
    public class CourseAdapter extends BaseAdapter {
        private Context context;
        private List<CourseEntity> data;
        public CourseAdapter(Context context, List<CourseEntity> data) {
            this.context = context;
            this.data = data;
        }
        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(context);
            View container= inflater.inflate(R.layout.row_course, null);
            ImageView imgContact= (ImageView)container.findViewById(R.id.iviCourse);
            TextView tviName= (TextView)container.findViewById(R.id.tviName);
            TextView tviGrade= (TextView)container.findViewById(R.id.tviGrade);
            //Extraer la entidad
            CourseEntity courseEntity= this.data.get(position);
            //Asociar la entidad con el XML
            String name = courseEntity.getName();
            double grade = courseEntity.getGrade();
            tviName.setText(name);
            tviGrade.setText(Double.toString(grade));
            //imgContact.setImageResource(contactEntity.getPhoto());
            return container;
        }
    }
```

- Asociamos el adapter al ListView y agregamos data de prueba

```java
     @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            lviGrade= (ListView)getView().findViewById(R.id.lviGrade);
            //data
            courseEntities= new ArrayList<>();
            courseEntities.add(new CourseEntity("Matemática",15,0));
            courseEntities.add(new CourseEntity("Física",13,0));
            courseEntities.add(new CourseEntity("Algoritmos I",18,0));
            courseEntities.add(new CourseEntity("Base de Datos II",16,0));
            //adapter
            CourseAdapter courseAdapter= new CourseAdapter(getActivity(), courseEntities);
            //asociar adapter a lista
            lviGrade.setAdapter(courseAdapter);
        }
```

- Finalmente en el login validamos que exista la sesión

```java
    public class LoginActivity extends AppCompatActivity {
        private static final String VALUE_ADMIN="admin";
        private static final String VALUE_PASSWORD="123456";
        private EditText editTxtUsername;
        private EditText editTxtPassword;
        private Button btnLogin;
        private String username,password;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            if(session())
            {
                gotoHome();
                return;
            }else
            {
                init();
            }
        }
        private boolean session() {
            String tmp =PreferencesHelper.getUserSession(this);
            if(tmp==null) return false;
            return true;
        }
        private void init() {
            editTxtUsername= (EditText)findViewById(R.id.editTxtUsername);
            editTxtPassword= (EditText)findViewById(R.id.editTxtPassword);
            btnLogin= (Button)findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validateForm())
                    {
                        if(username.equals(VALUE_ADMIN) && password.equals(VALUE_PASSWORD))
                        {
                            saveSession();
                            gotoHome();
                        }else
                        {
                            //TODO mostrar mensaje de error
                        }
                    }
                }
            });
        }
        private void saveSession() {
            PreferencesHelper.saveSession(this, username,password);
        }
        private void gotoHome() {
            Intent intent= new Intent(this, NavigationDrawerActivity.class);
            startActivity(intent);
            finish();
        }
        private boolean validateForm() {
            username = editTxtUsername.getText().toString().trim();
            password = editTxtPassword.getText().toString().trim();
            if (username.isEmpty()) {
                editTxtUsername.setError("Username ,campo inválido");
                return false;
            }
            if (password.isEmpty())
            {
                editTxtUsername.setError("Passsword ,campo inválido");
                return false;
            }
            return true;
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            return false;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return false;
        }
    }
```