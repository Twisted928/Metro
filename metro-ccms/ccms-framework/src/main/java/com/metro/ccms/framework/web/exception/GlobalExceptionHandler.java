package com.metro.ccms.framework.web.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.metro.ccms.common.constant.HttpStatus;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.exception.BaseException;
import com.metro.ccms.common.exception.CustomException;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorContext;
import com.metro.ccms.framework.monitor.JwMonitorContextHolder;


/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e)
    {
    	exceptionhandler(e);
        return Result.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public Result businessException(CustomException e)
    {
    	exceptionhandler(e);
        if (StringUtils.isNull(e.getCode()))
        {
            return Result.error(e.getMessage());
        }
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        return Result.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAuthorizationException(AccessDeniedException e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage());
        return Result.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public Result handleAccountExpiredException(AccountExpiredException e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException(UsernameNotFoundException e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result validatedBindException(BindException e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Result.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
    	exceptionhandler(e);
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(message);
    }

    private void exceptionhandler(Exception ex) {
    	try {
        	JwMonitorContext jwMonitorContext = JwMonitorContextHolder.get();
        	Stack<JwMonitor> cacheStack =jwMonitorContext.getCacheStack();
        	if(null==cacheStack) {
        		return;
        	}
        	StringWriter sw = new StringWriter();
    		ex.printStackTrace(new PrintWriter(sw));
    		String strException = sw.toString();
    		strException = strException.length() <= 1800 ? strException : strException.substring(0, 1800);
        	JwMonitor jwMonitor=cacheStack.peek();
        	jwMonitor.setIfException(JwMonitor.IS_ERROR);
        	jwMonitor.setException(strException);
    	}catch(Exception e) {
    		
    	}

		
    }
}
