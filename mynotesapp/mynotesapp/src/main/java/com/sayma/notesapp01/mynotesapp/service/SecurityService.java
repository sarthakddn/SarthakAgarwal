package com.sayma.notesapp01.mynotesapp.service;

public interface SecurityService {
	public String findLoggedInUsername();

    public void autoLogin(String username, String password);
}
