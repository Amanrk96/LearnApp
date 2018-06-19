package com.example.hp.edume;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 25-Jun-17.
 */
public class QsnRequest extends StringRequest {
    private static final String QUESTION_REQUEST_URL = "http://amanrks.000webhostapp.com/QsnSub.php";
    private Map<String, String> params;

    public QsnRequest(String qsn, String name, Response.Listener<String> listener) {
        super(Request.Method.POST, QUESTION_REQUEST_URL, listener, null);//listener will tell listener above when volley has done , or error report
        params = new HashMap<>();
        params.put("qsn", qsn);
        //  params.put("ans",ans);
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}