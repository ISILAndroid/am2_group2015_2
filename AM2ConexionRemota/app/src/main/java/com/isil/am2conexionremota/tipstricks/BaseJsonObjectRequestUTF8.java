package com.isil.am2conexionremota.tipstricks;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by emedinaa on 25/09/14.
 */
public class BaseJsonObjectRequestUTF8 extends JsonObjectRequest {

    private  static  final  String LOG_TAG="BaseJsonObjectRequestUTF8";
    protected static final String TYPE_UTF8_CHARSET = "charset=utf-8";
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    public BaseJsonObjectRequestUTF8(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String type = response.headers.get(HTTP.CONTENT_TYPE);
            if (type == null) {
                Log.d(LOG_TAG, "content type was null");

                type = TYPE_UTF8_CHARSET;
                response.headers.put(HTTP.CONTENT_TYPE, type);
            } else if (!type.contains("UTF-8")) {
                Log.d(LOG_TAG, "content type had UTF-8 missing");

                type += ";" + TYPE_UTF8_CHARSET;
                response.headers.put(HTTP.CONTENT_TYPE, type);
            }
        } catch (Exception e) {
            //print stacktrace e.g.
        }
        //return super.parseNetworkResponse(response);

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public String getBodyContentType() {
       return PROTOCOL_CONTENT_TYPE;
        //return "application/x-www-form-urlencoded; charset=UTF-8";
        //return "application/json; charset=UTF-8";
    }
}
