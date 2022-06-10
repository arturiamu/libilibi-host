package com.am.adastra.controller.Exception;

import com.am.adastra.ex.*;
import com.am.adastra.util.Result;
import com.am.adastra.util.State;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 9:28
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestControllerAdvice
public class ExceptionConfigController {

    @ExceptionHandler(ServiceException.class)
    public Result<Void> serviceExceptionHandler(ServiceException e) {
        Result<Void> result = new Result<>();
        if (e instanceof LoginException) {
            result.setFail(e.getMessage(), State.LOGIN_EX_CODE);
        } else if (e instanceof RegisterException) {
            result.setFail(e.getMessage(), State.REGISTER_EX_CODE);
        } else if (e instanceof SystemException) {
            result.setFail(e.getMessage(), State.SYS_EX_CODE);
        } else if (e instanceof UserNotLoginException) {
            result.setFail(e.getMessage(), State.USER_NOT_LOGIN_EX_CODE);
        } else if (e instanceof ValidException) {
            result.setFail(e.getMessage(), State.VALID_EX_CODE);
        } else if (e instanceof IllegalOperationException) {
            result.setFail(e.getMessage(), State.ILLEGAL_EX_CODE);
        } else {
            result.setFail(e.getMessage(), State.SYS_EX_CODE);
        }
        return result;
    }
}
