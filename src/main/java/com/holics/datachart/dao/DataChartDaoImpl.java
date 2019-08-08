package com.holics.datachart.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.holics.datachart.service.entity.AuthenticationRequest;
@Repository
public class DataChartDaoImpl implements DataChartDao {

	SessionFactory sessionFactory;
	
	@Autowired
	  public DataChartDaoImpl(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }

	@Override
	public User authenticate(AuthenticationRequest authenticationRequest) {
		Criteria criteria = this.sessionFactory.openSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", authenticationRequest.getUserName()));
		criteria.add(Restrictions.eq("password", authenticationRequest.getPassword()));
		return (User) criteria.uniqueResult();
	}

	@Override
	public boolean checkUserAlreadyExists(User user) {
		boolean isUserExists = true;
		Criteria criteria = this.sessionFactory.openSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", user.getUserName()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		User existingUser = (User) criteria.uniqueResult();
		if (existingUser != null) {
			isUserExists = false;
		}
		return isUserExists;

	}

	@Override
	public User register(User user) {
		this.sessionFactory.openSession().save(user);
		return user;
	}

	@Override
	public List<HashMap<String, Object>> getUserData(String userId) {

		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> dataGrap1 = new HashMap<>();

		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");

		dataGrap1.put("graph1", jo);
		dataList.add(dataGrap1);
		return dataList;
	}
	



}
