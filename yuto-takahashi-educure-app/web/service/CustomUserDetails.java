package jp.co.hoge.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.hoge.web.model.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ロールを設定 (今回は全員に "ROLE_USER" を付与しています)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 必要に応じてユーザーのロールをここで追加できます。
        if (user.getIsAdmin()) {  // 管理者であれば "ROLE_ADMIN" を追加
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        // データベースから取得したパスワードを返す
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // データベースから取得したメールアドレスを返す
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // アカウントの有効期限が切れていないか（true: 切れていない）
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // アカウントがロックされていないか（true: ロックされていない）
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 資格情報の有効期限が切れていないか（true: 切れていない）
        return true;
    }

    @Override
    public boolean isEnabled() {
        // アカウントが有効かどうか（true: 有効）
        return true;
    }

    // ユーザーオブジェクトを直接返すためのメソッド
    public User getUser() {
        return this.user;
    }
}
