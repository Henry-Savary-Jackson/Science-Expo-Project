package science.expo.project.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {
    
    public static double e = Math.E;
    
    public static void skipLines(Scanner scanner, int n){
	for (int i = 1; i <= n; i++){
	    scanner.nextLine();
	}
    }
    
    public static void scatterToCSV(String name, String dir, ArrayList<double[]> dArr) {
	StringBuilder sb = new StringBuilder();
	sb.append('x').append(',').append('y').append("\n");
	for ( double[] sp : dArr){
	    sb.append(sp[0]).append(',').append(sp[1]).append("\n");
	}
	try (PrintWriter printer = new PrintWriter(dir+name)){
	    printer.write(sb.toString());
	    printer.close();
	}catch (FileNotFoundException fnfe){
	    fnfe.printStackTrace();
	}
    }
    
    public static double readDouble(String d){
	int eIndex = d.indexOf('e');
	
	try{
	    if (eIndex == -1){
		return Double.parseDouble(d);
	    
	    }else{
		double c = Double.parseDouble(d.substring(0, eIndex));
		double p = Double.parseDouble(d.substring(d.indexOf('-')+1));
		return c * Math.pow(e,p);
	    }
	}catch (NumberFormatException nfe){
	    nfe.printStackTrace();
	    return 0;
	}	
    }
    
}
