package org.linkworld.ocean.controller;


import org.linkworld.ocean.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
}

