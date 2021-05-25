package com.metro.ccms.framework.monitor;

import java.io.Serializable;
import java.util.Stack;

public class JwMonitorContext implements Serializable{
	private static final long serialVersionUID = -2771546115129662016L;
	Stack<JwMonitor> cacheStack=new Stack<JwMonitor>();
	Stack<JwMonitor> monitorStack=new Stack<JwMonitor>();


	public Stack<JwMonitor> getCacheStack() {
		return cacheStack;
	}


	public void setCacheStack(Stack<JwMonitor> cacheStack) {
		this.cacheStack = cacheStack;
	}


	public Stack<JwMonitor> getMonitorStack() {
		return monitorStack;
	}


	public void setMonitorStack(Stack<JwMonitor> monitorStack) {
		this.monitorStack = monitorStack;
	}
 
	
}
