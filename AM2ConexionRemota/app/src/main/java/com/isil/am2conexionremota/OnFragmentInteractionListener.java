package com.isil.am2conexionremota;

import com.isil.am2conexionremota.entity.SpeakerEntity;

/**
 * Created by emedinaa on 9/11/14.
 */
public interface OnFragmentInteractionListener {

    void gotoAddSpeaker();
    void gotoListSpeaker();
    void gotoSpeakerDetails(SpeakerEntity speakerEntity);
}
