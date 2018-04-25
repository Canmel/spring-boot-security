package com.goshine.web.controller;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.RoleService;
import com.goshine.service.UserService;
import com.goshine.web.enums.UserStatus;
import dto.BaseModel;
import dto.PageQuery;
import dto.Role;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import exceptions.UserNotExistException;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {

    @Autowired
    public RoleService roleService;

    @GetMapping
    @ResponseBody
    public R query(Role role) {
        PageInfo pageInfo = super.query(role);
        return R.ok().page(pageInfo);
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public R details(@PathVariable(name = "id") String id) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        User details = (User) super.details(user);
        if (ObjectUtils.isEmpty(details)) {
            R.error("获取角色失败");
        } else {
            details.setPassword(null);
        }

        return R.ok().model(details);
    }

    @PostMapping
    @ResponseBody
    public R create(@RequestBody @Valid User user, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        user.setStatus(UserStatus.ACTIVE.getStatus());
        user.setPassword(encodeUserPassword(user.getPassword()));
        if (!super.create(user)) {
            return R.error("创建角色失败！");
        }
        return R.ok().put("msg", "创建角色成功！");
    }

    @PutMapping("/{id:\\d+}")
    public R edit(@Valid @RequestBody User user, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        if (!super.update(user)) {
            return R.error("修改角色失败");
        }
        return R.ok().msg("修改角色成功");
    }


    @DeleteMapping("/{id:\\d+}")
    public R delete(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        if (super.delete(user)) {
            return R.ok().msg("删除角色成功");
        } else {
            return R.error("删除角色失败");
        }
    }

    @GetMapping("/error/{id:\\d+}")
    public void toError(@PathVariable String id) {
        throw new UserNotExistException(id);
    }

    @Override
    public BaseService getService() {
        return roleService;
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
