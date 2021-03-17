package com.example;

import com.example.dao.MortgageDao;
import com.example.model.Mortgage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MortgagePlan {
    public static void main(String[] args) throws IOException {
       File file = new File("src/prospects.txt");
       String resultContent="";
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            int i=0;
            String asterisk = "";
            while(scanner.hasNext()){
                String unFormattedLine = scanner.nextLine();
                int lineLengthCheck = unFormattedLine.split(",").length;
                if(lineLengthCheck >=4 ){
                    i++;
                    if(unFormattedLine.contains("\"")){
                        String[] userFields = unFormattedLine
                                .replaceFirst("\"","")
                                .split("\"");
                        userFields[0] = userFields[0].replace(","," ");
                        unFormattedLine = String.join("",userFields);
                    }

                    String[] userFields = unFormattedLine.split(",");
                    String name= userFields[0];

                    // getting the loan value
                    double loanAmount= Double.parseDouble(userFields[1]);
                    // getting yearly interest rate and changing into monthly interest rate
                    double interestRate= (Double.parseDouble(userFields[2]));
                    // getting years period
                    double years = Double.parseDouble(userFields[3]);

                    Mortgage mortgage = new Mortgage(name,loanAmount,interestRate,years);
                    mortgage.setMonthlyPayment(mortgage.calculate());

                    MortgageDao mortgageDao = new MortgageDao("mortgage");
                    mortgageDao.saveMortgageResult(mortgage);

                    String result = "Prospect "+ i+": "+ mortgage.getName()+" wants to borrow "+ mortgage.getLoanAmount()+ "€ for a period of "+ years+ " years and pay "+ mortgage.getMonthlyPayment() +"€ each month";
                    asterisk = "";
                    int asteriskCount = 0;
                    int asteriskLengthToBe = result.length();
                    while(asteriskCount<=asteriskLengthToBe){
                        asterisk = asterisk.concat("*");
                        asteriskCount++;
                    }

                    resultContent= resultContent.concat("\n"+asterisk+"\n\n"+result+"\n");

                }
            }
            resultContent= resultContent.concat("\n"+asterisk);
            writeToFile(resultContent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(String result) throws IOException {
        FileWriter fileWriter = new FileWriter("src/result.txt");
        fileWriter.write(result);
        fileWriter.close();
    }

}
