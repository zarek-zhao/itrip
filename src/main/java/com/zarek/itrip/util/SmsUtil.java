//package com.zarek.itrip.util;
//
//import com.cloopen.rest.sdk.CCPRestSmsSDK;
//
//import java.util.HashMap;
//import java.util.Set;
//
///**
// * <b>手机短信发送工具类</b>
// * @author Arthur
// * @version 1.0.0
// * @since 1.0.0
// */
//public class SmsUtil {
//
//	/**
//	 * <b>使用短信发送激活验证码</b>
//	 * @param cellphone
//	 * @param code
//	 * @return
//	 */
//	public static boolean sendActivationCode(String cellphone, String code) {
//		HashMap<String, Object> result = null;
//
//		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
//		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
//		restAPI.setAccount("8a216da8747ac982017490df4eb11e32", "4d5d334065f341d1b6accc70afac5d68");// 初始化主帐号和主帐号TOKEN
//		restAPI.setAppId("8a216da8747ac982017490df4f8c1e38");// 初始化应用ID
//		result = restAPI.sendTemplateSMS(cellphone,"1", new String[]{code, "30"});
//
//		System.out.println("SDKTestSendTemplateSMS result=" + result);
//
//		if("000000".equals(result.get("statusCode"))){
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				System.out.println(key +" = "+object);
//			}
//			return true;
//		}else{
//			//异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//		}
//		return false;
//	}
//}
