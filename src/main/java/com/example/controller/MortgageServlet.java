package com.example.controller;

import com.example.model.Customer;
import com.example.services.CustomerService;
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

        BufferedReader reader = request.getReader();
        String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();
        Customer customer = gson.fromJson(body, Customer.class);
        customer.setMonthlyPayment(customer.calculate());


        CustomerService customerService = new CustomerService("mortgage");
        customerService.saveCustomer(customer);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(customer));
        out.flush();
    }
}
