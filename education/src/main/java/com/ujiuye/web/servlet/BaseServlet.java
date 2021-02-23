package com.ujiuye.web.servlet;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //定义个参数(页面传递过来的) method:表示method的值是什么那么我们对应的Servlet中就调用对应的方法
		 String method = req.getParameter("method");
		 //获取某个Servlet类的Class类型对象
		 Class<? extends BaseServlet> clazz = this.getClass();
		 //调用method对应的方法
		 try {
			Method m = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			 //方法调用
			m.invoke(this, req,resp);
		} catch (Exception e) {
			throw new RuntimeException(e); 
		}
		 
	}
}
