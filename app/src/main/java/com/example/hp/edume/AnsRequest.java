package com.example.hp.edume;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 19-Jul-17.
 */

public class AnsRequest extends StringRequest {
    private static final String ANSWER_REQUEST_URL = "http://amanrks.000webhostapp.com/AnsSub.php";
    private Map<String, String> params;

    public AnsRequest(String ans, int id,String name , Response.Listener<String> listener) {
        super(Method.POST, ANSWER_REQUEST_URL, listener, null);
        String ids = Integer.toString(id);
        params = new HashMap<>();
        params.put("ans", ans);
        params.put("id", ids);
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}