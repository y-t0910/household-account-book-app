package jp.co.hoge.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.co.hoge.web.model.Transaction;
import jp.co.hoge.web.repository.TransactionRepository;

public class DataSummaryServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DataSummaryService dataSummaryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // モックを初期化
    }

    @Test
    public void testGetSummary() {
        // テスト用のダミーデータを作成
        Transaction transaction1 = new Transaction();
        transaction1.setType("income");
        transaction1.setAmount(1000);

        Transaction transaction2 = new Transaction();
        transaction2.setType("expense");
        transaction2.setAmount(500);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        // リポジトリのモックを設定
        when(transactionRepository.findByUserIdAndDateBetween(1L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)))
                .thenReturn(transactions);

        // テスト対象のメソッドを呼び出し
        var summary = dataSummaryService.getSummary(1L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));

        // アサーションで結果を確認
        assertEquals(2, summary.get("transactions"));
        assertEquals(1000.0, summary.get("budget"));
        assertEquals(500.0, summary.get("expenses"));
    }
}
