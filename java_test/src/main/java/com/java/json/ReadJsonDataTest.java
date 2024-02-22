package com.java.json;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class ReadJsonDataTest {

	public static void main(String[] args) {
		String jsonStr = "{\r\n"
				+ "	\"accountDetailForm\": {\r\n"
				+ "		\"accLogCertPage\": \"\",\r\n"
				+ "		\"accountType\": \"\",\r\n"
				+ "		\"billUserId\": \"2088502136886997\",\r\n"
				+ "		\"bizTypeList\": [],\r\n"
				+ "		\"endAmount\": \"\",\r\n"
				+ "		\"endDateInput\": \"2021-07-20 16:24:55\",\r\n"
				+ "		\"forceAync\": \"\",\r\n"
				+ "		\"goodsTitle\": \"\",\r\n"
				+ "		\"pageNum\": \"1\",\r\n"
				+ "		\"pageSize\": \"20\",\r\n"
				+ "		\"precisionQueryKey\": \"tradeNo\",\r\n"
				+ "		\"precisionQueryValue\": \"\",\r\n"
				+ "		\"queryEntrance\": \"1\",\r\n"
				+ "		\"reqUserId\": \"\",\r\n"
				+ "		\"searchType\": \"\",\r\n"
				+ "		\"searchableCardListJson\": \"\",\r\n"
				+ "		\"securityBizType\": \"\",\r\n"
				+ "		\"securityId\": \"\",\r\n"
				+ "		\"shopId\": \"\",\r\n"
				+ "		\"showType\": \"1\",\r\n"
				+ "		\"sortTarget\": \"tradeTime\",\r\n"
				+ "		\"sortType\": \"0\",\r\n"
				+ "		\"startAmount\": \"\",\r\n"
				+ "		\"startDateInput\": \"2021-07-19 16:24:55\",\r\n"
				+ "		\"targetMainAccount\": \"\",\r\n"
				+ "		\"type\": \"\",\r\n"
				+ "		\"ua\": \"\"\r\n"
				+ "	},\r\n"
				+ "	\"queryForm\": {\r\n"
				+ "		\"accLogCertPage\": \"\",\r\n"
				+ "		\"accountType\": \"\",\r\n"
				+ "		\"billUserId\": \"2088502136886997\",\r\n"
				+ "		\"bizTypeList\": [],\r\n"
				+ "		\"endAmount\": \"\",\r\n"
				+ "		\"endDateInput\": \"2021-07-20 16:24:55\",\r\n"
				+ "		\"forceAync\": \"\",\r\n"
				+ "		\"goodsTitle\": \"\",\r\n"
				+ "		\"pageNum\": \"1\",\r\n"
				+ "		\"pageSize\": \"20\",\r\n"
				+ "		\"precisionQueryKey\": \"tradeNo\",\r\n"
				+ "		\"precisionQueryValue\": \"\",\r\n"
				+ "		\"queryEntrance\": \"1\",\r\n"
				+ "		\"reqUserId\": \"\",\r\n"
				+ "		\"searchType\": \"\",\r\n"
				+ "		\"searchableCardListJson\": \"\",\r\n"
				+ "		\"securityBizType\": \"\",\r\n"
				+ "		\"securityId\": \"\",\r\n"
				+ "		\"shopId\": \"\",\r\n"
				+ "		\"showType\": \"1\",\r\n"
				+ "		\"sortTarget\": \"tradeTime\",\r\n"
				+ "		\"sortType\": \"0\",\r\n"
				+ "		\"startAmount\": \"\",\r\n"
				+ "		\"startDateInput\": \"2021-07-19 16:24:55\",\r\n"
				+ "		\"targetMainAccount\": \"\",\r\n"
				+ "		\"type\": \"\",\r\n"
				+ "		\"ua\": \"\"\r\n"
				+ "	},\r\n"
				+ "	\"status\": \"succeed\",\r\n"
				+ "	\"success\": \"true\",\r\n"
				+ "	\"result\": {\r\n"
				+ "		\"summary\": {\r\n"
				+ "			\"showSummary\": true,\r\n"
				+ "			\"expendSum\": {\r\n"
				+ "				\"amount\": \"0.00\",\r\n"
				+ "				\"count\": 0\r\n"
				+ "			},\r\n"
				+ "			\"showBegin\": false,\r\n"
				+ "			\"incomeSum\": {\r\n"
				+ "				\"amount\": \"0.60\",\r\n"
				+ "				\"count\": 3\r\n"
				+ "			},\r\n"
				+ "			\"endBalance\": \"0.00\",\r\n"
				+ "			\"showEnd\": false,\r\n"
				+ "			\"expendDetails\": [],\r\n"
				+ "			\"showClassify\": true,\r\n"
				+ "			\"incomeDetails\": [{\r\n"
				+ "				\"amount\": \"0.60\",\r\n"
				+ "				\"counts\": 3,\r\n"
				+ "				\"title\": \"转账\"\r\n"
				+ "			}],\r\n"
				+ "			\"beginBalance\": \"0.00\"\r\n"
				+ "		},\r\n"
				+ "		\"paging\": {\r\n"
				+ "			\"totalItems\": 3,\r\n"
				+ "			\"current\": 1,\r\n"
				+ "			\"sizePerPage\": 20\r\n"
				+ "		},\r\n"
				+ "		\"showBillInfo\": true,\r\n"
				+ "		\"detail\": [{\r\n"
				+ "			\"bizNos\": \"\",\r\n"
				+ "			\"billSource\": \"\",\r\n"
				+ "			\"otherBizFullName\": \"\",\r\n"
				+ "			\"balance\": \"14.03\",\r\n"
				+ "			\"transDate\": \"2021-07-20\",\r\n"
				+ "			\"action\": {\r\n"
				+ "				\"needDetail\": false\r\n"
				+ "			},\r\n"
				+ "			\"bizOrigNo\": \"\",\r\n"
				+ "			\"cashierChannels\": \"\",\r\n"
				+ "			\"storeName\": \"\",\r\n"
				+ "			\"depositBankNo\": \"\",\r\n"
				+ "			\"signProduct\": \"\",\r\n"
				+ "			\"orderNo\": \"\",\r\n"
				+ "			\"bizDesc\": \"\",\r\n"
				+ "			\"tradeNo\": \"20210720200040011100330004302168\",\r\n"
				+ "			\"accountType\": \"转账\",\r\n"
				+ "			\"otherAccountFullname\": \"**华\",\r\n"
				+ "			\"accountLogId\": \"331967272385331\",\r\n"
				+ "			\"transMemo\": \"转账\",\r\n"
				+ "			\"chargeRate\": \"\",\r\n"
				+ "			\"tradeAmount\": \"0.30\",\r\n"
				+ "			\"tradeTime\": \"2021-07-20 16:18:34\",\r\n"
				+ "			\"otherAccount\": \"dummy\",\r\n"
				+ "			\"actualChargeAmount\": \"0.00\",\r\n"
				+ "			\"goodsTitle\": \"\",\r\n"
				+ "			\"otherAccountEmail\": \"136***@qq.com\",\r\n"
				+ "			\"buyerMemo\": \"\"\r\n"
				+ "		}, {\r\n"
				+ "			\"bizNos\": \"\",\r\n"
				+ "			\"billSource\": \"\",\r\n"
				+ "			\"otherBizFullName\": \"\",\r\n"
				+ "			\"balance\": \"13.73\",\r\n"
				+ "			\"transDate\": \"2021-07-20\",\r\n"
				+ "			\"action\": {\r\n"
				+ "				\"needDetail\": false\r\n"
				+ "			},\r\n"
				+ "			\"bizOrigNo\": \"\",\r\n"
				+ "			\"cashierChannels\": \"\",\r\n"
				+ "			\"storeName\": \"\",\r\n"
				+ "			\"depositBankNo\": \"\",\r\n"
				+ "			\"signProduct\": \"\",\r\n"
				+ "			\"orderNo\": \"\",\r\n"
				+ "			\"bizDesc\": \"\",\r\n"
				+ "			\"tradeNo\": \"20210720200040011100330004334599\",\r\n"
				+ "			\"accountType\": \"转账\",\r\n"
				+ "			\"otherAccountFullname\": \"**华\",\r\n"
				+ "			\"accountLogId\": \"331963835253331\",\r\n"
				+ "			\"transMemo\": \"转账\",\r\n"
				+ "			\"chargeRate\": \"\",\r\n"
				+ "			\"tradeAmount\": \"0.20\",\r\n"
				+ "			\"tradeTime\": \"2021-07-20 16:18:24\",\r\n"
				+ "			\"otherAccount\": \"dummy\",\r\n"
				+ "			\"actualChargeAmount\": \"0.00\",\r\n"
				+ "			\"goodsTitle\": \"\",\r\n"
				+ "			\"otherAccountEmail\": \"136***@qq.com\",\r\n"
				+ "			\"buyerMemo\": \"\"\r\n"
				+ "		}, {\r\n"
				+ "			\"bizNos\": \"\",\r\n"
				+ "			\"billSource\": \"\",\r\n"
				+ "			\"otherBizFullName\": \"\",\r\n"
				+ "			\"balance\": \"13.53\",\r\n"
				+ "			\"transDate\": \"2021-07-20\",\r\n"
				+ "			\"action\": {\r\n"
				+ "				\"needDetail\": false\r\n"
				+ "			},\r\n"
				+ "			\"bizOrigNo\": \"\",\r\n"
				+ "			\"cashierChannels\": \"\",\r\n"
				+ "			\"storeName\": \"\",\r\n"
				+ "			\"depositBankNo\": \"\",\r\n"
				+ "			\"signProduct\": \"\",\r\n"
				+ "			\"orderNo\": \"\",\r\n"
				+ "			\"bizDesc\": \"\",\r\n"
				+ "			\"tradeNo\": \"20210720200040011100330004367390\",\r\n"
				+ "			\"accountType\": \"转账\",\r\n"
				+ "			\"otherAccountFullname\": \"**华\",\r\n"
				+ "			\"accountLogId\": \"331966425061331\",\r\n"
				+ "			\"transMemo\": \"转账\",\r\n"
				+ "			\"chargeRate\": \"\",\r\n"
				+ "			\"tradeAmount\": \"0.10\",\r\n"
				+ "			\"tradeTime\": \"2021-07-20 16:18:12\",\r\n"
				+ "			\"otherAccount\": \"dummy\",\r\n"
				+ "			\"actualChargeAmount\": \"0.00\",\r\n"
				+ "			\"goodsTitle\": \"\",\r\n"
				+ "			\"otherAccountEmail\": \"136***@qq.com\",\r\n"
				+ "			\"buyerMemo\": \"\"\r\n"
				+ "		}]\r\n"
				+ "	},\r\n"
				+ "	\"isEntOperator\": false\r\n"
				+ "}\r\n"
				+ "";
		
		
		JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
		Map<String, Object>  jsonMap = JSONUtil.toBean(jsonObject, Map.class, false);
		//System.out.println(">> jsonMap:"+jsonMap.get("result"));
		
		for(Map.Entry<String, Object> i: jsonMap.entrySet()) {
			if(i.getKey().equals("result")) {
				//System.out.println("i.getValue():"+i.getValue());
				
				JSONObject resultObject = JSONUtil.parseObj(i.getValue());
				Map<String, Object>  resultMap = JSONUtil.toBean(resultObject, Map.class, false);
				for(Map.Entry<String, Object> x: resultMap.entrySet()) {
					
					if(x.getKey().equals("detail")) {
						//System.out.println(">> x.getKey()"+x.getKey());
						//System.out.println(">> x.getValue()"+x.getValue());
						
						JSONArray detailArray = JSONUtil.parseArray(x.getValue());
						JSONObject orderObject;
						Map<String, Object>  orderMap = new HashMap<String, Object>();
						if(detailArray.size() > 0) {
							for(Object z : detailArray) {
								orderObject = JSONUtil.parseObj(z);
								orderMap = JSONUtil.toBean(orderObject, Map.class, false);
								System.out.println(">> Thread2 balance:"+orderMap.get("balance")+" tradeNo:"+orderMap.get("tradeNo")+" tradeAmount:"+orderMap.get("tradeAmount")+" tradeTime:"+orderMap.get("tradeTime"));
							}
						}
					}
				}
				
			}
		}


	}

}
