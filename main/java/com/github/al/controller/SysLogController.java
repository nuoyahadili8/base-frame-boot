package com.github.al.controller;

import com.github.al.common.utils.PageUtils;
import com.github.al.common.utils.Query;
import com.github.al.common.utils.R;
import com.github.al.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.al.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统日志
 *
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-03-08 10:40:56
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    public String list() {
        return "log/list";
    }
    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/listData")
    @RequiresPermissions("sys:log:list")
    public R listData(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysLog> sysLogList = sysLogService.queryList(query);
        int total = sysLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * @author chenyi
     * @Description 新增
     * @param
     * @date 2017/6/27 11:17
     **/
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("sys:log:add")
    public R save(@RequestBody SysLog syslog){
        syslog.setCreateDate(new Date());
        syslog.setIp("1231454685");
        syslog.setMethod("asd");
        syslog.setOperation("asdawd");
        syslog.setParams("asdawdaw");
        sysLogService.save(syslog);

        return R.ok();
    }
   /**
    * @author chenyi
    * @Description 修改
    * @param
    * @date 2017/6/27 11:17
    **/
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:log:edit")
    public R update(@RequestBody SysLog syslog){
        sysLogService.update(syslog);
        return R.ok();
    }
    /**
     * @author chenyi
     * @Description 跳转到新增页面
     * @param
     * @date 2017/6/27 11:17
     **/
    @RequestMapping("/add")
    @RequiresPermissions("sys:log:add")
    public String add(){
        return "syslog/add.jsp";
    }
    /**
     * @author chenyi
     * @Description  跳转到修改页面
     * @param
     * @date 2017/6/27 11:17
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sys:log:edit")
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id){
        SysLog sysLog = sysLogService.queryObject(id);
        model.addAttribute("model", sysLog);
        return "syslog/edit.jsp";
    }
    /**
     * @author chenyi
     * @Description 删除
     * @param
     * @date 2017/6/27 11:37
     **/
    @ResponseBody
    @RequestMapping("/deleteBatch")
    @RequiresPermissions("sys:log:delete")
    public R deleteBatch(@RequestBody Long[] ids){
        sysLogService.deleteBatch(ids);
        return R.ok();
    }
}
