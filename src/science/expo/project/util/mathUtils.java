package science.expo.project.util;

import java.util.Arrays;

public class mathUtils {
    
    public static int[] windowsAroundPoint(double[] fBand ,double point, double dT, double range){
	double lOfRange = point - (range/4);
	double rOfRange = point + (range/2);
	int negRange = (int) Math.ceil((lOfRange)/dT) ;
	int posRange = (int) Math.ceil((rOfRange)/dT);
	return new int[]{negRange < 0? 0: negRange, posRange > fBand.length-1? fBand.length -1: posRange};
    }
    
    public static double peakIndexInRange(double[] windows){
	int index = 0;
	double iHighest = 0;
	
	for (int i = 0; i < windows.length ; i ++){
	    if (windows[i]> iHighest){
		iHighest = windows[i];
		index = i;
	    }
	}
	return index;
    }
    
}
