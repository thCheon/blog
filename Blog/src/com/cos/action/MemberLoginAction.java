package com.cos.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.dao.MemberDAO;
import com.cos.dto.MemberVO;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class MemberLoginAction implements Action{
	private static String naming = "MemberController : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		MemberDAO dao = new MemberDAO();
		
		String id = null, pw = null, salt = "";
		
		if(request.getParameter("id")!=null)id=request.getParameter("id");
		if(dao.select_salt(id)!=null)salt = dao.select_salt(id);
		
		if(request.getParameter("password")!=null) {
			pw = request.getParameter("password"); // SHA256으로 해쉬하기.
			pw = SHA256.getEncrypt(pw, salt);
		}
		
		int result = dao.login(id,pw);
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("id", id);
			Script.moving(response, "로그인 성공", url);
		} else if(result == 2){
			session.setAttribute("id", id);
			Script.moving(response, "이메일 인증이 되지 않았습니다.", url);
		} else  {
			Script.moving(response, "입력하신 정보를 조회하지 못했습니다. 다시한번 확인해 주십시오.");
		}
	}
}
