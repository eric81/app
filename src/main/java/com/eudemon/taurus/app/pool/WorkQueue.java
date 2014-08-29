package com.eudemon.taurus.app.pool;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 拥有线程池的工作队列
 * @author xiaoyang.zhang
 *
 */
public class WorkQueue {
	/**
	 * 线程数
	 */
	private int numOfThread =3;
	
    /**
     * 线程池
     */
    private ExecutorService exec = null;
    /**
     * 任务对列
     */
    private LinkedList<WorkTask> tasks = new LinkedList<WorkTask>();
    
    /**
     * 任务队列，优先级高
     */
    private LinkedList<WorkTask> tasksHigh = new LinkedList<WorkTask>();
   
    
    public WorkQueue(int threadCount){
    	numOfThread = threadCount;
    }
    
    public void start(){
    	exec = Executors.newFixedThreadPool(numOfThread);
        for (int i = 0; i < numOfThread; i++){
            exec.execute(new WorkThread(this));
        }
    }
    
    /**
     * 从任务队列中取出排在第一位的任务，并将此任务移出队列
     * @return
     */
    public synchronized WorkTask getTask(){
    	if(!tasksHigh.isEmpty()){
    		return tasksHigh.removeFirst();
    	}
    	else{
    		if(!tasks.isEmpty()){
    			return tasks.removeFirst();
    		}
    		else{
    			return null;
    		}
    	}
    } 
    
    /**
     * 获取任务队列中排在第一位的任务，该任务并不会被移除队列
     * @return
     */
    public synchronized WorkTask peekTask(){
    	if(!tasksHigh.isEmpty()){
    		return tasksHigh.removeFirst();
    	}
    	else{
    		if(!tasks.isEmpty()){
    			return tasks.removeFirst();
    		}
    		else{
    			return null;
    		}
    	}
    } 
    
    /**
     * 将任务加入队列末尾
     * @param task
     */
    public synchronized void addTask(WorkTask task){
    	if(null != task){
    		if(task.getPriority() == 1){
    			tasksHigh.addLast(task);
    		}
    		else{
    	    	tasks.addLast(task);
    		}
	    	notify();//唤醒一个工作线程
    	}
    }
    
    /**
     * 批量加入任务
     */
    public synchronized void addTaskLs(List<WorkTask> taskLs){
    	if(null != taskLs && taskLs.size() > 0){
    		WorkTask task = null;
    		for(int i = 0; i < taskLs.size(); i++){
    			task = taskLs.get(i);
        		if(task.getPriority() == 1){
        			tasksHigh.addLast(task);
        		}
        		else{
        	    	tasks.addLast(task);
        		}
    		}
    		
    		notify();//唤醒一个工作线程
    	}
    }
    
    /**
     * 根据Task ID 删除某个Task
     * @param id ：Task ID
     */
    public synchronized void removeTask(long id){
    	WorkTask task = null;
    	for(int i = 0; i< tasksHigh.size(); i++){
    		task = tasksHigh.get(i);
    		if(task.getId() == id){
    			tasksHigh.remove(i);
    			break;
    		}
    	}
    	
    	for(int i = 0; i< tasks.size(); i++){
    		task = tasks.get(i);
    		if(task.getId() == id){
    			tasks.remove(i);
    			break;
    		}
    	}
    }
    
    /**
     * 删除所有任务
     */
    public synchronized void removeAllTask(){
    	tasks.clear();
    	tasksHigh.clear();
    }
    
    /**
     * 工作线程
     * @author xiaoyang.zhang
     *
     */
    private class WorkThread implements Runnable{
    	private WorkQueue wq = null;

    	public WorkThread(WorkQueue wq) {
    		this.wq = wq;
    	}
    	
    	public void run() {
    		WorkTask task = null;

    		while (true) {
    			synchronized (wq) {
    				task = getTask();
    				
    				if (null == task) {
    					try {
    						wq.wait();//没有任务，当前线程休眠

    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    				}
    			}

    			if (null != task) {
    				task.execute();//有任务，执行任务
    			}
    		}
    	}
    }
}