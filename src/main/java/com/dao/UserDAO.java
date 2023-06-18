package com.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.entities.User;

public interface UserDAO extends Idao<User>{
	List<User> getByExample(User u, MatchMode mode, boolean detached);
}
