package com.agileproject.expense_tracker;

import com.agileproject.expense_tracker.bll.TransactionBLL;
import com.agileproject.expense_tracker.models.Transaction;
import com.agileproject.expense_tracker.response.TransactionResponse;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TransactionUnitTest {

    private TransactionBLL transactionBLL;

    @Before
    public void setup() {
        transactionBLL = new TransactionBLL();
    }

    @Test
    public void testA_emptyIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income note", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c");
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testB_negativeIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income note", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", -100);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testC_zeroIncAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income note", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 0);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testD_validIncAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income note", "Income", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 100);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertEquals(100.0, transactionResponse.getTransaction().getAmount());
    }

    @Test
    public void testE_emptyExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense note", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c");
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testF_negativeExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense note", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", -100);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testG_zeroExpAmount_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense note", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 0);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    @Test
    public void testH_validExpAmount_shouldAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Expense note", "Expense", "5d879975de6f522844aa111e", "2019-09-25", "5d90c4828fad49338c15706c", 100);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertEquals(100.0, transactionResponse.getTransaction().getAmount());
    }



    @Test
    public void testI_emptyCategory_shouldNotAddANewTransaction() {
        Transaction newTransaction = new Transaction("Test Income note", "Income", "5d879975de6f522844aa111e", "2019-09-25", "", 100);
        TransactionResponse transactionResponse = transactionBLL.addNewTransaction(newTransaction);
        assertNull(transactionResponse);
    }

    //test to get my transaction list
    @Test
    public void testJ_transactionSize() {
        TransactionResponse transactionResponse = transactionBLL.getTransactions("5d879975de6f522844aa111e");
        assertEquals(2, transactionResponse.getMyTransactions().size());
    }


}
