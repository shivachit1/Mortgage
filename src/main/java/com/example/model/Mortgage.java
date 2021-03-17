package com.example.model;
import java.text.DecimalFormat;
import com.example.MathUtil;

public class Mortgage {
   private String name;
   private double loanAmount;
   private double interestRate;
   private double years;
   private double monthlyPayment;

    public Mortgage(String name, double loanAmount, double interestRate, double years) {
        this.name = name;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getYears() {
        return years;
    }

    public void setYears(double years) {
        this.years = years;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double calculate(){

        // error handling before calculation
        if(this.loanAmount<=0) throw new IllegalArgumentException("Loan Amount should be greater than 0");
        if(this.interestRate<=0) throw new IllegalArgumentException("Interest Rate should be greater than 0");
        if(this.years<=0) throw new IllegalArgumentException("years should be greater than 0");

        // getting yearly interest rate and changing into monthly interest rate
        double b= (this.interestRate/100)/12;
        double p = this.years*12;
        double monthlyPay = this.loanAmount*((b*(MathUtil.power((1+b),p)))/((MathUtil.power((1+b),p))-1));
        //System.out.println(monthlyPay);
        DecimalFormat df = new DecimalFormat("#.##");
        monthlyPay = Double.parseDouble(df.format(monthlyPay));
       return monthlyPay;
    }

}
