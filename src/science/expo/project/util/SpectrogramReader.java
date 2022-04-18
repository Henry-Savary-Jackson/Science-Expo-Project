package science.expo.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpectrogramReader {
    public File file;
    public FileInputStream inp;
    public Scanner scanner;
    
    public double tEcho1, tEcho2 ;
    
    public double[][] data ;
    
    public double tLength, fLength;
    
    public double t1,f1;
    
    public int nT, nF;
    
    public double dT, dF;
    
    public ArrayList<double[]> echoScatter1 = new ArrayList<>();
    public ArrayList<double[]> echoScatter2 = new ArrayList<>();
    
    public double validRange;
    
    public SpectrogramReader(URL filePath, double _tEcho1, double _tEcho2, double _validRange) throws FileNotFoundException,  URISyntaxException, IOException {
	readSpectrogramFile(filePath);
	tEcho1 = _tEcho1 - t1;
	tEcho2 = _tEcho2 - t1;
	validRange = _validRange;
	getScatterPoints(tEcho1,tEcho2);
	    
    }
    
    public void getScatterPoints(double _tEcho1, double _tEcho2){
	double lT1 = _tEcho1 ;
	double lT2 = _tEcho2 ;
	for(int f = nF-1; f >= 0; f -= 5){
	    System.out.println("ECHO1");
	    System.out.println("lT1: " + lT1);
	    int[] T1Range = mathUtils.windowsAroundPoint(data[f], lT1, dT, validRange);
	    double T1 = mathUtils.peakIndexInRange(Arrays.copyOfRange(data[f], T1Range[0], T1Range[1]+1));
	    lT1 = (T1Range[0] +T1)*dT;
	    echoScatter1.add(new double[]{  t1 +lT1  , f1 + (f * dF) , T1Range[0], T1Range[1] });
	    
	    System.out.println("ECHO2");
	    System.out.println("lT2: " + lT2);
	    int[] T2Range = mathUtils.windowsAroundPoint(data[f], lT2, dT, validRange);
	    double T2 = mathUtils.peakIndexInRange(Arrays.copyOfRange(data[f], T2Range[0], T2Range[1]+1));
	    lT2 = (T2Range[0] +T2)*dT ;
	    echoScatter2.add(new double[]{ t1 + lT2 ,  f1 + (f * dF) , T2Range[0], T2Range[1] }); 
	}
    }
    
    
    public void readSpectrogramFile(URL filePath) throws FileNotFoundException, URISyntaxException, IOException{
	
	file = new File(filePath.toURI());
	scanner = new Scanner(new FileInputStream(file));
	
	FileUtils.skipLines(scanner, 3);
	
	double xmin = Double.parseDouble(scanner.nextLine());
	double xmax = Double.parseDouble(scanner.nextLine());
	tLength = xmax-xmin;
	
	nT = Integer.parseInt(scanner.nextLine());
	dT = Double.parseDouble(scanner.nextLine());
	System.out.println("time of analysis: " + nT * dT);
	t1 = Double.parseDouble(scanner.nextLine());
	
	double ymin = Double.parseDouble(scanner.nextLine()); 
	double ymax = Double.parseDouble(scanner.nextLine());
	fLength = ymax- ymin;
	
	nF = Integer.parseInt(scanner.nextLine());
	dF = Double.parseDouble(scanner.nextLine());
	f1 = Double.parseDouble(scanner.nextLine());
	
	data = new double[nF][nT];
	
	for (int f = 0; f < nF; f++){
	    for(int t=0; t < nT; t++){
		data[f][t]= FileUtils.readDouble(scanner.nextLine()) ;
	    }
	}
    }
}
