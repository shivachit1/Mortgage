package com.example.helper;

import com.example.model.Customer;
import com.example.services.CustomerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<Customer> readFile (){
        File file = new File("src/prospects.txt");
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNext()){
                String unFormattedLine = scanner.nextLine();
                int lineLengthCheck = unFormattedLine.split(",").length;
                if(lineLengthCheck >=4 ){
                    if(unFormattedLine.contains("\"")){
                        String[] userFields = unFormattedLine
                                .replaceFirst("\"","")
                                .split("\"");
                        userFields[0] = userFields[0].replace(","," ");
                        unFormattedLine = String.join("",userFields);
                    }

                    String[] userFields = unFormattedLine.split(",");
                    Customer customer = new Customer(
                            userFields[0],
                            Double.parseDouble(userFields[1]),
                            Double.parseDouble(userFields[2]),
                            Double.parseDouble(userFields[3])
                    );
                    customer.setMonthlyPayment(customer.calculate());

                    // connecting to mongo db and accessing mortgage database
                    CustomerService customerService = new CustomerService("mortgage");
                    customerService.saveCustomer(customer);
                    customers.add(customer);

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
