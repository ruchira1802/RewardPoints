package com.fetch.rewards;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class RewardServiceImpl implements RewardService {

    /**
     * transactionsMap = stores the transaction for a time, sorted in the order of time
     * totalPoints = stores the totalPoints present for a user
     * balance = stores the  overall balance of points by each and every payer to a user
     */
    private static TreeMap<Instant,Transaction> transactionsMap;
    private static Integer totalPoints;
    private static Map<String, Integer> balance;

    static {
        totalPoints = 0;
        balance = new HashMap<>();
        transactionsMap = new TreeMap<>();
    }

    /**
     * service to add a transaction
     * @param t
     */
    @Override
    public void addTransaction(Transaction t) {
        totalPoints = totalPoints + t.getPoints();
        transactionsMap.put(Instant.parse(t.getTimestamp()),t);
    }

    /**
     * service to add list of transaction
     * @param transactions
     */
    @Override
    public void addTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            totalPoints = totalPoints + t.getPoints();
            balance.put(t.getPayer(), balance.getOrDefault(t.getPayer(), 0) + t.getPoints());
            transactionsMap.put(Instant.parse(t.getTimestamp()),t);
        }
    }

    /**
     * Service to calculate the points spent from each payer
     * @param t
     * @return Map<String, Integer> pointsSpend
     */
   @Override
    public Map<String, Integer> spendPoints(Transaction t) {
        /**
         * This map stores the total points spent from each payer
         */
        Map<String, Integer> spentTransaction = new HashMap<>();
        /**
         * checking if the total points to spend is in the limits or not
         */
        if (t.getPoints() > totalPoints) {
            return spentTransaction;
        }

        //total points to spend
        int max = t.getPoints();

        for(Map.Entry<Instant,Transaction> transactionEntry:transactionsMap.entrySet()) {
            if(max<=0) {
                break;
            }
            Transaction currentTransaction = transactionEntry.getValue();
            /**user can spend at max the total points in the current transaction or the points (if it is less than the
             points in the current points) that are left to spent.
             For example: if in the current transaction the user received 500 points, but the points that are left to spend is 200,
             then at max user spend 200 points received from that payer
             **/
            int moneySpent = Math.min(currentTransaction.getPoints(), max);
            max = max - moneySpent;

            //updating points spent for the payer
            spentTransaction.put(currentTransaction.getPayer(), spentTransaction.getOrDefault(currentTransaction.getPayer(), 0) - moneySpent);

            //updating balance for the payer
            balance.put(currentTransaction.getPayer(), balance.get(currentTransaction.getPayer()) - moneySpent);
            //updating the points for the current transaction
            if(currentTransaction.getPoints()>0) {
                currentTransaction.setPoints(currentTransaction.getPoints()-moneySpent);
                totalPoints = totalPoints-moneySpent;
            } else {
                currentTransaction.setPoints(0);
            }
            transactionsMap.put(Instant.parse(currentTransaction.getTimestamp()),currentTransaction);
        }
        System.out.println(totalPoints);
        return spentTransaction;
    }

    /**
     * service to return the balance of each payer
     * @return Map<String, Integer> balance
     */
    @Override
    public Map<String, Integer> getBalance() {
        return balance;
    }
}
