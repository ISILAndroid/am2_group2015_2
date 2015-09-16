package com.isil.mynotes.view.listeners;

import com.isil.mynotes.model.entity.NoteEntity;
import com.isil.mynotes.storage.db.CRUDOperations;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     CRUDOperations getCrudOperations();
     void deleteNote(NoteEntity noteEntity);
}
