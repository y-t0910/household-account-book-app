package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExportController {

    @GetMapping("/export")
    public String showExportForm(Model model) {
        // 必要に応じて、フォームに表示する初期データをセット
        return "export"; // export.htmlのビューを表示
    }

    @PostMapping("/export")
    public String exportData(@RequestParam("format") String format, Model model) {
        // データのエクスポート処理を行います
        model.addAttribute("message", "データがエクスポートされました。");
        return "export_confirmation"; // export_confirmation.htmlのビューを表示
    }
}

