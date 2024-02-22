package LinuxShellCommand;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LinuxShellCommand {

	//批量kill进程shell命令： ps -ef | grep -i UpdateMysqlData | awk '{print $2}' | xargs kill -9

	public static void main(String[] args) {
		//int maxThreadNum = Integer.valueOf(args[0]);
		
		int maxThreadNum = 24;
		
		if(OtherClass.isLinux()) {
			ProcessBuilder processBuilder = new ProcessBuilder();
		    // -- Linux --
			int num = 0;
			int itemidNum = 0;
			while(num < maxThreadNum) {
				num+=1;
				if(num == 1) {
					itemidNum = 0;
				}else {
					itemidNum += 720000;
				}
				
			
				// 批量kill进程shell命令： ps -ef | grep -i UpdateMysqlData | awk '{print $2}' | xargs kill -9
			
				//"bash", "-c", "java -jar UpdateMysqlData.jar "+itemidNum+" > ./log/"+num+".txt &"
			    // Run a shell command
				//通常使用shell去运行脚本，两种方法 bash xxx.sh，另外一种就是bash -c "cmd string"
				String shellScript = "java -jar UpdateMysqlData.jar "+num+" "+itemidNum+" > ./log/"+num+".txt &";
			    System.out.println(">> shellCript:"+shellScript);
				processBuilder.command("bash", "-c",shellScript);
			    // Run a shell script
			    //processBuilder.command("path/to/hello.sh");
		
			    // -- Windows --
			    // Run a command
			    //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
			    // Run a bat file
			    //processBuilder.command("C:\\Users\\mkyong\\hello.bat");
		
			    try {
		
			        Process process = processBuilder.start();
			        StringBuilder output = new StringBuilder();
			        BufferedReader reader = new BufferedReader(
			        new InputStreamReader(process.getInputStream()));
		
			        String line;
			        while ((line = reader.readLine()) != null) {
			            output.append(">> "+line);
			        }
		
			        int exitVal = process.waitFor();
			        if (exitVal == 0) {
			            System.out.println(">> num:"+num+" Running success!");
	
			        } else {
			            //abnormal...
			        }
		
			    } catch (IOException e) {
			        e.printStackTrace();
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
		}else {
			System.out.println("This java-tools just only for linux!");
		}

		
		

		
	}
}
