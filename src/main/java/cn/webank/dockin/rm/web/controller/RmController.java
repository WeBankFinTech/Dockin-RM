



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

import cn.webank.dockin.rm.bean.biz.AddInstanceDTO;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.service.RmService;
import cn.webank.dockin.rm.web.service.QueryRmService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Validated
@RestController
@RequestMapping("/rmController")
public class RmController extends AuthBaseController {
    public static final long EXEC_TIMEOUT = 5 * 60 * 1000;
    @Autowired
    private RmService rmService;
    @Autowired
    private QueryRmService queryRmService;

    @RequestMapping(value = "/getClusterId", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getClusterId(final HttpServletRequest httpRequest, final HttpServletResponse
            httpResponse, String hostIp, String podIp, String timestamp) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getClusterId(hostIp, podIp, timestamp)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodNetworkInfo", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodNetworkInfo(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String podName) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodNetworkInfo(podName)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodMultiNetwork", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodMultiNetwork(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String podName) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodMultiNetwork(podName)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodInfoBySubsystem", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodInfoBySubsystem(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String subsystem, String dcn) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodInfo(subsystem, dcn)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodInfosByPodNameList", method = RequestMethod.POST)
    public DeferredResult<ResultDto> getPodInfosByPodNameList(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, @RequestBody String podNameList, Boolean ignoreStatus) {
        List<String> podNames = JSON.parseArray(podNameList, String.class);
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodInfosByPodNameList(podNames, ignoreStatus)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodInfoByPodName", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodInfoByPodName(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String podName) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodInfoByPodName(podName)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodInfoByPodSetId", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodInfoByPodSetId(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String podSetId) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> queryRmService
                .getPodInfoByPodSetId(podSetId)), getTimeoutDefaultResult(httpRequest), getExceptionDefaultResult(httpRequest));
    }


    @RequestMapping(value = "/getPodInfoByPodIp", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodInfoByPodIp(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String podIp) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodInfoByPodIp(podIp)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    @RequestMapping(value = "/getPodInfoByHostIp", method = RequestMethod.GET)
    public DeferredResult<ResultDto> getPodInfoByHostIp(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, String hostIp) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .getPodInfoByHostIp(hostIp)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }


    @RequestMapping(value = "/addAppInstance", method = RequestMethod.POST)
    public DeferredResult<ResultDto> addPodInstance(final HttpServletRequest httpRequest, final
    HttpServletResponse httpResponse, @RequestBody AddInstanceDTO params) {
        return execute(httpRequest, httpResponse, null, EXEC_TIMEOUT, ((requestDto, bizErrors) -> rmService
                .addAppInstance(params)), null, getDefaultExceptionResult(httpRequest.getRemoteAddr()));
    }

    private ResultDto getDefaultExceptionResult(String requesterIp) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Constants.FAIL);
        resultDto.setMessage("unknow error, illegal requester ip: " + requesterIp);
        return resultDto;
    }
}