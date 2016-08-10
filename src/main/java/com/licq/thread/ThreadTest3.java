package com.licq.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * 三个线程顺序打印字符串ABC，打印10遍
 * 写法三
 * */
public class ThreadTest3 {

	private static int seq = 0;
	private static Thread[] threads = new Thread[3];
	private static Map<String, String> mapping = new HashMap<String, String>();

	public static void main(String[] args) {
		mapping.put("0", "A");
		mapping.put("1", "B");
		mapping.put("2", "C");

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				public void run() {
					while (true) {
						if(seq >= 30){
							break;
						}
						String threadName = Thread.currentThread().getName();
						if (seq % threads.length == Integer
								.parseInt(threadName)) {
							System.out.println("------------" + mapping.get(threadName) + "-----------");
							seq = seq + 1;
						} else {
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
							}
						}
					}
				}
			});
			threads[i].setName(String.valueOf(i));
			threads[i].start();
		}

	}


}
