package com.example.demo.controller;

import java.util.*;

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

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.common.util.MDUtils;
import com.example.demo.common.util.WebContextUtils;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.CommonService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * 用户控制类
 *
 * @author QuiFar
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CommonService commonService;

    /**
     * 列表视图初始化
     *
     * @return 视图名称
     */
    @RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {
        // 角色列表
        List<Role> roles = roleService.listByStatus(1);

        model.addAttribute("roles", roles);
        return "user/list";
    }

    /**
     * 根据条件获取列表
     *
     * @param params 页面请求参数
     * @return
     */
    @GetMapping("/listPage")
    @ResponseBody
    public JsonResult listPage(@RequestParam Map<String, Object> params) {
        List<User> list = userService.listPage(params);
        int countPage = userService.countPage(params);
        // 返回
        return JsonResultUtils.jsonPageResult(list, countPage);
    }

    /**
     * 新增初始化view
     *
     * @param model 需要返回的数据模型对象
     * @return 表示返回的ViewName, 该值用视图解析器处理, 最终还是封装成ModelAndView对象
     */
    @GetMapping("/add")
    public String add(Model model) {
        // 初始值
        User user = new User();
        user.setStatus(1);
        user.setSex(1);
        // 角色列表
        List<Role> roles = roleService.listByStatus(1);

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("action", "add");
        return "user/edit";
    }

    /**
     * 编辑初始化View
     *
     * @param id    编码
     * @param model 需要返回的模型对象
     * @return 视图名称
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userService.get(id);
        // 角色列表
        List<Role> roles = roleService.listByStatus(1);

        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        return "user/edit";
    }

    /**
     * 保存,这里需要校验用户名和手机是唯一的，这一步可以参考商品编码唯一验证方法
     * 这里暂时不做校验
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(User user) throws Exception {
        int flag;
        String operation;

        // 新增
        if (user.getId() == null) {
            int id = commonService.getTableNewId("pn_user", "id");
            user.setId(id);
            // 暂时没有部门信息
            user.setDepartmentId(10001);
            // 初始化密码
            user.setPassword(MDUtils.encodeMD5("123456"));
            user.setCreateId(WebContextUtils.getCurrentUserId());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            flag = userService.add(user);

            operation = Constants.INSERT;
        } else {
            user.setUpdateTime(new Date());
            flag = userService.update(user);

            operation = Constants.UPDATE;
        }

        // 日志操作
        commonService.addLog(getClass(), "save()", operation, user);

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * 根据ID移除单个对象
     *
     * @param id
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public JsonResult remove(@RequestParam("id") Integer id) {
        int flag = 0;
        // 禁止删除超级管理员
        if (!id.equals(10001)) {
            flag = userService.remove(id);
        }

        // 日志操作
        commonService.addLog(getClass(), "remove()", Constants.DELETE, id);

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
        int flag = 0;
        // 禁止删除超级管理员
        if (!Arrays.asList(ids).contains("10001")) {
            flag = userService.removeBatch(ids);
        }

        // 日志操作
        commonService.addLog(getClass(), "removeBatch()", Constants.DELETE, ids);

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * 左边菜单列表
     *
     * @return
     */
    @GetMapping("/listMenu")
    @ResponseBody
    public JsonResult listMenu() {
        // 当前登录信息
        int userId = WebContextUtils.getCurrentUserId();

        User user = userService.get(userId);

        // 获取该角色的系统菜单列表
        List<Menu> menus = menuService.listByRoleId(user.getRoleId());
        return JsonResultUtils.jsonResult(menus);
    }

    /**
     * 修改密码初始化视图
     *
     * @return
     */
    @GetMapping("/changePwd")
    public String changePwd(Model model) {
        String userName = WebContextUtils.getCurrentUserName();
        model.addAttribute("userName", userName);
        return "user/changePwd";
    }

    /**
     * 获取修改密码操作
     *
     * @param userName 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @param model    需要返回的数据
     * @return
     * @throws Exception
     */
    @PostMapping("/changePwdPost")
    @ResponseBody
    public JsonResult changePwdPost(@RequestParam String userName, @RequestParam String oldPwd,
                                    @RequestParam String newPwd, HttpSession session, Model model) throws Exception {
        int flag = 0;

        // 根据用户名获取用户信息
        User user = userService.getByUserName(userName);
        String encodePwd = MDUtils.encodeMD5(oldPwd);
        if (user != null && user.getPassword().equals(encodePwd)) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setPassword(MDUtils.encodeMD5(newPwd));

            // 修改密码
            flag = userService.update(newUser);
        } else {
            // 密码错误
            return JsonResultUtils.jsonResult("密码错误");
        }

        // 成修改后 清空session
        if (flag >= 1) {
            // 清除session
            Enumeration<String> em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                session.removeAttribute(em.nextElement());
            }
        }

        model.addAttribute("userName", userName);
        return JsonResultUtils.jsonResult(flag);
    }

    /**
     * 密码重置
     *
     * @return
     */
    @GetMapping("/resetPwd")
    public String resetPwd() {
        return "user/resetPwd";
    }

}
