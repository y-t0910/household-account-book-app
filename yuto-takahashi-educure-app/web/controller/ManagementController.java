package jp.co.hoge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.hoge.web.model.Budgets;
import jp.co.hoge.web.service.BudgetService;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private BudgetService budgetService;

    // 管理画面の表示
    @GetMapping
    public String showManagementPage(Model model) {
        // 必要なデータがあればここに追加
        return "management";
    }

    // 編集・削除ページの表示
    @GetMapping("/edit-delete/{id}")
    public String editAndDeleteBudget(@PathVariable Long id, Model model) {
        Budgets budget = budgetService.findById(id);
        model.addAttribute("budget", budget);  // データをビューに渡す
        return "edit-delete";  // edit-delete.htmlを返す
    }

}



