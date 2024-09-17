package jp.co.hoge.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.web.model.Budgets;
import jp.co.hoge.web.repository.BudgetRepository;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budgets findById(Long id) {
        return budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("データが見つかりません"));
    }

    public void update(Long id, Budgets budget) {
        Budgets existingBudget = findById(id);
        existingBudget.setCategoryId(budget.getCategoryId()); 
        existingBudget.setAmount(budget.getAmount()); 
        budgetRepository.save(existingBudget);
    }


    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }
}
