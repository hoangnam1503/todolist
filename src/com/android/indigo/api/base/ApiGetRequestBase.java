package com.android.indigo.api.base;

import android.content.Context;
import com.android.volley.Request;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public abstract class ApiGetRequestBase extends ApiRequestBase {
    public ApiGetRequestBase() { super(); }

    public ApiGetRequestBase(Context mContext) { super(mContext); }

    @Override
    public int getMethod() { return Request.Method.GET; }
}
