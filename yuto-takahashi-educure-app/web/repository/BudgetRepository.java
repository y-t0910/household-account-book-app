package jp.co.hoge.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.hoge.web.model.Budgets;

@Repository
public interface BudgetRepository extends JpaRepository<Budgets, Long> {
}

