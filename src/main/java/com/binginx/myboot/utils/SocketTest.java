package com.binginx.myboot.utils;

import cn.hutool.core.util.StrUtil;

import java.io.IOException;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        String xml = "12345678<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xml += "<transaction>                                                 ";
        xml += "	<header>                                                  ";
        xml += "		<ver>1.0</ver>                                        ";
        xml += "		<msg>                                                 ";
        xml += "			<sndAppCd>IODS</sndAppCd>                         ";
        xml += "			<sndDt>20200528</sndDt>                           ";
        xml += "			<sndTm>211954470</sndTm>                          ";
        xml += "			<seqNb>IODS1907312119544709</seqNb>               ";
        xml += "			<msgCd>EWOA.00000100.01</msgCd>                   ";
        xml += "			<callTyp>SYN</callTyp>                            ";
        xml += "			<replyToQ>REP.TCP.GWIN</replyToQ>                 ";
        xml += "			<srcAppCd>IODS</srcAppCd>                         ";
        xml += "			<srcNb>GIODS201907312119544709</srcNb>            ";
        xml += "			<rcvAppCd>BOFS</rcvAppCd>                         ";
        xml += "		</msg>                                                ";
        xml += "	</header>                                                 ";
        xml += "	<body>                                                    ";
        xml += "		<request>                                             ";
        xml += "			<ReqBaseHdr>                                      ";
        xml += "				<ChnlCD>999999</ChnlCD>                       ";
        xml += "				<InsNo>0001</InsNo>                           ";
        xml += "				<TlrNo>0777</TlrNo>                           ";
        xml += "				<BrNo>000</BrNo>                              ";
        xml += "				<LglPsnID>001</LglPsnID>                      ";
        xml += "			</ReqBaseHdr>                                     ";
        xml += "<Info>";
        xml += "<CustNo>C888890538</CustNo>";
        xml += "<OprNo>0001</OprNo>";
        xml += "<AcctNo>414386193600015</AcctNo>";
        xml += "<AccNm>河北鼎帝商贸有限公司</AccNm>";
        xml += "<TradeTm>20200429 10:10:58</TradeTm>";
        xml += "<Amount>200.00</Amount>";
        xml += "<TradeType>转入</TradeType>";
        xml += "</Info>";
        xml += "		</request>                                             ";
        xml += "	</body>                                                    ";
        xml += "</transaction>                                                 ";

//        for (int i = 0; i < 100000; i++) {
//            SocketUtil.sendMsg(xml,"192.168.1.130",9999);
//        }
        System.out.println(StrUtil.cleanBlank(xml));
    }
}
