package com.example;

import com.example.dao.MortgageDao;
import com.example.model.Mortgage;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.stream.Collectors;
// Servlet for responding on route /calculate
public class MortgageServlet extends HttpServlet {

    // handing form POST request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.addHeader("Content-Encoding", "application/json");

        BufferedReader reader = request.getReader();
        String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();
        Mortgage mortgage = gson.fromJson(body,Mortgage.class);
        mortgage.setMonthlyPayment(mortgage.calculate());
        MortgageDao mortgageDao = new MortgageDao("mortgage");
        mortgageDao.saveMortgageResult(mortgage);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(mortgage));
        out.flush();
    }
}
