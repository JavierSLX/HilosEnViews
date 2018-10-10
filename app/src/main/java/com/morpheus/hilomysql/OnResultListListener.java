package com.morpheus.hilomysql;

import java.util.List;

public interface OnResultListListener<T>
{
    void onSuccess(List<T> result);
    void onFailed(String message, int error);
}
