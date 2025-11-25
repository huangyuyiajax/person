package com.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.model.*;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @description: TODO
 * @author huangyuyi
 * @date 2023/4/21 10:56
 * @version 1.0
 */
 
@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/admin"})
@Slf4j
public class AdminActionController {

	@Autowired
	private UserService userService;


	@PostMapping({"/selectUserList"})
	public JSONObject selectUserList() {
		JSONObject res = new JSONObject();
		try {
			List<User> list = userService.selectUserList();
			res.put("data",list);
			res.put("code",0);
			res.put("message","成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("code",-1);
			res.put("message",e.getMessage());
		}
		return res;
	}

	@PostMapping({"/saveBill"})
	public JSONObject saveBill(User user) {
		JSONObject res = new JSONObject();
		try {
			userService.saveBill(user);
			res.put("code",0);
			res.put("message","成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("code",-1);
			res.put("message",e.getMessage());
		}
		return res;
	}


	@PostMapping({"/saveUser"})
	public JSONObject saveUser(User user) {
		JSONObject res = new JSONObject();
		try {
			userService.saveUser(user);
			res.put("code",0);
			res.put("message","成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("code",-1);
			res.put("message",e.getMessage());
		}
		return res;
	}

	@PostMapping({"/saveLottery"})
	public JSONObject saveLottery(Lottery lottery) {
		JSONObject res = new JSONObject();
		try {
			userService.saveLottery(lottery);
			res.put("code",0);
			res.put("message","成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("code",-1);
			res.put("message",e.getMessage());
		}
		return res;
	}
	@PostMapping({"/selectReport"})
	public JSONObject selectReport() {
		JSONObject res = new JSONObject();
		try {
			List<Report> list = userService.selectReport();
			res.put("data",list);
			res.put("code",0);
			res.put("message","成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put("code",-1);
			res.put("message",e.getMessage());
		}
		return res;
	}


}
