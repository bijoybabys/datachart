package com.holics.datachart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holics.datachart.dao.DataChartDao;
import com.holics.datachart.dao.User;
import com.holics.datachart.service.entity.AuthenticationRequest;

@Service
public class DataChartServiceImpl implements DataChartService {

	@Autowired
	DataChartDao dataChartDao;

	@Override
	public User authenticate(AuthenticationRequest authenticationRequest) {
		User user = dataChartDao.authenticate(authenticationRequest);
		return user;
	}

	@Override
	public boolean checkUserAlreadyExists(User user) {
		return dataChartDao.checkUserAlreadyExists(user);
	}

	@Override
	public User register(User user) {
		return dataChartDao.register(user);
	}

	@Override
	public String getUserData(int userId) {
		return dataChartDao.getUserData(userId);

	}

}
