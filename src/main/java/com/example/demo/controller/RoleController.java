package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.Constants;
import com.example.demo.common.R;
import com.example.demo.common.ResultBean;
import com.example.demo.common.util.StringUtils;
import com.example.demo.common.util.WebContextUitls;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleMenu;
import com.example.demo.service.CommonService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 角色设置控制器
 * @date 2017年11月26日 上午21:32:27
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
    public ResultBean listPage(@RequestParam Map<String, Object> params) {
        double page = Double.parseDouble(params.get("page").toString());
        double limit = Double.parseDouble(params.get("limit").toString());
        params.put("offset", (int) ((page - 1) * limit));
        List<Role> list = roleService.listPage(params);

        // 返回数据
        ResultBean resultBean = ResultBean.getInstance();
        resultBean.setCount(roleService.countPage(params));
        resultBean.setData(list);
        return resultBean;
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
     * @param role
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public R save(HttpSession session, Role role) {
        R r = R.getInstance();

        int flag = 0;
        // 新增
        if (role.getRoleId() == null) {
            int roleId = commonService.getTableNewId("pn_role", "role_id", 10001);
            role.setRoleId(roleId);
            role.setCreateId(WebContextUitls.getCurrentUserId(session));
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            flag = roleService.add(role);
        } else {
            // role.setUpdateTime(new Date());
            flag = roleService.update(role);
        }

        // 结果返回
        if (flag > 0) {
            r.setCode(Constants.RESULT_CODE_SUCCESS);
            r.setMsg(Constants.RESULT_SUCCESS_DESCRIPTION);
        } else {
            r.setCode(Constants.RESULT_CODE_FAIL);
            r.setMsg(Constants.Result_FAIL_DESCRIPTION);
        }
        return r;
    }

    /**
     * 根据ID移除单个对象
     *
     * @param roleId
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestParam("roleId") Integer roleId) {
        R r = R.getInstance();

        int flag = roleService.remove(roleId);

        // 结果返回
        if (flag > 0) {
            r.setCode(Constants.RESULT_CODE_SUCCESS);
            r.setMsg(Constants.RESULT_SUCCESS_DESCRIPTION);
        } else {
            r.setCode(Constants.RESULT_CODE_FAIL);
            r.setMsg(Constants.Result_FAIL_DESCRIPTION);
        }
        return r;
    }

    /**
     * id数组批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/removeBatch")
    @ResponseBody
    public R removeBatch(@RequestBody String[] ids) {
        R r = R.getInstance();
        int flag = roleService.removeBatch(ids);

        // 结果返回
        if (flag > 0) {
            r.setCode(Constants.RESULT_CODE_SUCCESS);
            r.setMsg(Constants.RESULT_SUCCESS_DESCRIPTION);
        } else {
            r.setCode(Constants.RESULT_CODE_FAIL);
            r.setMsg(Constants.Result_FAIL_DESCRIPTION);
        }
        return r;
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
     * 保存角色菜单设置(需要事务)
     *
     * @param params
     * @return
     */
    @PostMapping("/saveRoleMenu")
    @ResponseBody
    public R saveRoleMenu(@RequestBody JSONObject params) {
        R r = R.getInstance();
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
        if (flag > 0) {
            r.setCode(Constants.RESULT_CODE_SUCCESS);
            r.setMsg(Constants.RESULT_SUCCESS_DESCRIPTION);
        } else {
            r.setCode(Constants.RESULT_CODE_FAIL);
            r.setMsg(Constants.Result_FAIL_DESCRIPTION);
        }
        return r;
    }
}
