package com.defonds.socket.begin;  
  
import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.Socket;  
  
public class Client {  
    public static final String IP_ADDR = "localhost";//服务器地址   
    public static final int PORT = 8888;//服务器端口号    
      
    public static void main(String[] args) {    
        System.out.println("客户端启动...");    
        System.out.println("当接收到服务器端字符为 \"HELLO\" 的时候, 客户端将终止\n");   
        while (true) {    
            Socket socket = null;  
            try {  
                //创建一个流套接字并将其连接到指定主机上的指定端口号  
                socket = new Socket(IP_ADDR, PORT);    
                    
                //读取服务器端数据    
                DataInputStream input = new DataInputStream(socket.getInputStream());    
              
                    
                String ret = input.readUTF();     
                System.out.println("服务器端返回过来的是: " + ret);    
                //向服务器端发送数据    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
//                System.out.print("请输入: \t");    
//                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
//                out.writeUTF(str);
                // 如接收到 "HELLO" 则断开连接    
                if ("HELLO".equals(ret)) {  
//                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                   System.out.print("请输入: \t");  
                   String str2 = new BufferedReader(new InputStreamReader(System.in)).readLine();    
                  
                   if("OK".equals(str2)){//如果输入是OK则关闭socket连接
                	   out.writeUTF(str2); 
                	    System.out.println("客户端将关闭连接");    
	   	                Thread.sleep(3000);   //睡眠时间长点  防止服务器自己未关闭前抛异常
	   	                break; 
                   }else{
                	   out.writeUTF(str2); 
                   }
	                  
                }  else{
                  //如果是其他字符，客户端可以向服务器通信
                  System.out.print("请输入: \t");  
                  String str3 = new BufferedReader(new InputStreamReader(System.in)).readLine();    
                  out.writeUTF(str3); 
                } 

                
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("客户端异常:" + e.getMessage());   
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) {  
                        socket = null;   
                        System.out.println("客户端 finally 异常:" + e.getMessage());   
                    }  
                }  
            }  
        }    
    }    
}    

