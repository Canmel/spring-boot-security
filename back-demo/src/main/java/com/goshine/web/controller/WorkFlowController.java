package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.WorkFlowService;
import com.goshine.web.enums.*;
import dto.workFlow.WorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/workflow")
public class WorkFlowController extends BaseController {

    private final String controllerName = "工作流";

    @GetMapping
    @ResponseBody
    public R query(WorkFlow flow) {
        PageInfo pageInfo = super.query(flow);
        return R.ok().page(pageInfo);
    }


    @PostMapping
    @ResponseBody
    public R create(@RequestBody @Valid WorkFlow workFlow, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        workFlow.setStatus(WorkFlowStatus.ACTIVE.getStatus());
        workFlow.setIsPublic(WorkFlowPublic.UNPUBLIC.getStatus());
        if (!getService().create(workFlow)) {
            return R.error("创建", controllerName);
        }
        return R.ok().msg("创建", controllerName);
    }


    @GetMapping("types")
    @ResponseBody
    public R types() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (WorkFlowType flowType : WorkFlowType.values()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("label", flowType.getName());
            map.put("value", flowType.getStatus());
            list.add(map);
        }
        return R.ok().put(list);
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public R details(@PathVariable(name = "id") String id) {
        WorkFlow flow = new WorkFlow();
        flow.setId(Integer.parseInt(id));
        WorkFlow workFlow = (WorkFlow) super.details(flow);
        if (ObjectUtils.isEmpty(workFlow)) R.error("获取", controllerName);
        return R.ok().model(workFlow);
    }

    @Autowired
    private WorkFlowService service;


    @Override
    public BaseService getService() {
        return service;
    }
}
