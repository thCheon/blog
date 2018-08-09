package com.cos.action;

public class ActionFactory {
	private static String naming = "MemberController : ";
	
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory() {}
	
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String cmd) {
		if(cmd.equals("member_join")) {
			return new MemberJoinAction();
		} else if(cmd.equals("member_login")) {
			return new MemberLoginAction();
		}
		return null;
	}
}
