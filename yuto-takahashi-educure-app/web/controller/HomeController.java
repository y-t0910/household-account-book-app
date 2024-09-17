package jp.co.hoge.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home") 
    public String showHomePage(Model model) {
        // ダミーデータを用意します（実際にはサービスから取得する）
        List<String> transactions = Arrays.asList("取引1: 1000円", "取引2: 500円", "取引3: 1500円");
        String budgetStatus = "予算の50%を消費しています。";

        // モデルにデータを追加します
        model.addAttribute("transactions", transactions);
        model.addAttribute("budgetStatus", budgetStatus);

        return "home"; // home.htmlをレンダリングします
    }
}

