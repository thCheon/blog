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

public class MemberJoinAction implements Action{
	private static String naming = "MemberController : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "gmail/emailSendAction.jsp";
		MemberVO mvo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		
		String id = null, pw = null, un = null, em = null, addr = null, salt = SHA256.generateSalt();
		if(request.getParameter("id")!=null)id=request.getParameter("id");
		if(request.getParameter("password")!=null) {
			pw = request.getParameter("password"); // SHA256으로 해쉬하기.
			pw = SHA256.getEncrypt(pw, salt);
		}
		if(request.getParameter("username")!=null)un=request.getParameter("username");
		if(request.getParameter("roadFullAddr")!=null)addr=request.getParameter("roadFullAddr");
		if(request.getParameter("email")!=null)em=request.getParameter("email");
		
		mvo.setId(id);
		mvo.setPassword(pw);
		mvo.setUsername(un);
		mvo.setRoadFullAddr(addr);
		mvo.setEmail(em);
		mvo.setSalt(salt);
		
		int result = dao.insert(mvo);
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", mvo.getId());
			Script.moving(response, "구글 인증 페이지", url);
		} else {
			Script.moving(response, "회원가입 실패");
		}
	}
}
