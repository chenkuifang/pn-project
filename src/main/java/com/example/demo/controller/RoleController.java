package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.common.util.StringUtils;
import com.example.demo.common.util.WebContextUtils;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleMenu;
import com.example.demo.service.CommonService;
import com.example.demo.service.RoleService;

/**
 * 角色管理控制类
 *
 * @author QuiFar
 * @version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list() {
        return "/role/list";
    }

    /**
     * 根据条件获取角色列表
     *
     * @param params 页面请求参数
     * @return
     */
    @GetMapping("/listPage")
    @ResponseBody
    public JsonResult listPage(@RequestParam Map<String, Object> params) {
        double page = Double.parseDouble(params.get("page").toString());
        double limit = Double.parseDouble(params.get("limit").toString());
        params.put("offset", (int) ((page - 1) * limit));

        List<Role> list = roleService.listPage(params);
        int countPage = roleService.countPage(params);

        return JsonResultUtils.jsonPageResult(list, countPage);
    }

    /**
     * 新增初始化视图 （参数验证的时候必须包括action字段）
     *
     * @param model
     * @return 包含action判断页面是add/edit动作
     */
    @GetMapping("/add")
    public String add(Model model) {
        // 初始值
        Role role = new Role();
        role.setStatus(1);
        model.addAttribute("role", role);
        model.addAttribute("action", "add");
        return "/role/edit";
    }

    /**
     * 编辑初始化视图
     *
     * @param roleId
     * @param model
     * @return
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") int roleId, Model model) {
        Role role = roleService.get(roleId);
        model.addAttribute("action", "edit");
        model.addAttribute("role", role);
        return "/role/edit";
    }

    /**
     * 保存
     *
     * @param session
     * @param role
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(HttpSession session, Role role) {

        int flag = 0;
        // 新增
        if (role.getRoleId() == null) {
            int roleId = commonService.getTableNewId("pn_role", "role_id", 10001);
            role.setRoleId(roleId);
            role.setCreateId(WebContextUtils.getCurrentUserId(session));
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            flag = roleService.add(role);
        } else {
            // role.setUpdateTime(new Date());
            flag = roleService.update(role);
        }

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * 根据ID移除单个对象
     *
     * @param roleId
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public JsonResult remove(@RequestParam("roleId") Integer roleId) {
        int flag = roleService.remove(roleId);

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * id数组批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/removeBatch")
    @ResponseBody
    public JsonResult removeBatch(@RequestBody String[] ids) {
        int flag = roleService.removeBatch(ids);

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * 菜单权限设置视图初始化
     *
     * @param model
     * @return
     */
    @GetMapping("/editMenu/{roleId}")
    public String editMenu(@PathVariable("roleId") int roleId, Model model) {
        model.addAttribute("action", "add");
        model.addAttribute("roleId", roleId);
        return "/role/editMenu";
    }

    /**
     * 保存角色菜单设置(需要事务),并且这一步必须是同步的,目前暂不考虑并发问题
     *
     * @param params
     * @return
     */
    @PostMapping("/saveRoleMenu")
    @ResponseBody
    public JsonResult saveRoleMenu(@RequestBody JSONObject params) {
        int flag = 1;

        // 参数
        int roleId = params.getInteger("roleId");
        String[] menuIds = StringUtils.string2Array(params.getString("ids"));

        // 封装roleMenu类
        RoleMenu roleMenu;
        List<RoleMenu> roleMenus = new ArrayList<>();
        int id = commonService.getTableNewId("pn_role_menu", "id", 10001);
        for (int i = 0, len = menuIds.length; i < len; i++) {
            roleMenu = new RoleMenu();
            roleMenu.setId(id + i);
            roleMenu.setMenuId(Integer.parseInt(menuIds[i]));
            roleMenu.setRoleId(roleId);
            roleMenus.add(roleMenu);
        }

        // 增加角色菜单
        try {
            roleService.addMenuBatch(roleId, roleMenus);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }
}
