package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.ReimbursementService;
import com.goshine.web.enums.MenuLevel;
import com.goshine.web.enums.MenuStatus;
import dto.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reimbursement")
public class ReimbursementController extends BaseController {

    @Autowired
    private ReimbursementService service;

    @ResponseBody
    @GetMapping()
    public R menuStatus(Menu menu) {
        PageInfo pageInfo = super.query(menu);
        return R.ok().page(pageInfo);
    }

    @Override
    public BaseService getService() {
        return service;
    }
}
