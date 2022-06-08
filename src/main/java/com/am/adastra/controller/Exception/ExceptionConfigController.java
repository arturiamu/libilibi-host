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
        if (e instanceof InvalidUserInformationException) {
            result.setFail("用户信息无效！", State.ERR_REG_INFO);
        } else if (e instanceof PasswordNotMatchException) {
            result.setFail("密码错误！", State.ERR_REG_INFO);
        } else if (e instanceof RegistrationParameterErrorException) {
            result.setFail("参数错误！", State.ERR_REG_INFO);
        } else if (e instanceof UsernameDoesNotExistException) {
            result.setFail("用户不存在！", State.ERR_REG_INFO);
        } else if (e instanceof UsernameDuplicateException) {
            result.setFail("用户名已存在！", State.ERR_REG_INFO);
        } else if (e instanceof UserNotLoginException) {
            result.setFail("用户未登录！", State.ERR_REG_INFO);
        } else if (e instanceof VerificationCodeErrorException) {
            result.setFail("验证码错误！", State.ERR_REG_INFO);
        } else if (e instanceof VerificationCodeExpiredException) {
            result.setFail("验证码过期！", State.ERR_REG_INFO);
        } else if (e instanceof AccountRegisteredException) {
            result.setFail("当前账号已注册！", State.ERR_REG_INFO);
        }else if (e instanceof UserCollectionRepeatException){
            result.setFail("重复添加！", State.ERR_REPEATADD);
        }else {
            result.setFail("系统繁忙，请稍后重试", State.ERR_REG_INFO);
        }
        return result;
    }
}
