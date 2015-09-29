package com.isil.mynotesormlite.storage.dborm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Jay Rambhia on 05/04/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "MyNotes.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<NoteEntity, Integer> noteDao = null;
    private RuntimeExceptionDao<NoteEntity, Integer> noteRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, NoteEntity.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, NoteEntity.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
        noteDao = null;
    }

    public RuntimeExceptionDao<NoteEntity, Integer> getNoteDataDao() {
        if (noteRuntimeDao == null) {
            noteRuntimeDao = getRuntimeExceptionDao(NoteEntity.class);
        }

        return noteRuntimeDao;
    }

    public Dao<NoteEntity, Integer> getNoteDao() throws SQLException {
        if (noteDao == null) {
            noteDao = getDao(NoteEntity.class);
        }

        return noteDao;
    }

}
