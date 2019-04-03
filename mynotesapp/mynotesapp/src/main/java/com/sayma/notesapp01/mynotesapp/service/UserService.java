package com.sayma.notesapp01.mynotesapp.service;

import com.sayma.notesapp01.mynotesapp.entity.User;

public interface UserService {

	void save(User user);

    User findByUsername(String username);
}
	

