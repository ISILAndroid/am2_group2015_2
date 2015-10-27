package com.isil.am2conexionremota.tipstricks;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by emedinaa on 23/09/14.
 */
public class BaseJsonArrayHeaderRequest extends JsonArrayRequest
{
    private Map<String, String> responseHeader;
    private Response.Listener<JSONArray> mListener;
    private int mStatusCode;

    public BaseJsonArrayHeaderRequest( String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener,errorListener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        responseHeader = response.headers;
        mStatusCode = response.statusCode;

        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        if(mListener!=null)
        mListener.onResponse(response);
    }

    public int getmStatusCode() {
        return mStatusCode;
    }

}
