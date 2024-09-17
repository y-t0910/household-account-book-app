package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BudgetSettingsController {

    @GetMapping("/Budget_settings")
    public String showBudgetSettingsForm() {
        return "Budget_settings"; // フォームのページ名を返す
    }

    @PostMapping("/Budget_settings")
    public String saveBudgetSettings(@RequestParam("budget") int budget,
                                     @RequestParam("alert") int alert,
                                     Model model) {
        // 予算設定とアラートの保存処理を行います
        model.addAttribute("message", "予算設定が保存されました。");
        return "budget_settings_confirmation"; // 確認ページに遷移
    }
}
