package zilv_zhuomianshubiaojiankong;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class MouseControl {

	public static void main(String[] args) throws AWTException {
		while(true) {
			
			//获取系统的小时时间
			Date date = DateUtil.date();
			int hour = DateUtil.hour(date, true);
			System.out.println(">> hour:"+hour);
			
			
			
			
			if(isRestTime(hour)) {
				System.out.println(">> 注意休息，要自律！当前所拥有的根本不算什么，需要自律才能拥有更多！");
				Robot robot = new Robot();
				
				int countNum = 0;
				while(countNum < 5) {
					countNum += 1;
					robot.mouseMove(0,0);
					OtherClass.timeSleep(1);

				}
				
			}
			OtherClass.timeSleep(10);
			
		}
		
		

	}
	
	public static Boolean isRestTime(int hour) {
		boolean isRestTime = false;
		
		switch(hour) {
			case 0:
				isRestTime = true;
				break;
			case 1:
				isRestTime = true;
				break;
			case 2:
				isRestTime = true;
				break;
			case 3:
				isRestTime = true;
				break;
			case 4:
				isRestTime = true;
				break;
			case 5:
				isRestTime = true;
				break;
			case 6:
				isRestTime = true;
				break;
			case 7:
				isRestTime = true;
				break;
			case 8:
				isRestTime = false;
				break;
			case 9:
				isRestTime = false;
				break;
			case 10:
				isRestTime = false;
				break;
				
			case 11:
				isRestTime = false;
				break;
			case 12:
				isRestTime = true;
				break;
			case 13:
				isRestTime = true;
				break;
			case 14:
				isRestTime = false;
				break;
			case 16:
				isRestTime = false;
				break;
			case 17:
				isRestTime = false;
				break;
			case 18:
				isRestTime = false;
				break;
			case 19:
				isRestTime = true;
				break;
			case 20:
				isRestTime = true;
				break;
			case 21:
				isRestTime = true;
				break;
			case 22:
				isRestTime = true;
				break;
			case 23:
				isRestTime = true;
				break;
			case 24:
				isRestTime = true;
				break;
			default:
				isRestTime = false;
		}
		
		
		return isRestTime;
	}

}
