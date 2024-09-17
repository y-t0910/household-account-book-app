package jp.co.hoge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.hoge.web.model.User;
import jp.co.hoge.web.service.UserService;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    // 新規登録ページの表示
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());  // 空のUserオブジェクトを追加
        return "register";  // register.htmlを表示
    }

    // アカウント修正ページ表示
    @GetMapping("/edit_account")
    public String showAccountEditPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // 認証されたユーザー名を取得
        User user = userService.findByName(name); // サービスからユーザー情報を取得
        if (user != null) {
            model.addAttribute("user", user); // ユーザー情報をテンプレートに渡す
        } else {
            // ユーザーが見つからなかった場合のエラーハンドリング
            return "redirect:/error"; // 例えばエラーページへリダイレクト
        }
        return "edit_account"; // edit_account.htmlを返す
    }


    // 新規登録処理
    @PostMapping("/register")
    public String registerAccount(@ModelAttribute User user) {
        userService.createUser(user);  // createUserメソッドで登録処理
        return "redirect:/login?registered=true";
    }

    // アカウント修正処理
    @PostMapping("/edit_account")
    public String updateAccount(@ModelAttribute User user) {
        userService.updateUser(user);  // updateUserメソッドで更新処理
        return "/edit_account";
    }

    // アカウント退会ページの表示
    @GetMapping("/deleteAccount")
    public String showDeleteAccountPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        return "deleteAccount";  // 退会確認ページにリダイレクト
    }

    // アカウント退会処理
    @PostMapping("/deleteAccount")
    public String deleteAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        userService.deleteUserByName(name);  // ユーザーを削除
        SecurityContextHolder.clearContext();  // セッションをクリアしてログアウト
        return "redirect:/login?accountDeleted=true";  // 退会後にログインページへリダイレクト
    }
}
