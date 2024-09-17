package jp.co.hoge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.hoge.web.service.PasswordResetService; 

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/Password_Reset")
    public String showResetPasswordForm() {
        return "Password_Reset"; // Password_Reset.htmlを表示
    }

    @PostMapping("/Password_Reset")
    public String resetPassword(@RequestParam("email") String email, Model model) {
        boolean isEmailSent = passwordResetService.sendResetLink(email);

        if (isEmailSent) {
            model.addAttribute("message", "リセットリンクがメールで送信されました。");
            return "reset_password_confirmation";
        } else {
            model.addAttribute("error", "メールアドレスが見つかりません。");
            return "password_reset";
        }
    }
}
