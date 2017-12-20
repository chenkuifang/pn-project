package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.entity.Goods;
import com.example.demo.service.CommonService;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品列表控制器
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list() {
        return "/goods/list";
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
        List<Goods> list = goodsService.listPage(params);
        int countPage = goodsService.countPage(params);

        return JsonResultUtils.jsonPageResult(list, countPage);
    }

    /**
     * 新增初始化视图
     *
     * @return 包含action判断页面是add/edit动作
     */
    @GetMapping("/add")
    public String add(Model model) {
        Goods goods = new Goods();
        goods.setStatus(1);
        model.addAttribute("goods", goods);
        return "/goods/edit";
    }

    /**
     * 编辑初始化视图
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Goods goods = goodsService.get(id);
        model.addAttribute("goods", goods);
        return "/goods/edit";
    }

    /**
     * 保存
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(Goods goods) {
        int flag;
        String operation;

        // 新增
        if (goods.getId() == null) {
            int id = commonService.getTableNewId("pn_goods", "id", 10001);
            goods.setId(id);
            flag = goodsService.add(goods);

            operation = Constants.INSERT;
        } else {
            flag = goodsService.update(goods);

            operation = Constants.UPDATE;
        }

        // 日志操作
        commonService.addLog(getClass(), "save()", operation, goods);

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
        int flag = goodsService.remove(id);

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
        int flag = goodsService.removeBatch(ids);

        // 日志操作
        commonService.addLog(getClass(), "removeBatch()", Constants.DELETE, ids);

        // 结果返回
        return JsonResultUtils.jsonResult(flag);
    }


}
