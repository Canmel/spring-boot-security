package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.UserService;
import com.goshine.web.enums.MenuLevel;
import com.goshine.web.enums.MenuStatus;
import com.goshine.web.enums.UserStatus;
import dto.User;
import exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/options")
public class OptionsController {


    @ResponseBody
    @GetMapping("/menus/statuses")
    public R menuStatus() {
        List list = new ArrayList();
        for (MenuStatus status : MenuStatus.values()) {
            Map<String, Object> statuses = new HashMap<>();
            statuses.put("label", status.getName());
            statuses.put("value", status.getStatus());
            list.add(statuses);
        }
        return R.ok().put("root", list);
    }

    @ResponseBody
    @GetMapping("/menus/levels")
    public R menuLevel() {
        List list = new ArrayList();
        for (MenuLevel status : MenuLevel.values()) {
            Map<String, Object> statuses = new HashMap<>();
            statuses.put("label", status.getName());
            statuses.put("value", status.getStatus());
            list.add(statuses);
        }
        return R.ok().put("root", list);
    }
}
