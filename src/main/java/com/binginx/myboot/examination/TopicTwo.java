package com.binginx.myboot.examination;

import java.util.*;

public class TopicTwo {
    public static final String BANK_LEVEL_0 = "0"; //总行
    public static final String BANK_LEVEL_1 = "1"; //分行
    public static final String BANK_LEVEL_2 = "2"; //支行
    public static final String BANK_STATUS_0 = "0"; //正常
    public static final String BANK_STATUS_1 = "1"; //撤并
    public static void main(String[] args) {
        int total = 0;
        String revokeBefore = null;
        String revokeAfter = null;
        Map<String,BankInfo> bankInfoMap = new LinkedHashMap<>();
        List<BankInfo> bankInfoList = new ArrayList<>();
        Scanner scanner= new Scanner(System.in);
        while (scanner.hasNext()) {
            String lineString = scanner.nextLine();
            String[] stringArray = lineString.split(" ");
            if(stringArray.length == 1) {
                if(Integer.parseInt(stringArray[0]) == -1) {
                    break;
                } else {
                    total = Integer.parseInt(stringArray[0]);
                }
            }
            if(stringArray.length == 5) {
                BankInfo bankInfo = new BankInfo();
                bankInfo.setBankNo(stringArray[0]);
                bankInfo.setBankLevel(stringArray[1]);
                bankInfo.setParentBankNo(stringArray[2]);
                bankInfo.setBankName(stringArray[3]);
                bankInfo.setStatus(stringArray[4]);
                bankInfoMap.put(stringArray[0],bankInfo);
                bankInfoList.add(bankInfo);
            }
            if(stringArray.length == 2) {
                revokeBefore = stringArray[0];
                revokeAfter = stringArray[1];
            }
        }
        if(bankInfoMap.size() != total) {
            System.out.println("机构数目错误");
        }
        if(null == revokeBefore || null == revokeAfter) {
            System.out.println("撤并机构信息错误");
        }
        BankInfo bankInfo = bankInfoMap.get(revokeBefore);
        if(null != bankInfo) {
            BankInfo bankInfoAfter = bankInfoMap.get(revokeAfter);
            String bankLevel = bankInfo.getBankLevel();
            if(BANK_LEVEL_0.equals(bankLevel)) {
                System.out.println("总行不能被撤并");
            }
            if(BANK_LEVEL_2.equals(bankLevel)) {
                bankInfo.setStatus(BANK_STATUS_1);
                bankInfo.setParentBankNo(revokeAfter);
            }
            if(BANK_LEVEL_1.equals(bankLevel)) {
                bankInfo.setStatus(BANK_STATUS_1);
                for (BankInfo bankInfo1 : bankInfoList) {
                    if(bankInfo1.getParentBankNo().equals(revokeBefore)) {
                        BankInfo bankInfo2 = bankInfoMap.get(bankInfo1.getBankNo());
                        bankInfo2.setParentBankNo(revokeAfter);
                        bankInfo2.setBankName(bankInfoAfter.getBankName().substring(0,3) + bankInfo2.getBankName().substring(3,6));
                    }
                }
            }
            Iterator<Map.Entry<String, BankInfo>> iterator = bankInfoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, BankInfo> entry = iterator.next();
                System.out.println(entry.getValue().toString());
            }
        } else {
            System.out.println("撤并机构信息不存在");
        }
        scanner.close();
    }
}
