package com.licq.thread;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 三个线程顺序打印字符串ABC，打印10遍
 * 写法二
 * @author lichaoqiang
 * */
public class ThreadTest2 implements Runnable{
	
	
	static Thread[] threads = new Thread[3];
	static int len = 3;
	static final AtomicInteger ai = new AtomicInteger(1);
	String val="";
	
	public ThreadTest2(String val){
		this.val = val;
	}
	
	public void run() {
		while(true){
			if(ai.get()<= 30){
				
				if("A".equals(val)){
					
					if(ai.get()%len == 1){
						// TODO Auto-generated method stub
						System.out.println(Thread.currentThread().getName()+val);
						ai.getAndIncrement();
					} 
				}
				if("B".equals(val)){
					if(ai.get()%len == 2){
						// TODO Auto-generated method stub
						System.out.println(Thread.currentThread().getName()+val);
						ai.getAndIncrement();
					}
				}
				if("C".equals(val)){
					if(ai.get()%len == 0){
						// TODO Auto-generated method stub
						System.out.println(Thread.currentThread().getName()+val);
						ai.getAndIncrement();
					}
				}
			}else{
				break;
			}
			
		}
		
//		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		threads[0] = new Thread(new ThreadTest2("A"));
		
		threads[1] = new Thread(new ThreadTest2("B"));
		
		threads[2] = new Thread(new ThreadTest2("C"));
		
		threads[0].start();
		threads[1].start();
		threads[2].start();
	}

}
