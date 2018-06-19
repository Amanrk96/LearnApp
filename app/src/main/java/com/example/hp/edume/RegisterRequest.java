package com.example.hp.edume;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 14-Jun-17.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://amanrks.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String password, String email, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);                    //listener will tell listener above when volley has done , or error report
        params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}