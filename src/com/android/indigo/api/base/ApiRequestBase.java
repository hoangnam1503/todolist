package com.android.indigo.api.base;

import android.content.Context;
import android.net.Uri;
import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public abstract class ApiRequestBase {
    private final int DEFAULT_TIMEOUT = 30000;
    protected Context context = null;
    private Map<String, String> queryParams = null;
    private Map<String, String> postParams = null;
    private RequestQueue requestQueue = null;

    public ApiRequestBase() {
        queryParams = new HashMap<String, String>();
        postParams = new HashMap<String, String>();
    }

    public ApiRequestBase(Context mContext) {
        this();
        this.context = mContext;
        queryParams = new HashMap<String, String>();
        postParams = new HashMap<String, String>();
        requestQueue = Volley.newRequestQueue(mContext);
    }

    public void setRequestQueue(RequestQueue mRequestQueue) { this.requestQueue = mRequestQueue; }

    public Map<String, String> getQueryParams() { return queryParams; }

    public void putQueryParam(String key, String value) {
        queryParams.put(key, value);
    }

    public Map<String, String> getPostParams() { return postParams; }

    public void putPostParam(String key, String value) {
        postParams.put(key, value);
    }

    /**
     * Build URL query via queryParams
     */
    public String buildQueryString() {
        Uri.Builder search_url = new Uri.Builder();
        for (String key : queryParams.keySet()) {
            search_url.appendQueryParameter(key, queryParams.get(key));
        }
        return search_url.build().toString();
    }

    public Request buildRequest() {
        Request request = new Request<String>(getMethod(),  buildUrl() + buildQueryString(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onError(volleyError);
            }
        }) {
            @Override
            protected Response parseNetworkResponse(NetworkResponse networkResponse) {
                return Response.success(new String(networkResponse.data), getCacheEntry());
            }

            @Override
            protected void deliverResponse(String response) {
                if (isSuccess(response)) {
                    onResponse(response);
                } else {
                    String errorMessage = getErrorMessage(response);
                    onError(new VolleyError(errorMessage));
                }
            }
        };

        request.setRetryPolicy(buildRetryPolicy());
        return request;
    }

    public void doRequest() {
        requestQueue.add(buildRequest());
    }

    public void stop() { requestQueue.stop(); }

    public String getErrorMessage(String response) {
        String errorMsg = "";
        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
            errorMsg = jsonObject.getJSONObject("error").getString("message");
        } catch (JSONException e) {

        }
        return errorMsg;
    }

    public boolean isSuccess(String response) {
        boolean isSuccess = false;
        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
            isSuccess = jsonObject.getString("status").equals("success");
        } catch (JSONException e) {
        }
        return isSuccess;
    }

    /**
     * Define request timeout (set to 30 seconds)
     */
    public RetryPolicy buildRetryPolicy() {
        return new DefaultRetryPolicy(DEFAULT_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public void replaceAccessToken() {}

    public abstract String getApiName();

    public abstract int getMethod();

    public abstract String buildUrl();

    public abstract void onError(VolleyError error);

    public abstract void onResponse(String response);
}
