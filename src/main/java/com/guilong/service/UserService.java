package com.guilong.service;

import java.util.List;
import java.util.Set;

import com.guilong.domain.User;
import com.guilong.domain.security.UserRole;

public interface UserService {
 User findByUserName(String username);
 User findByEmail(String email);
 boolean checkUserExists(String username, String email);
 boolean checkUsernameExists(String username);
 boolean checkEmailExists(String email);
 void save(User user);
 User createUser(User user, Set<UserRole> userRoles);
 User saveUser(User user);
 List<User> findUserList();
 void enableUser(String username);
 void disableUser(String username);
}
