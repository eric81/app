package com.eudemon.taurus.app.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 批量、定时处理数据
 * 当时间间隔参数为负数时，则不启用按时间间隔入库
 * 1、数据量达到批量设定值时处理数据
 * 2、按间隔时间定时处理数据
 * 
 * @author xiaoyang.zhang
 * 
 */
public class BatchTimerPool {
	private int threadNum = 3;

	private WorkQueue wq = null;

	private LinkedList<Object> datas = new LinkedList<Object>();

	private int saveTime = 30;

	private int batchSize = 100;

	private BatchTimerHandler handler = null;
	


	/**
	 * 
	 * @param threadNum
	 *            处理线程数
	 * @param batchSize
	 *            批量大小设定值
	 * @param saveTime
	 *            时间间隔设定值
	 * @param handler
	 *            数据处理回调接口
	 */
	public BatchTimerPool(int threadNum, int batchSize, int saveTime, BatchTimerHandler handler) {
		this.threadNum = threadNum;
		this.batchSize = batchSize;
		this.saveTime = saveTime;
		this.handler = handler;
	}

	/**
	 * 启动池
	 */
	public void start() {
		wq = new WorkQueue(threadNum);
		wq.start();
		if (saveTime > 0) {
			startTimer();
		}
	}

	/**
	 * 定时批量入库
	 */
	private void startTimer() {
		Timer saveTimer = new Timer();
		saveTimer.schedule(new TimerTask() {
			public void run() {
				timerHandle();
			}
		}, saveTime * 1000, saveTime * 1000);
	}

	/**
	 * 将数据加入池
	 * 
	 * @param data
	 */
	public synchronized void addData(Object data) {
		datas.addLast(data);

		if (datas.size() >= batchSize) {
			handleData();
		}
	}

	private synchronized void timerHandle() {
		if (datas.size() == 0) {
			return;
		}
		handleData();
	}

	private void handleData() {
		final List<Object> ls = new ArrayList<Object>();
		for (int i = 0; i < datas.size(); i++) {
			ls.add(datas.get(i));
		}
		datas.clear();

		wq.addTask(new DefaultWorkTask() {
			public void execute() {
				handler.handleData(ls);
			}
		});
	}
}