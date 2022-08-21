package com.huawei.roma3c.port.exception;


import com.alibaba.fastjson.JSON;
import com.huawei.roma3c.port.base.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;

/**
 * Description: no desc
 * User: zh
 * Date: 2022/8/21
 * Time: 23:37
 */

//@RestControllerAdvice
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        /**
         *  需要做什么：对代码中出现的异常进行处理
         *  1、根据请求方法的返回值类型 来分别进行异常信息的处理
         *  2、返回json：抛出resultInfo异常
         *  3、返回views:
        * */


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code","400");
        modelAndView.addObject("msg","系统异常，请重试");


        //首先获取发生异常的方法：
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod) handler;
            ResponseBody responseBody= handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);//很强势
            if(null==responseBody){
                //返回view
                if(e instanceof ParamsException){
                    //自定义异常
                    ParamsException paramsException=(ParamsException) e;
                    //值覆盖！
                    modelAndView.addObject("code", paramsException.getCode());
                    modelAndView.addObject("msg",paramsException.getMsg());
                }
                return modelAndView;
            }else {
                //返回json
                //json的系统内部异常
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统错误，请稍后试一试");

                if(e instanceof ParamsException){
                    //json的参数异常
                    ParamsException paramsException=(ParamsException)e;
                    resultInfo.setCode(paramsException.getCode());
                    resultInfo.setMsg(paramsException.getMsg());
            }
                //写入请求：
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.setCharacterEncoding("utf-8");
                PrintWriter printWriter=null;
                try {
                    printWriter=httpServletResponse.getWriter();
                    printWriter.write(JSON.toJSONString(resultInfo));
                    printWriter.flush();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }finally {
                    if(null!=printWriter){
                        printWriter.close();
                    }
                }
                return null;
            }
        }
        //非请求方法异常
        return modelAndView;
    }
}
