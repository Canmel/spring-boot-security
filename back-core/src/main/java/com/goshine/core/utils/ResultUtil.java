package com.goshine.core.utils;

import com.goshine.core.base.Result;

public class ResultUtil {
    /**
     * 操作成功
     * @return
     */
    public static Result success(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
