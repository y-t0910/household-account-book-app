package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String showSearchForm() {
        // 検索フォームを表示するためのメソッド
        return "search"; // search.html のビューを表示
    }

    @PostMapping("/search")
    public String searchData(@RequestParam("keyword") String keyword, Model model) {
        // POSTリクエストによる検索処理を行い、結果をmodelに追加
        model.addAttribute("results", "検索結果をここに表示します");
        return "search_results"; // search_results.html のビューを表示
    }
}
