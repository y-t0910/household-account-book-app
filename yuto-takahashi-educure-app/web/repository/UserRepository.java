package jp.co.hoge.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.hoge.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // ユーザー名で検索するメソッド
    User findByName(String name);

    // メールアドレスで検索するメソッド
    User findByEmail(String email);

    // ユーザー名でユーザーを削除するメソッド
    void deleteByName(String name);
}
