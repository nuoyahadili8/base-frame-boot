package com.github.al.controller;

import com.github.al.common.exception.MyException;
import com.github.al.common.log.SysLog;
import com.github.al.common.utils.PageUtils;
import com.github.al.common.utils.Query;
import com.github.al.common.utils.R;
import com.github.al.entity.SysOss;
import com.github.al.service.SysOssService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.github.al.common.enumresource.StateEnum;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * oss配置
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-12-13 10:07:04
 */
@Controller
@RequestMapping("sysoss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;

    @RequestMapping("/list")
    public String list() {
        return "sysoss/list";
    }
	/**
	 * 列表
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("sysoss:list")
    @SysLog
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysOss> sysOssList = sysOssService.getList(query);
		int total = sysOssService.getCount(query);
		
		PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("sysoss:save")
    public String add(){
        return "sysoss/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sysoss:update")
    public String edit(Model model, @PathVariable("id") String id){
		SysOss sysOss = sysOssService.get(id);
        model.addAttribute("model",sysOss);
        return "sysoss/edit";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{bucket}")
    @RequiresPermissions("sysoss:info")
    public R info(@PathVariable("bucket") String bucket){
        SysOss sysOss = sysOssService.get(bucket);
        return R.ok().put("sysOss", sysOss);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存oss配置")
	@RequestMapping("/save")
	@RequiresPermissions("sysoss:save")
	public R save(@RequestBody SysOss sysOss){
        SysOss sysoss=sysOssService.get(sysOss.getBucket());
        if(sysoss!=null){
            throw new MyException("该bucket已存在");
        }
        sysOss.setCreateTime(new Date());
		sysOssService.save(sysOss);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改oss配置")
	@RequestMapping("/update")
	@RequiresPermissions("sysoss:update")
	public R update(@RequestBody SysOss sysOss){
		sysOssService.update(sysOss);
		
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用oss配置")
    @RequestMapping("/enable")
    @RequiresPermissions("sysoss:update")
    public R enable(@RequestBody String[] ids){
        String stateValue=StateEnum.ENABLE.getCode();
		sysOssService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用oss配置")
    @RequestMapping("/limit")
    @RequiresPermissions("sysoss:update")
    public R limit(@RequestBody String[] ids){
        String stateValue= StateEnum.LIMIT.getCode();
		sysOssService.updateState(ids,stateValue);
        return R.ok();
    }
	
	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除oss配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sysoss:delete")
	public R delete(@RequestBody String[] buckets){
		sysOssService.deleteBatch(buckets);
		
		return R.ok();
	}
	
}
