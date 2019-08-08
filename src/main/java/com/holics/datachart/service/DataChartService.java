package com.holics.datachart.service;

import java.util.HashMap;
import java.util.List;

import com.holics.datachart.dao.User;
import com.holics.datachart.service.entity.AuthenticationRequest;

public interface DataChartService {

	User authenticate(AuthenticationRequest authenticationRequest);

	boolean checkUserAlreadyExists(User user);

	User register(User user);

	List<HashMap<String, Object>> getUserData(String userId);

}
