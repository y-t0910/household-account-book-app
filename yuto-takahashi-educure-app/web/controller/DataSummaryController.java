package jp.co.hoge.web.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.hoge.web.service.DataSummaryService;

@RestController
@RequestMapping("/api/summary")
public class DataSummaryController {

    @Autowired
    private DataSummaryService dataSummaryService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getSummaryData(@RequestParam Long userId, 
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, 
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Map<String, Object> summary = dataSummaryService.getSummary(userId, start, end);
        return ResponseEntity.ok(summary);
    }
}