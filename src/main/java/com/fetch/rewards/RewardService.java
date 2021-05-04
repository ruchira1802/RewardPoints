package com.fetch.rewards;


import java.util.List;
import java.util.Map;

/**
 * Common interface for the RewardService
 */
public interface RewardService {

    /**
     *  Add a transaction
     * @param t
     */
    void addTransaction(Transaction t);

    /**
     * Add list of transactions
     * @param transactions
     */
    void addTransactions(List<Transaction> transactions);

    /**
     * Spend points and get points spent by each payer
     * @param t
     * @return
     */
    Map<String,Integer> spendPoints(Transaction t);

    /**
     * Get balance points from each payer
     * @return
     */
    Map<String,Integer> getBalance();



}
