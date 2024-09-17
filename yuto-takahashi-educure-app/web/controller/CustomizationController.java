package jp.co.hoge.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomizationController {
    private List<String> categories = new ArrayList<>(); // 仮のカテゴリリスト

    // カテゴリ追加
    @PostMapping("/customization/save")
    public String saveCategory(@RequestParam("category") String category, Model model) {
        categories.add(category);
        model.addAttribute("categories", categories);
        model.addAttribute("message", "カテゴリが追加されました。");
        return "customization";
    }

    // カスタマイズページの表示
    @GetMapping("/customization")
    public String showCustomizationPage(Model model) {
        model.addAttribute("categories", categories);
        return "customization";
    }

    // カテゴリ編集
    @PostMapping("/customization/edit")
    public String editCategory(@RequestParam("oldCategory") String oldCategory, @RequestParam("newCategory") String newCategory, Model model) {
        int index = categories.indexOf(oldCategory);
        if (index != -1) {
            categories.set(index, newCategory);
            model.addAttribute("message", oldCategory + " が " + newCategory + " に編集されました。");
        } else {
            model.addAttribute("message", oldCategory + " が見つかりませんでした。");
        }
        model.addAttribute("categories", categories);
        return "customization";
    }

    // カテゴリ削除
    @PostMapping("/customization/delete")
    public String deleteCategory(@RequestParam("category") String category, Model model) {
        categories.remove(category);
        model.addAttribute("categories", categories);
        model.addAttribute("message", "カテゴリ " + category + " が削除されました。");
        return "customization";
    }
}
