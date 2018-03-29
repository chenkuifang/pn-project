package com.example.demo.init;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;

/**
 * 自定义注解自动扫描初始化方法。扫描部分不规范代码。这里列出2种：
 * <p>
 * 1.使用servlet 容器启动时启动的注解 @PostConstruct
 * <p>
 * 2.使用应用的监听器ApplicationListener接口的onApplicationEvent方法
 *
 * @author QuiFar
 * @version V1.0
 */
@Component
@Slf4j
public class InitAnnotation {

	// 是否执行检查代码，一般开发过程中为true，生产环境为false
	@Value("${pn.isCheckCode}")
	private boolean isCheckCode;

	// 规定方法的返回值
	private String str = String.class.getName() + " , " + JsonResult.class.getName();

	// 需要扫描的基础包
	private String basePackage = "com.example.demo.controller";

	List<String> packageNames = new ArrayList<>();

	@PostConstruct
	public void init() throws Exception {
		if (isCheckCode) {
			scanPackage(basePackage);
			checkMethod();
		}
	}

	/**
	 * 检查方法的返回值是否正确
	 *
	 * @throws Exception
	 */
	private void checkMethod() throws Exception {
		if (packageNames.size() <= 0) {
			return;
		}
		// 遍历包下的所有类
		for (String className : packageNames) {
			Class<?> cName = Class.forName(className.replace(".class", "").trim());
			if (cName.isAnnotationPresent(Controller.class)) {
				// 获取控制类下的所有方法
				Method[] methods = cName.getDeclaredMethods();
				boolean flag = isContailsAnnotation(methods);
				if (!flag) {
					log.error(cName.getSimpleName() + "出错啦,启动失败！");
					// 方法一、关闭JVM，调用shutdown hooks,正常关闭
					System.exit(0);
					// 方法二
					// throw new RuntimeException();
				}
			}
		}

	}

	/**
	 * 是否包含指定的注解名称
	 *
	 * @param methods
	 *            方法数组
	 * @return
	 */
	private boolean isContailsAnnotation(Method[] methods) {
		// 遍历类下的所有方法
		for (Method m : methods) {
			// 每个方法是否都包含某个注解
			if (m.isAnnotationPresent(RequestMapping.class) || m.isAnnotationPresent(GetMapping.class)
					|| m.isAnnotationPresent(PostMapping.class)) {
				// 方法的返回值
				Class<?> returnType = m.getReturnType();

				if (!str.contains(returnType.getName())) {
					log.error(m.getName() + "方法返回值不正确,返回值只能包括" + str);
					log.error(Constants.RETURN_VALUE_FAIL_CODE + ":" + Constants.RETURN_VALUE_FAIL_DESCRIPTION);
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 扫描指定包
	 *
	 * @param Package
	 */
	private void scanPackage(String Package) {
		// 将所有的.转义获取对应的路径
		URL url = this.getClass().getClassLoader().getResource(replaceTo(Package));
		String pathFile = url.getFile();

		File file = new File(pathFile);
		String[] fileList = file.list();
		for (String path : fileList) {
			File eachFile = new File(pathFile + "/" + path);
			// 扫描到目录，继续递归扫描
			if (eachFile.isDirectory()) {
				scanPackage(Package + "." + eachFile.getName());
			} else {
				packageNames.add(Package + "." + eachFile.getName());
			}
		}
	}

	/**
	 * 转义
	 *
	 * @param path
	 * @return
	 */
	private String replaceTo(String path) {
		return path.replaceAll("\\.", "/");
	}

}
