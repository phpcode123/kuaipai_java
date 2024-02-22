package zilv_rest_for_minute;

import java.awt.AWTException;
import java.awt.Robot;

import cn.hutool.core.date.DateUtil;

public class RestForAnHalfHour{

	public static void main(String[] args) throws AWTException {
		while(true) {
			
			//获取系统的分钟时间数字
			int minute= DateUtil.thisMinute();
			System.out.println(">> minute:"+minute);
			
		
			
			if(isRestTime(minute)) {
				System.out.println(">> 注意休息，要自律！当前所拥有的根本不算什么，需要自律才能拥有更多！");
				Robot robot = new Robot();
				
				int countNum = 0;
				while(countNum < 100) {
					countNum += 1;
					robot.mouseMove(1900,1060);
					OtherClass.timeSleepByMilliSecond(100);;

				}
				
			}
			OtherClass.timeSleep(10);
			
		}
		
		

	}
	
	
	
	//最好用数组ArrayList来判断，可以减少不少代码量
	public static Boolean isRestTime(int minute) {
		boolean isRestTime = false;
		
		
		
		
		switch(minute) {
			case 1:
				isRestTime = true;
				break;
			case 2:
				isRestTime = true;
				break;
			case 31:
				isRestTime = true;
				break;
			case 32:
				isRestTime = true;
				break;
			default:
				isRestTime = false;
		}
		
		
		return isRestTime;
	}

}
