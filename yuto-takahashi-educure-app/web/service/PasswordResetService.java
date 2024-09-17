package jp.co.hoge.web.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    // 例として、メール送信を模擬したメソッドを作成
    public boolean sendResetLink(String email) {
        // 実際の処理では、ユーザーが存在するか確認し、リセットリンクをメールで送信します
        // ここでは単純にtrueを返すことにします（仮実装）
        if (email.equals("valid@example.com")) {
            // リセットリンクの送信処理（仮定）
            return true;
        } else {
            return false;
        }
    }
}
