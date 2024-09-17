package jp.co.hoge.web.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.hoge.web.service.DataSummaryService;

@SpringBootTest
@AutoConfigureMockMvc
public class DataSummaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSummaryService dataSummaryService;

    @Test
    public void testGetSummary() throws Exception {
        // ダミーデータを準備
        Map<String, Object> summary = new HashMap<>();
        summary.put("transactions", 3);
        summary.put("budget", 1500.0);
        summary.put("expenses", 500.0);

        // サービスメソッドのモックを設定
        when(dataSummaryService.getSummary(1L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)))
                .thenReturn(summary);

        // エンドポイントをテスト
        mockMvc.perform(get("/api/summary")
                .param("userId", "1")
                .param("start", "2023-01-01")
                .param("end", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactions").value(3))
                .andExpect(jsonPath("$.budget").value(1500.0))
                .andExpect(jsonPath("$.expenses").value(500.0));
    }
}
