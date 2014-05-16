package com.android.indigo.api.base;

import android.content.Context;
import com.android.volley.Request;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public abstract class ApiPostRequestBase extends ApiRequestBase {
    public ApiPostRequestBase() { super(); }

    public ApiPostRequestBase(Context mContext) { super(mContext); }

    @Override
    public int getMethod() { return Request.Method.POST; }
}
