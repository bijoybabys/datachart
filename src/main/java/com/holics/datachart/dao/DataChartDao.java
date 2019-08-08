package com.holics.datachart.dao;

import java.util.HashMap;
import java.util.List;

import com.holics.datachart.service.entity.AuthenticationRequest;

public interface DataChartDao {

	User authenticate(AuthenticationRequest authenticationRequest);

	boolean checkUserAlreadyExists(User user);

	User register(User user);

	List<HashMap<String, Object>> getUserData(String userId);

}
