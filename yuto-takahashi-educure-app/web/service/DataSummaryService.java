package jp.co.hoge.web.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.web.model.Transaction;
import jp.co.hoge.web.repository.TransactionRepository;

@Service
public class DataSummaryService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, Object> getSummary(Long userId, LocalDate startDate, LocalDate endDate) {
        // データベースから該当ユーザーのトランザクションを取得
        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);

        double totalIncome = 0;
        double totalExpenses = 0;
        int transactionCount = transactions.size();

        // トランザクションをループして収入と支出を計算
        for (Transaction transaction : transactions) {
            if ("income".equals(transaction.getType())) {  // "income"のトランザクション
                totalIncome += transaction.getAmount();
            } else {  // それ以外は"expense"と仮定
                totalExpenses += transaction.getAmount();
            }
        }

        // 結果をマップに格納
        Map<String, Object> summary = new HashMap<>();
        summary.put("transactions", transactionCount);  // 取引数
        summary.put("budget", totalIncome);  // 収入
        summary.put("expenses", totalExpenses);  // 支出

        return summary;
    }
}
