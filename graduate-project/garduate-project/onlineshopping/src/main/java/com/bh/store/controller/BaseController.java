package com.bh.store.controller;

import com.bh.store.controller.ex.*;
import com.bh.store.service.ex.*;
import com.bh.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //成功状态码
    public static final int OK= 200;
    @ExceptionHandler({ServiceException.class,FileUploadException.class})//用于统一抛出异常
    //项目产生异常统一拦截到此方法中，该方法充当请求处理，返回值返回给前端
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if (e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("密码不正确");
        }else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户收货地址超出上限");
        }else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户数据不存在");
        } else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("数据非法访问");
        }else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品未找到");
        }else if (e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车数据不存在");
        } else if (e instanceof OrderNotFoundException){
            result.setState(4008);
            result.setMessage("订单未找到");
        }else if (e instanceof OrderItemEmptyException){
            result.setState(4009);
            result.setMessage("订单内容为空");
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据产生未知异常");
        }else if (e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除时异常");
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
        }else if (e instanceof FileSizeException){
            result.setState(6001);
        }else if (e instanceof FileTypeException){
            result.setState(6002);
        }else if (e instanceof FileStateException){
            result.setState(6003);
        }else if (e instanceof FileUploadIOException){
            result.setState(6004);
        }
        return result;
    }
    public final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    public final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
