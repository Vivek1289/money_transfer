package com.revoult.entity.dto;

/**
 * @author Vivek Grewal
 */
public class TransactionDTO {
    private String accountNumberTo;
    private String amount;

    public TransactionDTO(String accountNumberTo, String amount) {
        this.accountNumberTo = accountNumberTo;
        this.amount = amount;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
