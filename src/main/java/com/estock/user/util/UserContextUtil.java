package com.estock.user.util;

import com.estock.user.entity.User;
import com.estock.user.model.UserContext;

public class UserContextUtil {
	
	public static UserContext prepareUserContext(User user, String token) {
		UserContext uc = new UserContext();
		uc.setFirstname(user.getFirstname());
		uc.setLastname(user.getLastname());
		uc.setUserId(user.getUserId());
		uc.setUsername(user.getUsername());
		uc.setToken(token);
		return uc;
	}

}
