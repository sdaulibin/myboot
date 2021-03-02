package com.binginx.myboot.examination;

public class DepositReceipt {
    private int number;
    private double accountBalance;
    private double receiptBalance;
    private double interest;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getReceiptBalance() {
        return receiptBalance;
    }

    public void setReceiptBalance(double receiptBalance) {
        this.receiptBalance = receiptBalance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
