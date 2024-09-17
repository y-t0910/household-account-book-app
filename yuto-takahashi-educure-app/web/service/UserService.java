package jp.co.hoge.web.service;

import jp.co.hoge.web.model.User;

public interface UserService {
    
    User findByName(String name);

    void deleteUserByName(String name);

    void createUser(User user);

    void updateUser(User user);
}
