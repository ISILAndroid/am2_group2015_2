package com.isil.mynotesormlite.utils;

/**
 * Created by emedinaa on 15/09/15.
 */
public class StringUtils {

    public static String firstCapitalize(String txt)
    {
        if(txt.isEmpty())return "";
        int length= txt.length();
        String first =txt.substring(0,1);
        first= first.toUpperCase();
        String capitalize= first+txt.substring(1,length);
        return capitalize;
    }
}
