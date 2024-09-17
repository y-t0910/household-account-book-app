package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImportController {

    @GetMapping("/import")
    public String showImportForm() {
        // インポート用のフォームを表示する
        return "import"; // import.html のビューを表示
    }

    @PostMapping("/import")
    public String importData(@RequestParam("file") MultipartFile file, Model model) {
        // データのインポート処理を行います
        model.addAttribute("message", "データがインポートされました。");
        return "import_confirmation"; // import_confirmation.html のビューを表示
    }
}
