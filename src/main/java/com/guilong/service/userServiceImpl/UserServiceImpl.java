package com.guilong.service.userServiceImpl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.guilong.dao.RoleDao;
import com.guilong.dao.UserDao;
import com.guilong.domain.User;
import com.guilong.domain.security.UserRole;
import com.guilong.service.AccountService;
import com.guilong.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
   private static final Logger LOG= LoggerFactory.getLogger(UserService.class);
   
   @Autowired
   private UserDao userDao;
   @Autowired
   private RoleDao roleDao;
   @Autowired
   private BCryptPasswordEncoder passwordEncoder;
   @Autowired
   private AccountService accountService;
   

@Override
public User findByUserName(String username) {
	// TODO Auto-generated method stub
	return userDao.findByUsername(username);
}

@Override
public User findByEmail(String email) {
	// TODO Auto-generated method stub
	return userDao.findByEmail(email);
}

@Override
public boolean checkUserExists(String username, String email) {
	// TODO Auto-generated method stub
	if(checkUsernameExists(username)||checkEmailExists(email)) return true;
	else return false;
}

@Override
public boolean checkUsernameExists(String username) {
	// TODO Auto-generated method stub
	if(findByUserName(username)!=null)
	return true;
	else return false;
}

@Override
public boolean checkEmailExists(String email) {
	if(findByEmail(email)!=null)
	return true;
	return false;
}

@Override
public void save(User user) {
	// TODO Auto-generated method stub
	userDao.save(user);
}

@Override
public User createUser(User user, Set<UserRole> userRoles) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public User saveUser(User user) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<User> findUserList() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void enableUser(String username) {
	// TODO Auto-generated method stub
	
}

@Override
public void disableUser(String username) {
	// TODO Auto-generated method stub
	
}
   
}
