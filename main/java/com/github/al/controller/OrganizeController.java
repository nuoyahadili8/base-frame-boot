package com.github.al.controller;

import com.github.al.common.log.SysLog;
import com.github.al.common.utils.*;
import com.github.al.entity.Organize;
import com.github.al.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.al.common.enumresource.StateEnum;
import com.github.al.common.enumresource.TopMenuEnum;

import com.github.al.service.OrganizeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 部门
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 17:39:31
 */
@Controller
@RequestMapping("organize")
public class OrganizeController {
	@Autowired
	private OrganizeService organizeService;
	@Autowired
    private SysMenuService sysMenuService;


    @RequestMapping("/list")
    public String list() {
        return "organize/list";
    }
	/**
	 * 列表
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("organize:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Organize> organizeList = organizeService.queryList(query);
		int total = organizeService.queryTotal(query);
        if (organizeList!=null&&organizeList.size()>0){
            for (int i = 0; i < organizeList.size(); i++) {
                if (TopMenuEnum.TopMenu.getCode().equals(organizeList.get(i).getParentOrgId())){
                    organizeList.get(i).setParentOrgName(TopMenuEnum.TopMenu.getDesc());
                    organizeList.get(i).setParentOrgId("-");
                }
            }
        }

		PageUtils pageUtil = new PageUtils(organizeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("organize:save")
    public String add(){
        return "/organize/add";
    }
    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping("/select")
    public R select() {

        List<Organize> organizeList = organizeService.getList();

        //添加顶级菜单
        Organize root = new Organize();

        root.setOrgId(TopMenuEnum.TopOrg.getCode());
        root.setOrgName(TopMenuEnum.TopOrg.getDesc());
        root.setParentOrgId("-1");
        root.setOpen(true);
        organizeList.add(root);
        List<ZtreeBean> ztreeBeans = new ArrayList<>();
        for (Organize organize : organizeList) {
            ZtreeBean tree = new ZtreeBean();
            tree.setId(organize.getOrgId() + "");
            tree.setpId(organize.getParentOrgId() + "");
            tree.setName(organize.getOrgName());
            tree.setOpen(organize.isOpen() + "");
            tree.setChkDisabled("false");
            ztreeBeans.add(tree);
        }

        return R.ok().put("data", ztreeBeans);
    }
    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("organize:update")
    public String edit(Model model, @PathVariable("id") String id){
		Organize organize = organizeService.queryObject(id);
        model.addAttribute("model",organize);
        return "organize/edit";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{orgId}")
    @RequiresPermissions("organize:info")
    public R info(@PathVariable("orgId") String orgId){
        Organize organize = organizeService.queryObject(orgId);
        return R.ok().put("organize", organize);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存部门")
	@RequestMapping("/save")
	@RequiresPermissions("organize:save")
	public R save(@RequestBody Organize organize){
        //判断部门编号是否存在
        Organize oldOrganize  = organizeService.queryByOrgCode(organize.getOrgCode());
        if (!StringUtil.isNullOrEmpty(oldOrganize)){
            return R.error("部门编号已存在");
        }

        organizeService.save(organize);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改部门")
	@RequestMapping("/update")
	@RequiresPermissions("organize:update")
	public R update(@RequestBody Organize organize){

		organizeService.update(organize);
		
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用部门")
    @RequestMapping("/enable")
    @RequiresPermissions("organize:update")
    public R enable(@RequestBody String[] ids){
        String stateValue= StateEnum.ENABLE.getCode();
		organizeService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用部门")
    @RequestMapping("/limit")
    @RequiresPermissions("organize:update")
    public R limit(@RequestBody String[] ids){
        String stateValue=StateEnum.LIMIT.getCode();
		organizeService.updateState(ids,stateValue);
        return R.ok();
    }

	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除部门")
	@RequestMapping("/delete")
	@RequiresPermissions("organize:delete")
	public R delete(@RequestBody String[] orgIds){

        for (String orgId : orgIds){
            organizeService.delete(orgId);
        }
		
		return R.ok();
	}
	
}
