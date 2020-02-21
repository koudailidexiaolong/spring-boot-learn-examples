package com.julong.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller类
 * @author julong
 * @date 2020年1月16日 下午5:41:11
 * @desc 
 */
@Controller
public class HelloAction {

	/**
	 * 测试方法
	 * @return
	 * @author julong
	 * @date 2020年1月16日 下午5:41:28
	 * @desc 访问方法 http://localhost:8080/ 就进入此方法了
	 */
	@RequestMapping("/")
	@ResponseBody
	public String sayHello(){

		return "Hello springboot";
	}
}
