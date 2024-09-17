package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InputController {

    // /input エンドポイントを処理するメソッド
    @GetMapping("/input")
    public String showInputForm() {
        return "input"; // input.htmlを返す
    }

    @PostMapping("/saveEntry")
    public String saveEntry(@RequestParam("type") String type, 
                            @RequestParam("category") String category,
                            @RequestParam("amount") int amount,
                            @RequestParam("date") String date,
                            @RequestParam("note") String note,
                            Model model) {
        // ここで入力データの保存処理を行います
        model.addAttribute("message", "入力データが保存されました。");
        return "input_confirmation"; // 保存確認画面に遷移します
    }
}
