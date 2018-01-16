package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.example.demo.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 日志文件输出控制类
 *
 * @author QuiFar
 **/
@Controller
@RequestMapping("/logs")
public class LogsController {

    @Value("${mylogs.Path}")
    private String logFilePath;

    /**
     * 系统日志初始视图
     *
     * @return 视图名称
     * @throws FileNotFoundException
     */
    @RequestMapping(path = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) throws FileNotFoundException {
        Map<String, String> map = FileUtils.getFileNameLists(logFilePath);
        model.addAttribute("data", map);
        return "logs/list";
    }

    /**
     * 系统日志翻页列表
     *
     * @param fileName 页面请求参数
     * @return
     */
    @GetMapping("/detail/{fileName}")
    public String get(@PathVariable("fileName") String fileName, Model model) {
        // 根据系统获取文件分隔符
        String fileSeparator = System.getProperty("file.separator");
        System.err.println(fileSeparator);
        String sfilePath = logFilePath.concat(fileSeparator).concat(fileName).concat(".log");
        List<String> data = FileUtils.readFileByLines(sfilePath);
        model.addAttribute("data", data);
        return "logs/detail";
    }

}