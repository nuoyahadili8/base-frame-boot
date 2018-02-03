package com.github.al.controller;


import com.github.al.common.enumresource.TopMenuEnum;
import com.github.al.common.exception.MyException;
import com.github.al.common.log.SysLog;
import com.github.al.common.utils.*;
import com.github.al.entity.SysMenu;
import com.github.al.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author chenyi
 * @Description 系统菜单
 * @date 2017/6/27 17:28
 **/
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/list")
    public String list() {
        return "menu/list";
    }

    /**
     * 所有菜单列表
     */
    @ResponseBody
    @RequestMapping("/listData")
    @RequiresPermissions("sys:menu:list")
    public R listData(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysMenu> menuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public R select() {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(Long.parseLong(TopMenuEnum.TopMenu.getCode()));
        root.setName(TopMenuEnum.TopMenu.getDesc());
        root.setParentId((long) -1);
        root.setOpen(true);
        menuList.add(root);
        List<ZtreeBean> ztreeBeans = new ArrayList<>();
        for (SysMenu menu : menuList) {
            ZtreeBean tree = new ZtreeBean();
            tree.setId(menu.getMenuId() + "");
            tree.setpId(menu.getParentId() + "");
            tree.setName(menu.getName());
            tree.setOpen(menu.getOpen() + "");
            tree.setChkDisabled("false");
            ztreeBeans.add(tree);
        }

        return R.ok().put("data", ztreeBeans);
    }

    /**
     * layui tree 的菜单列表
     */
    @ResponseBody
    @RequestMapping("/menuList")
    @RequiresPermissions("sys:menu:menuList")
    public R menuList() {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.findByParentId((long) 0);
        for (SysMenu sysMenu : menuList) {
            List<SysMenu> children = sysMenuService.findByParentId(sysMenu.getMenuId());
            setChildren(children);
            sysMenu.setChildren(children);
        }


        return R.ok().put("page", menuList);
    }


    public void setChildren(List<SysMenu> sysMenuEntities) {
        for (SysMenu sysMenu : sysMenuEntities) {
            List<SysMenu> children = sysMenuService.findByParentId(sysMenu.getMenuId());
            setChildren(children);
            sysMenu.setChildren(children);
        }

    }

    /**
     * 角色授权菜单
     */
    @ResponseBody
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public R perms() {
        //查询列表数据
        List<SysMenu> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() == Constant.SUPER_ADMIN) {
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        } else {
            menuList = sysMenuService.queryUserList(getUserId());
        }

        return R.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @ResponseBody
    @RequestMapping("/treePerms")
    @RequiresPermissions("sys:menu:perms")
    public R treePerms() {
        //查询列表数据
        List<SysMenu> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() == Constant.SUPER_ADMIN) {
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        } else {
            menuList = sysMenuService.queryUserList(getUserId());
        }
        List<ZtreeBean> ztreeBeans = new ArrayList<>();
        for (SysMenu menu : menuList) {
            ZtreeBean tree = new ZtreeBean();
            tree.setpId(menu.getParentId() + "");
            tree.setName(menu.getName());
            tree.setOpen(menu.getOpen() + "");
            tree.setChkDisabled("false");
            tree.setId(menu.getMenuId() + "");

            ztreeBeans.add(tree);
        }
        return R.ok().put("data", ztreeBeans);
    }

    /**
     * 菜单信息
     */
    @ResponseBody
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * @param
     * @author chenyi
     * @Description 跳转到新增页面
     * @date 2017/6/27 11:17
     **/
    @RequestMapping("/add")
    @RequiresPermissions("sys:menu:save")
    public String add() {
        return "menu/add";
    }

    /**
     * @param
     * @author chenyi
     * @Description 跳转到修改页面
     * @date 2017/6/27 11:17
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sys:menu:update")
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        String topMenu = TopMenuEnum.TopMenu.getCode();
        //如果是一级菜单 设置默认值
        SysMenu sysMenu = sysMenuService.queryObject(id);
        if (Long.parseLong(topMenu) == sysMenu.getParentId()) {
            sysMenu.setParentName(TopMenuEnum.TopMenu.getDesc());
        }
        model.addAttribute("model", sysMenu);
        return "menu/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody Long[] menuIds) {
        for (Long menuId : menuIds) {
            if (menuId.longValue() <= 30) {
                return R.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

    /**
     * 用户菜单列表
     */
    @ResponseBody
    @RequestMapping("/user")
    public R user() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        return R.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new MyException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new MyException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new MyException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new MyException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new MyException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
