package com.isil.mynotesormlite.storage.dborm;

import android.content.Context;
import android.util.Log;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jay Rambhia on 05/04/15.
 */
public class NoteRepository {

    private static final String TAG = "NoteRepository";
    private DatabaseHelper dbHelper;
    private Dao<NoteEntity, Integer> noteDao;

    public NoteRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            noteDao = dbHelper.getNoteDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(NoteEntity note) {
        try {
            return noteDao.create(note);
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return 0;
    }

    public int update(NoteEntity note) {
        try {
            return noteDao.update(note);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(NoteEntity note) {
        try {
            return noteDao.delete(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public NoteEntity getById(int id) {
        try {
            return noteDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<NoteEntity> findAll() {
        try {
            return noteDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<NoteEntity> getRecentAll() {

        QueryBuilder<NoteEntity, Integer> qb = noteDao.queryBuilder();
        try {
            qb.orderBy(NoteEntity.TIMESTAMP_FIELD, false);
            PreparedQuery<NoteEntity> preparedQuery = qb.prepare();
            return noteDao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public List<NoteEntity> getAllByCourseId(String courseId) {

        QueryBuilder<NoteEntity, Integer> qb = noteDao.queryBuilder();
        try {
            qb.where().eq(NoteEntity.COURSE_FIELD, courseId);
            PreparedQuery<NoteEntity> preparedQuery = qb.prepare();
            return noteDao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    /*public List<NoteEntity> getRecentAllByCourseId(String courseId) {

        QueryBuilder<NoteEntity, Integer> qb = noteDao.queryBuilder();
        try {
            qb.where().eq(MediaEntity.COURSE_FIELD,courseId);
            qb.orderBy(MediaEntity.TIMESTAMP_FIELD, false);
            PreparedQuery<MediaEntity> preparedQuery = qb.prepare();
            return noteDao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public long getNumberOfNotes() {
        QueryBuilder<NoteEntity, Integer> qb = noteDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }

    public List<NoteEntity> getRecentNotes(long limit) {
        if (limit < 1) {
            limit = 10;
        }

        QueryBuilder<NoteEntity, Integer> qb = noteDao.queryBuilder();
        try {
            qb.orderBy(NoteEntity.TIMESTAMP_FIELD, false);
            qb.limit(limit);
            PreparedQuery<NoteEntity> preparedQuery = qb.prepare();
            return noteDao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
