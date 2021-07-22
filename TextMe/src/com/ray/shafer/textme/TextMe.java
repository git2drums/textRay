package com.ray.shafer.textme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class TextMe {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Send me a text ...");


		
		String message = "";
		String phone = "";
		String username = "";
		String password = "";
		String address = "";
		String fname = "";
		String newMessage = "";
		
		String[] ar;
		Properties prop = new Properties();

		
		try (InputStream input = new FileInputStream("C:\\Ray\\TextMe\\TextMe\\TextMe.properties")) {

            // load a properties file
            prop.load(input);

            // get the property value and assign to a variable
            message = prop.getProperty("message");
            phone = prop.getProperty("phone");
            username = prop.getProperty("username");
            address = prop.getProperty("address");
            password = prop.getProperty("password");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		// production file
		try(BufferedReader in = new BufferedReader(new FileReader("C:\\Ray\\TextMe\\TextMe\\textees_11102020.csv"))) {
			
		// Test File
//		try(BufferedReader in = new BufferedReader(new FileReader("C:\\Ray\\TextMe\\TextMe\\textees_test.csv"))) {

			String readLine;
		    while ((readLine = in.readLine()) != null) {
		    	ar=readLine.split(",");
		    	phone = ar[0];
		    	fname = ar[1];
		    	newMessage = "Hey " + fname + ", " + message;
				URL url = new URL(address + "?username=" + username + "&password=" + password +
						"&phone=" + phone + "&message=" + URLEncoder.encode(newMessage,"UTF-8"));
				System.out.println(url);

				// Send the text
				URLConnection uRLConnection = url.openConnection();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
				String inputLine;
				while ((inputLine = bufferedReader.readLine()) != null)
				{
					System.out.println(inputLine);
				}
				bufferedReader.close();
								
		    }
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
		System.out.println("THE END");	
		
	}

}
