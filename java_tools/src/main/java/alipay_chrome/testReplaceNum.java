package alipay_chrome;

public class testReplaceNum {
	
	public void main(String[] args) {
		String a = "8.10";
		System.out.println(OtherClass.stringReplaceAll("0.*?$", "", a));
	}

}
