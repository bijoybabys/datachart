package com.holics.datachart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holics.datachart.dao.User;
import com.holics.datachart.service.DataChartService;
import com.holics.datachart.service.entity.AuthenticationRequest;
import com.holics.datachart.service.entity.ResponseData;

@RestController
@RequestMapping(value = "api/datachart")
public class DataChartController {

	@Autowired
	DataChartService datachartService;

	@PostMapping("/login")
	public ResponseEntity<ResponseData> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		ResponseData response = new ResponseData();
		User user = datachartService.authenticate(authenticationRequest);
		if (user != null) {
			response.put("userId", user.getUserId());
			response.put("message", "Login successfull");
		} else {
			response.put("message", "Login failed");
		}
		return ResponseEntity.ok(response);

	}

	@PostMapping("/register")
	public ResponseEntity<ResponseData> register(@RequestBody User user) {
		ResponseData response = new ResponseData();
		boolean isUserAlreadyExists = datachartService.checkUserAlreadyExists(user);
		if (isUserAlreadyExists) {
			response.put("message", "user already exists");

		} else {
			user = datachartService.register(user);
		}
		return null;

	}

	@GetMapping("/{userId}")
	public List<HashMap<String, Object>> getUserData(@PathParam("userId") String userId) {
		List<HashMap<String, Object>> dataList = new ArrayList<>();
		dataList = datachartService.getUserData(userId);
		return dataList;

	}

}
