package com.holics.datachart.dao;

import com.holics.datachart.service.entity.AuthenticationRequest;

public interface DataChartDao {

	User authenticate(AuthenticationRequest authenticationRequest);

	boolean checkUserAlreadyExists(User user);

	User register(User user);

	String getUserData(int userId);

}
