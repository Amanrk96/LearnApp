package com.example.hp.edume;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 20-Jun-17.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://amanrks.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String name, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);                    //listener will tell listener above when volley has done , or error report
        params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}