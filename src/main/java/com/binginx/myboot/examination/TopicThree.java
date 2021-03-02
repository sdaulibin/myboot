package com.binginx.myboot.examination;

import java.util.ArrayList;
import java.util.List;

public class TopicThree {
    public static void main(String[] args) {
        double total = 0;
        List<DepositReceipt> depositReceiptList = new ArrayList<>();
        for (int i=1;i<=depositReceiptList.size();i++) {
            DepositReceipt depositReceiptI = depositReceiptList.get(i);
            if(i==1) {
                depositReceiptI.setReceiptBalance(depositReceiptI.getAccountBalance());
                depositReceiptI.setInterest(computeInt(depositReceiptI.getAccountBalance(),i));
            }
            if(i>1) {
                double receiptBalance = depositReceiptI.getAccountBalance() - depositReceiptList.get(i-1).getAccountBalance();
                if (receiptBalance >= 0) {
                    depositReceiptI.setReceiptBalance(receiptBalance);
                    depositReceiptI.setInterest(computeInt(receiptBalance,i));
                    for (int j = i-1; j >=1 ; j--) {
                        DepositReceipt depositReceiptJ = depositReceiptList.get(j);
                        if((i-j)>=7) {
                            depositReceiptJ.setReceiptBalance(0);
                        }
                        depositReceiptJ.setInterest(computeInt(depositReceiptJ.getAccountBalance(),i));
                    }
                }
                if (receiptBalance < 0) {
                    depositReceiptI.setReceiptBalance(0);
                    depositReceiptI.setInterest(0);
                    double accountBalance = depositReceiptI.getAccountBalance();
                    for (int j = i-1; j >=1 ; j--) {
                        DepositReceipt depositReceiptJ = depositReceiptList.get(j);
                        if(accountBalance >= depositReceiptJ.getReceiptBalance()) {
                            depositReceiptJ.setReceiptBalance(0);
                            //
                        }
                    }
                }
            }
        }
    }
    public static double computeInt(double amt,int n){
        if(n>=7) {
            return amt*0.02*7/360;
        }
        if(n>0 && n<7) {
            return amt*0.01*n/360;
        }
        return 0;
    }
}
