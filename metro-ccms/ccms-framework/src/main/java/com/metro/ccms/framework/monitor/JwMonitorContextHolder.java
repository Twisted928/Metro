package com.metro.ccms.framework.monitor;



public final class JwMonitorContextHolder {
	private static final ThreadLocal<JwMonitorContext> CONTEXT = new ThreadLocal<JwMonitorContext>();
	public JwMonitorContextHolder(){

	}
	public static void put(JwMonitorContext context){
		CONTEXT.set(context);
	}
	public static JwMonitorContext get(){
		JwMonitorContext jwMonitorContext=CONTEXT.get();
		if(null==jwMonitorContext) {
			jwMonitorContext=new JwMonitorContext();
		}
		return jwMonitorContext;
	}
	public static void clear(){
		CONTEXT.remove();
	}
}
