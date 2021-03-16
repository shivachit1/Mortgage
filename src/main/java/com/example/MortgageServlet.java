package com.example;

import com.example.dao.MortgageDao;
import com.example.model.Mortgage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// Servlet for responding on route /calculate
public class MortgageServlet extends HttpServlet {

    // handing form POST request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // getting all form data using request
        String name = request.getParameter("name");
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));
        double years = Double.parseDouble(request.getParameter("years"));
        // creating User class model
        Mortgage mortgage = new Mortgage(name,loanAmount,interestRate,years);

        MortgageDao mortgageDao = new MortgageDao("mortgage");
        mortgageDao.saveMortgageResult(mortgage);
        // calling calculate method of user class
        double monthlyPayment = mortgage.calculate();

        // creating string value result with calculated data and html tags.
        String result=
                "<h2>Result</h2>"+
                "<h2> Hello, " + name + "</h2>"+
                "<p>You have to pay "+
                    "<strong>"+ monthlyPayment +" € </strong>"+
                    " monthly to payback your " +
                    "<strong>"+ loanAmount +"€ </strong>"+
                    "loan in <strong>"+years+"</strong> years "+
                    "with interest rate "+
                    "<strong>"+ interestRate +"%</strong> annually."+
                "</p>";
        request.setAttribute("results", result);

        // sending back data to same page
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
