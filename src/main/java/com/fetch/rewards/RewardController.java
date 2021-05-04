package com.fetch.rewards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Controller for REST APIs for the Reward Service
 */
@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     * POST - API to add a transaction
     * @param transaction
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/addTransaction")
    public ResponseEntity<ResponseObject> addTransaction(@RequestBody Transaction transaction) {
        rewardService.addTransaction(transaction);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage("added");
        return  ResponseEntity.ok(responseObject);
    }

    /**
     *  POST - API to add list of transactions
     * @param transactions
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/addTransactions")
    public ResponseEntity<ResponseObject> addTransactions(@RequestBody List<Transaction> transactions) {
        rewardService.addTransactions(transactions);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage("added");
        return  ResponseEntity.ok(responseObject);
    }

    /**
     * POST - API to get points spent from each payer for
     * @param transaction
     * @return
     */
    @PostMapping(value = "/spendPoints")
    public ResponseEntity<Map<String,Integer>> spendPoints(@RequestBody Transaction transaction) {
        Map<String,Integer> map = rewardService.spendPoints(transaction);
        return ResponseEntity.ok(map);
    }

    /**
     * GET - API to get balance of points for each payer
     * @param transaction
     * @return
     */
    @GetMapping(value="/getBalance")
    public ResponseEntity<Map<String,Integer>> getBalance(@RequestBody Transaction transaction) {
        Map<String,Integer> map = rewardService.getBalance();
        return ResponseEntity.ok(map);
    }

}
