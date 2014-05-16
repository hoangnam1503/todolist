package com.android.indigo.api.base;

import android.content.Context;
import com.android.volley.*;
import com.parse.entity.mime.MultipartEntity;
import com.parse.entity.mime.content.FileBody;
import com.parse.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public abstract class ApiMultiPartsRequestBase extends ApiRequestBase {
    protected MultipartEntity entity = null;

    public ApiMultiPartsRequestBase(Context mContext) {
        super(mContext);
        entity = new MultipartEntity();
    }

    @Override
    public int getMethod() { return Request.Method.POST; }

    public void putFile(String key, File file) {
        entity.addPart(key, new FileBody(file));
    }

    @Override
    public void putPostParam(String key, String value) {
        try {
            entity.addPart(key, new StringBody(value, Charset.forName("UTF-8")));
        } catch (UnsupportedEncodingException e) {
        }
    }

    @Override
    public Request buildRequest() {
        Request request = new Request<String>(getMethod(), buildUrl() + buildQueryString(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onError(volleyError);
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
                return Response.success(new String(networkResponse.data), getCacheEntry());
            }

            @Override
            protected void deliverResponse(String response) {
                if (isSuccess(response)) {
                    onResponse(response);
                } else {
                    String errorMsg = getErrorMessage(response);
                    onError(new VolleyError(errorMsg));
                }
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    entity.writeTo(bos);
                } catch (IOException e) {
                }
                return bos.toByteArray();
            }

            @Override
            public String getBodyContentType() {
                return entity.getContentType().getValue();
            }
        };
        request.setRetryPolicy(buildRetryPolicy());
        return request;
    }
}
