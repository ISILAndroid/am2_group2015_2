package com.isil.am2conexionremota.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by emedinaa on 9/11/14.
 */
public class SpeakerResponseEntity implements Serializable {

    private List<SpeakerEntity> results;

    public List<SpeakerEntity> getResults() {
        return results;
    }

    public void setResults(List<SpeakerEntity> results) {
        this.results = results;
    }
}
