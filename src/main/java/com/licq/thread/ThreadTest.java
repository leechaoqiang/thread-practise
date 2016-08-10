package com.licq.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 三个线程顺序打印字符串ABC，打印10遍
 * 写法一
 * */
public class ThreadTest {
	private boolean flagA =true;
	private boolean flagB =false;
	private boolean flagC =false;
	private Object objA = new Object();
	private Object objB = new Object();
	private Object objC = new Object();
	private void printA() throws InterruptedException{
		for(int i=0;i<10;i++){
			synchronized(objA){
				synchronized(objB){
					while(flagA){
						System.out.println("A");
						flagA=false;
						flagB=true;
						objB.notify();
					}
				}
				objA.wait();
			}		
		}
	}
	private void printB() throws InterruptedException{
		for(int i=0;i<10;i++){
			synchronized(objB){
				synchronized(objC){
					while(flagB){
						System.out.println("B");
						flagB=false;
						flagC=true;
						objC.notify();
					}
				}
				objB.wait();
			}		
		}
	}
	
	private void printC() throws InterruptedException{
		for(int i=0;i<10;i++){
			synchronized(objC){
				synchronized(objA){
					while(flagC){
						System.out.println("C");
						flagA=true;
						flagC=false;
						objA.notify();
					}
				}
				objC.wait();
			}		
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final ThreadTest ThreadTest =new ThreadTest();
		
		Runnable ra = new Runnable(){
			public void run(){
				try {
					ThreadTest.printA();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Runnable rb = new Runnable(){
			public void run(){
				try {
					ThreadTest.printB();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Runnable rc = new Runnable(){
			public void run(){
				try {
					ThreadTest.printC();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(ra).start();
		Thread.sleep(300); 
		new Thread(rb).start();
		Thread.sleep(300);
		new Thread(rc).start();
			
	}
	

}
