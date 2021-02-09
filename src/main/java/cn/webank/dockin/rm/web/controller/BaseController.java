/*
 * Copyright (C) @2021 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.webank.dockin.rm.web.controller;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.utils.StringBuilderHolder;
import cn.webank.dockin.rm.web.bean.*;
import cn.webank.dockin.rm.web.function.ExecServiceTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
public class BaseController {
    public static final String RESULT_CODE_KEY = "resultCode";
    protected static final Logger MONITOR_LOGGER =
            LoggerFactory.getLogger("BaseController");
    protected static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();
    protected static final ThreadLocal<StringBuilderHolder> THREADLOCAL_STRINGBUILDER_HOLDER =
            ThreadLocal.withInitial(() -> new StringBuilderHolder(1024));
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    @Qualifier("messageSource")
    protected ReloadableResourceBundleMessageSource bundleMessageSource;
    @Autowired
    private ThreadPoolTaskExecutor frontTaskExecutor;
    private int defaultMaxTimeout = 300_000;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    protected ThreadPoolTaskExecutor getFrontTaskExecutor() {
        return frontTaskExecutor;
    }
    protected <T> DeferredResult<T> buildDeferredResultWithTimeout(long timeoutMilliSeconds, T defaultTimeoutReturndefaultWebMessage) {
        DeferredResult<T> deferredResult = new DeferredResult<T>(timeoutMilliSeconds, defaultTimeoutReturndefaultWebMessage);
        return deferredResult;
    }
    public RequestMappingHandlerMapping getHandlerMapping() {
        return handlerMapping;
    }
    protected Locale getLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request,
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale == null) {
            locale = request.getLocale();
        }
        if (locale == null) {
            locale = Locale.CHINESE;
        }
        return locale;
    }
    protected <E extends BaseDTO, T> DeferredResult<T> execute(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServlvetResponse,
            E requestDto, long timeoutMilliSeconds, ExecServiceTemplate<E, T> template,
            T timeoutReturndefaultWebMessage,
            T exceptionReturndefaultWebMessage, boolean throwHttpResponseErrorWhenException) {
        DeferredResult<T> deferredResult = null;
        try {
            Assert.isTrue(timeoutMilliSeconds <= defaultMaxTimeout,
                    String.format("timeout is invalid:%s milliseconds more than %s milliseconds",
                            timeoutMilliSeconds, defaultMaxTimeout));
            deferredResult = buildDeferredResultWithTimeout(timeoutMilliSeconds, timeoutReturndefaultWebMessage);
            Method method = null;
            if (this.getHandlerMapping() != null) {
                HandlerExecutionChain handler =
                        this.getHandlerMapping().getHandler(httpServletRequest);
                HandlerMethod hm = (HandlerMethod) handler.getHandler();
                method = hm.getMethod();
            }
            String requestUri = URL_PATH_HELPER.getRequestUri(httpServletRequest);
            Locale locale = getLocale(httpServletRequest);
            DeferredResultRunnable<E, T> r = new DeferredResultRunnable<E, T>(method, requestUri,
                    deferredResult, template, requestDto, locale, bundleMessageSource,
                    httpServletRequest, httpServlvetResponse, exceptionReturndefaultWebMessage,
                    throwHttpResponseErrorWhenException);
            getFrontTaskExecutor().submit(r);
        } catch (Exception e) {
            LOG.error("execute fail", e);
            T m = exceptionReturndefaultWebMessage;
            if (m != null && (m instanceof BaseMessage)) {
                ((BaseMessage) m)
                        .setResponseStatus(ResponseStatus.FRONT_INTERNAL_SERVER_ERROR);
            }
            deferredResult = new DeferredResult<T>();
            deferredResult.setResult(m);
            if (throwHttpResponseErrorWhenException) {
                httpServlvetResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } finally {
        }
        return deferredResult;
    }
    protected <E extends BaseDTO, T> DeferredResult<T> execute(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServlvetResponse,
            E requestDto, long timeoutMilliSeconds, ExecServiceTemplate<E, T> template,
            T timeoutReturndefaultWebMessage,
            T exceptionReturndefaultWebMessage) {
        return this.execute(httpServletRequest, httpServlvetResponse, requestDto, timeoutMilliSeconds, template,
                timeoutReturndefaultWebMessage, exceptionReturndefaultWebMessage, false);
    }
    protected void logMonitor(String callerClassName, String callerMethodName, long responseTime,
                              String resultFlag, Exception sysException, BizErrors errors) {
        String bizRetCode = null;
        String retErrMsg = null;
        if (StringUtils.equals(MonitorKeys.BIZ_FAIL_FLAG, resultFlag)) {
            BizError latestBizError = null;
            if (errors != null && errors.hasErrors()) {
                latestBizError = errors.getAllErrors().get(errors.getErrorCount() - 1);
                bizRetCode = latestBizError.getCode();
                retErrMsg = latestBizError.getDefaultMessage();
            }
        } else if (StringUtils.equals(MonitorKeys.SYS_FAIL_FLAG, resultFlag)) {
            if (sysException != null) {
                retErrMsg = StringUtils.substring(sysException.toString(), 0, 200);
            }
        }
        String formatMonitorMsg = String.format(MonitorKeys.REQUEST_SUCCESS_TEMPLATE,
                callerClassName,
                callerMethodName,
                "",
                String.valueOf(responseTime),
                resultFlag,
                bizRetCode,
                retErrMsg,
                "",
                "");
        MONITOR_LOGGER.info(formatMonitorMsg);
    }
    protected <T> T handleFailResponseMessage(Exception e, T exceptionReturndefaultWebMessage) {
        T m = exceptionReturndefaultWebMessage;
        if (m != null && (m instanceof BaseMessage)) {
            ((BaseMessage) m).setResponseStatus(ResponseStatus.FRONT_INTERNAL_SERVER_ERROR);
        }
        return m;
    }
    protected <T> T handleSuccessResponseMessage(T result, BizErrors bizErrors, Locale locale,
                                                 ReloadableResourceBundleMessageSource bundleMessageSource) {
        final Locale myLocale = locale == null ? Locale.getDefault() : locale;
        if (bizErrors != null && bizErrors.hasErrors()) {
            StringBuilder sb = THREADLOCAL_STRINGBUILDER_HOLDER.get().resetAndGetStringBuilder();
            List<BizError> allErrors = bizErrors.getAllErrors();
            BizError bizError = allErrors.get(allErrors.size() - 1);
            String code = bizError.getCode();
            String msg = bundleMessageSource.getMessage(bizError.getCode(), bizError.getArguments(),
                    bizError.getDefaultMessage(), myLocale);
            if (result != null && result instanceof BaseMessage) {
                ((BaseMessage) result).setCode(code);
                ((BaseMessage) result).setMsg(msg);
            }
        }
        return result;
    }
    public ResultDto getExceptionDefaultResult(HttpServletRequest httpRequest) {
        logger.warn("exception request: uri={}, parameters={}", httpRequest.getRequestURI(), httpRequest.getParameterMap());
        ResultDto response = new ResultDto();
        response.setCode(Constants.FAIL);
        response.setMessage("server error");
        return response;
    }
    public ResultDto getTimeoutDefaultResult(HttpServletRequest httpRequest) {
        logger.warn("timeout request: uri={}, parameters={}", httpRequest.getRequestURI(), httpRequest.getParameterMap());
        ResultDto response = new ResultDto();
        response.setCode(Constants.FAIL);
        response.setMessage("timeout");
        return response;
    }
    private class DeferredResultRunnable<E extends BaseDTO, T> implements Runnable {
        private DeferredResult<T> deferredResult;
        private ExecServiceTemplate<E, T> service;
        private E requestDto;
        private Locale locale;
        private ReloadableResourceBundleMessageSource bundleMessageSource;
        private Method callerMethod;
        private HttpServletRequest httpServletRequest;
        private HttpServletResponse httpServletResponse;
        private String requestUri;
        private T exceptionReturnDefaultObject;
        private boolean throwHttpResponseErrorWhenException;
        public DeferredResultRunnable(Method callerMethod, String requestUri, DeferredResult<T> d,
                                      ExecServiceTemplate<E, T> s, E requestDto, Locale locale,
                                      ReloadableResourceBundleMessageSource bundleMessageSource,
                                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                      T exceptionReturnDefaultObject, boolean throwHttpResponseErrorWhenException) {
            this.deferredResult = d;
            this.requestUri = requestUri;
            this.service = s;
            this.requestDto = requestDto;
            this.locale = locale;
            this.bundleMessageSource = bundleMessageSource;
            this.callerMethod = callerMethod;
            this.httpServletRequest = httpServletRequest;
            this.httpServletResponse = httpServletResponse;
            this.exceptionReturnDefaultObject = exceptionReturnDefaultObject;
            this.throwHttpResponseErrorWhenException = throwHttpResponseErrorWhenException;
        }
        @Override
        public void run() {
            T responseObject = null;
            T result = null;
            long beginTime = System.currentTimeMillis();
            String callerClassName = "unknow";
            String callerMethodName = "unknow";
            String statusCode = ResponseStatus.SUCCESS.getCode();
            String resultFlag = MonitorKeys.SUCCESS_FLAG;
            BizErrors bizErrors = new BizErrors();
            Exception sysException = null;
            try {
                if (this.callerMethod != null) {
                    callerClassName = this.callerMethod.getDeclaringClass().getName();
                    if (callerClassName.contains("CGLIB")) {
                        callerClassName = callerClassName.substring(0, callerClassName.indexOf("$$"));
                    }
                    callerMethodName = this.callerMethod.getName();
                }
                result = this.service.apply(requestDto, bizErrors);
                responseObject =
                        handleSuccessResponseMessage(result, bizErrors, locale, bundleMessageSource);
                statusCode = bizErrors != null && bizErrors.hasErrors()
                        ? ResponseStatus.FRONT_INTERNAL_SERVER_ERROR.getCode()
                        : ResponseStatus.SUCCESS.getCode();
                resultFlag = bizErrors != null && bizErrors.hasErrors()
                        ? MonitorKeys.BIZ_FAIL_FLAG
                        : MonitorKeys.SUCCESS_FLAG;
            } catch (Exception e) {
                sysException = e;
                LOG.error("service error,request message:" + requestDto, e);
                statusCode = ResponseStatus.FRONT_INTERNAL_SERVER_ERROR.getCode();
                responseObject = handleFailResponseMessage(e, this.exceptionReturnDefaultObject);
                if (this.throwHttpResponseErrorWhenException) {
                    httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
                resultFlag = MonitorKeys.SYS_FAIL_FLAG;
            } finally {
                deferredResult.setResult(responseObject);
                long endTime = System.currentTimeMillis();
                long responseTime = endTime - beginTime;
                logMonitor(callerClassName, callerMethodName, responseTime, resultFlag, sysException, bizErrors);
            }
        }
    }
}
