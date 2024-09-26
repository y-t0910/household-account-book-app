import React, { useEffect, useState } from 'react';

const DataSummary = () => {
  const [summary, setSummary] = useState({ transactions: 0, budget: 0, expenses: 0 });

  useEffect(() => {
    // APIからサマリーデータを取得
    fetch('/api/summary')
      .then(response => response.json())
      .then(data => {
        console.log('APIから取得したデータ:', data); // デバッグ用に取得したデータを表示
        setSummary(data);
      })
      .catch(error => console.error('Error:', error));
  }, []);

  return (
    <div>
      <h2>データサマリー</h2>
      <p>取引数: {summary.transactions}</p>
      <p>予算: {summary.budget}円</p>
      <p>支出: {summary.expenses}円</p>
    </div>
  );
};

export default DataSummary;
