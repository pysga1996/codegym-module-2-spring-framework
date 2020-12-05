package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchController {

    private static final Map<String, String> dictionary;

    static {
        dictionary = new HashMap<>();
        dictionary.put("hello", "Xin chào");
        dictionary.put("how", "Thế nào");
        dictionary.put("book", "Quyển sách");
        dictionary.put("computer", "Máy tính");
    }

    @GetMapping("/")
    public String showSearchBox() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String word, Model model) {
        String meaning = dictionary.get(word);
        model.addAttribute("word", word);
        model.addAttribute("meaning", meaning);
        return "index";
    }
}
