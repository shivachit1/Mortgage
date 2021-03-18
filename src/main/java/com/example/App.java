package com.example;

import com.example.helper.FileReader;
import com.example.model.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException {
       ArrayList<Customer> customers = FileReader.readFile();

        String resultContent = "";
        String asterisk = "************************************************************************************************";
       for( Customer customer : customers){
           String result = "Prospect "+ (customers.indexOf(customer)+1) +": "+ customer.toString();
           resultContent= resultContent.concat(asterisk+"\n\n"+result+"\n\n"+asterisk+"\n");
       }
       writeToFile(resultContent);
    }

    public static void writeToFile(String result) throws IOException {
        FileWriter fileWriter = new FileWriter("src/result.txt");
        fileWriter.write(result);
        fileWriter.close();
    }

}
