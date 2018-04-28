package com.goshine.web.controller;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.MenuService;
import com.goshine.web.enums.MenuStatus;
import dto.Menu;
import exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController extends BaseController {

    private final String controllerName = "菜单";

    @Autowired
    public MenuService menuService;

    @GetMapping
    @ResponseBody
    public R query(Menu menu) {
        PageInfo pageInfo = super.query(menu);
        return R.ok().page(pageInfo);
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public R details(@PathVariable(name = "id") String id) {
        Menu menu = new Menu();
        menu.setId(Integer.parseInt(id));
        Menu details = (Menu) super.details(menu);
        if (ObjectUtils.isEmpty(details)) {
            R.error("获取", controllerName);
        }
        return R.ok().model(details);
    }

    @PostMapping
    @ResponseBody
    public R create(@RequestBody @Valid Menu menu, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        menu.setStatus(MenuStatus.ACTIVE.getStatus());
        if (!super.create(menu)) {
            return R.error("创建", controllerName);
        }
        return R.ok().msg("创建", controllerName);
    }

    @PutMapping("/{id:\\d+}")
    public R edit(@Valid @RequestBody Menu menu, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        if (!super.update(menu)) {
            return R.error("修改", controllerName);
        }
        return R.ok().msg("修改", controllerName);
    }


    @DeleteMapping("/{id:\\d+}")
    public R delete(@PathVariable Integer id) {
        Menu menu = new Menu();
        menu.setId(id);
        if (super.delete(menu)) {
            return R.ok().msg("删除", controllerName);
        } else {
            return R.error("删除", controllerName);
        }
    }

    @GetMapping("/error/{id:\\d+}")
    public void toError(@PathVariable String id) {
        throw new UserNotExistException(id);
    }


    @ResponseBody
    @GetMapping("/tops")
    public R topMenus() {
        List<Menu> menus = menuService.getTopMenus();
        if (ObjectUtils.isEmpty(menus)) {
            return R.error("查询", controllerName);
        } else {
            return R.ok().msg("操作成功").put(menus);
        }
    }

    @ResponseBody
    @GetMapping("/subs")
    public R subMenus() {
        List<Menu> menus = menuService.getSubMenus();
        if (ObjectUtils.isEmpty(menus)) {
            return R.error("查询", controllerName);
        } else {
            return R.ok().msg("操作成功").put(menus);
        }
    }

    @ResponseBody
    @GetMapping("/rid/{id:\\d+}")
    public R getMenusByRoleId(@PathVariable Integer id) {
        List<Menu> menus = menuService.getMenusByRoleId(id);
        if (ObjectUtils.isEmpty(menus)) {
            return R.error("查询", controllerName);
        } else {
            return R.ok().msg("操作成功").put(menus);
        }
    }

    @Override
    public BaseService getService() {
        return menuService;
    }

    private String encodeUserPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public R generateErrorResp(BindingResult errors) {
        if (errors.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            errors.getAllErrors().stream().forEach(error -> {
                msg.append(error.getDefaultMessage());
            });
            return R.error(msg.toString());
        } else {
            return null;
        }
    }
}
