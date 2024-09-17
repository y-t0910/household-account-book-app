package jp.co.hoge.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.hoge.web.model.User;
import jp.co.hoge.web.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;  

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    public void createUser(User user) {
        // パスワードをハッシュ化してから保存
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        // パスワードをハッシュ化してから保存
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
        }
        return new CustomUserDetails(user);  // カスタムUserDetailsを返す
    }
}
