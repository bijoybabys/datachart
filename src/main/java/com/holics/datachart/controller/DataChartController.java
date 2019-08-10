package com.holics.datachart.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holics.datachart.dao.User;
import com.holics.datachart.service.DataChartService;
import com.holics.datachart.service.entity.AuthenticationRequest;
import com.holics.datachart.service.entity.ResponseData;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/datachart")
public class DataChartController {

	@Autowired
	DataChartService datachartService;

	@GetMapping("/healthcheck")
	public ResponseEntity<ResponseData> healthCheck() {
		
		ResponseData response = new ResponseData();
		response.put("message", "success");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseData> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		ResponseData response = new ResponseData();
		User user = datachartService.authenticate(authenticationRequest);
		if (user != null) {
			response.put("userId", user.getUserId());
			response.put("userName", user.getUserName());
			response.put("message", "success");
		} else {
			response.put("message", "failed");
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
	public ResponseEntity<ResponseData>  getUserData(@PathVariable("userId") String userId) {
		ResponseData response = new ResponseData();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = datachartService.getUserData(Integer.parseInt(userId));
		Map<String, Object> data = null;
		try {
			data = objectMapper.readValue(jsonData, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		response.put("data", data);
		return ResponseEntity.ok(response);


	}

}
