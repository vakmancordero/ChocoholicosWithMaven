package com.devlabs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devlabs.service.LoginService;

/**
 *
 * @author VakSF
 */
public class LoginController extends HttpServlet {
    
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();
        
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        
        if (!user.isEmpty() && !password.isEmpty()) {
            
            if (this.loginService.login(user, password)) {
                
                request.getSession().setAttribute("loggedUser", user);
                
                response.sendRedirect("home.jsp");
                
            } else {
                
                response.sendRedirect("login.jsp");
                
            }
            
        } else {
            
            writer.print("empty fields");
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "LoginController Class";
    }

}
