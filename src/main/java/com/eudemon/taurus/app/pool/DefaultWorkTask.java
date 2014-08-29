package com.eudemon.taurus.app.pool;

public abstract class DefaultWorkTask implements WorkTask {
	public long getId() {
		return 0;
	}

	public int getPriority() {
		return 0;
	}
}
