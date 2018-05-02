package com.goshine.web.controller;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.MenuService;
import com.goshine.service.RoleService;
import com.goshine.service.UserService;
import com.goshine.web.enums.RoleStatus;
import com.goshine.web.enums.UserStatus;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import exceptions.UserNotExistException;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {

    @Autowired
    public RoleService roleService;

    @Autowired
    public MenuService menuService;

    @GetMapping
    @ResponseBody
    public R query(Role role) {
        PageInfo pageInfo = super.query(role);
        return R.ok().page(pageInfo);
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public R details(@PathVariable(name = "id") String id) {
        Role role = new Role();
        role.setId(Integer.parseInt(id));
        Role details = (Role) super.details(role);

        if (ObjectUtils.isEmpty(details)) {
            R.error("获取角色失败");
        }
        List<Menu> menus = menuService.getMenusByRoleId(details.getId());
        details.setMenus(menus);
        return R.ok().model(details);
    }

    @PostMapping
    @ResponseBody
    public R create(@RequestBody @Valid Role role, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        role.setStatus(RoleStatus.ACTIVE.getStatus());
        if (!super.create(role)) {
            return R.error("创建角色失败！");
        }
        return R.ok().put("msg", "创建角色成功！");
    }

    @PutMapping("/{id:\\d+}")
    public R edit(@Valid @RequestBody Role role, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        if (!super.update(role)) {
            return R.error("修改角色失败");
        }
        return R.ok().msg("修改角色成功");
    }


    @DeleteMapping("/{id:\\d+}")
    public R delete(@PathVariable Integer id) {
        Role role = new Role();
        role.setId(id);
        if (super.delete(role)) {
            return R.ok().msg("删除角色成功");
        } else {
            return R.error("删除角色失败");
        }
    }

    @PostMapping("/menus")
    public R uodateMenus(@RequestBody Role role) {
        if(roleService.updateMemus(role.getId(), role.getMenuIds())){
            return  R.ok().msg("更新角色持有菜单成功！");
        }
        return R.error().msg("");
    }

    @GetMapping("/all")
    public R all(){
        List roles = roleService.all();
        if(ObjectUtils.isEmpty(roles)){
            return R.error("获取角色失败");
        }
        return R.ok().model(roles);
    }


    @Override
    public BaseService getService() {
        return roleService;
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
