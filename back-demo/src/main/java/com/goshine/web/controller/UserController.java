package com.goshine.web.controller;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.goshine.core.base.R;
import com.goshine.service.BaseService;
import com.goshine.service.UserService;
import com.goshine.web.enums.UserStatus;
import dto.BaseModel;
import dto.PageQuery;
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
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    public UserService userService;

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping("/current")
    public User getUserDetail(@AuthenticationPrincipal UserDetails user) {
        return userService.loadUserByUserName(user.getUsername());
    }

    @GetMapping
    @ResponseBody
    public R query(User user) {
        PageInfo pageInfo = super.query(user);
        return R.ok().page(pageInfo);
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public R details(@PathVariable(name = "id") String id) {
        User user = new User();
        user.setId(Integer.parseInt(id));
        User details = (User) super.details(user);
        if (ObjectUtils.isEmpty(details)) {
            R.error("获取用户失败");
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
            return R.error("创建用户失败！");
        }
        return R.ok().put("msg", "创建用户成功！");
    }

    @PutMapping("/{id:\\d+}")
    public R edit(@Valid @RequestBody User user, BindingResult errors) {
        R resp = generateErrorResp(errors);
        if (!ObjectUtils.isEmpty(resp)) {
            return resp;
        }
        if(!super.update(user)) {
            return R.error("修改用户失败");
        }
        return R.ok().msg("修改用户成功");
    }


    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

    @GetMapping("/error/{id:\\d+}")
    public void toError(@PathVariable String id) {
        throw new UserNotExistException(id);
    }

    @Override
    public BaseService getService() {
        return userService;
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
