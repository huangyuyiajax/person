package com.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @description: TODO
 * @author huangyuyi
 * @date 2023/4/21 10:49
 * @version 1.0
 */

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@Controller
@RequestMapping({"/app"})
@Slf4j
public class AppController {

	@RequestMapping({"/index"})
	public String index() {
		return "app/index.html";
	}


}
