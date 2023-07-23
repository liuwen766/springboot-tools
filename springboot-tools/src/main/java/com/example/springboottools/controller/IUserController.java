package com.example.springboottools.controller;

import com.example.springboottools.entity.IUser;
import com.example.springboottools.entity.Role;
import com.example.springboottools.entity.query.UserQueryBean;
import com.example.springboottools.service.IUserService;
import com.example.springboottools.util.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user/v2")
public class IUserController {

    @Autowired
    private IUserService userService;

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add/Edit User")
    @PostMapping("add")
    public ResponseResult<IUser> add(IUser user) {
        if (user.getId() == null || !userService.exists(user.getId())) {
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            Set<Role> roles = new HashSet<>(1);
            Role role = new Role();
            role.setId(1L);
            roles.add(role);
//            user.setRoles(roles);
            userService.save(user);
        } else {
            user.setUpdateTime(LocalDateTime.now());
            userService.update(user);
        }
        return ResponseResult.success(userService.find(user.getId()));
    }


    /**
     * @return user list
     */
    @ApiOperation("Query User One")
    @GetMapping("edit/{userId}")
    public ResponseResult<IUser> edit(@PathVariable("userId") Long userId) {
        return ResponseResult.success(userService.find(userId));
    }

    /**
     * @return user list
     */
    @ApiOperation("Query User Page")
    @GetMapping("list")
    public ResponseResult<Page<IUser>> list(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseResult.success(userService.findPage(UserQueryBean.builder().build(), PageRequest.of(pageNumber, pageSize)));
    }
}