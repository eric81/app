package com.eudemon.taurus.app.pool;

public interface WorkTask{
	void execute();
	long getId();
	int getPriority();
}

