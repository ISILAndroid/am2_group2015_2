package com.isil.mynotesormlite.view.listeners;

import com.isil.mynotesormlite.entity.NoteEntity;
import com.isil.mynotesormlite.storage.db.CRUDOperations;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     CRUDOperations getCrudOperations();
     void deleteNote(NoteEntity noteEntity);
}
