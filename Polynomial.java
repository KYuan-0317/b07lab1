import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


public class Polynomial {
	 double[] coefficients;
	 int[] exponents;
 
public Polynomial() {
	 coefficients = new double[0];
	 exponents = new int[0];
 }
 
 public Polynomial(double[] coefficients, int[] exponents) {
	  this.coefficients  = coefficients;
	  this.exponents = exponents;
 }
 
 public Polynomial removeRedundant() {
	 int len = this.exponents.length;
	 int[] exp = this.exponents;
	 double[] coe = this.coefficients;
	 for (int i = 0; i<len; i++) {
		 for (int j = 0; j<len; j++) {
			 if (i != j && exp[i] == exp[j] && coe[i] != 0 && coe[j] != 0) {
				 coe[i] += coe[j];
				 coe[j] = 0;
			 }
		 }
	 }
	int r_len = 0;
	for(int i = 0; i < len; i++) {
		if (coe[i] != 0)
			r_len += 1;
		}
	int[] r_exp = new int[r_len];
	int j = 0;
	double[] r_coe = new double[r_len];
	for(int i = 0; i < len; i++) {
		if (coe[i] != 0) {
			r_exp[j] = exp[i];
			r_coe[j] = coe[i];
			j++;
			}
		}
	return new Polynomial(r_coe, r_exp);
 }
 
 public Polynomial(File file) throws IOException {
	 Scanner scanner = new Scanner(file);
     String line = scanner.nextLine();
     scanner.close();
     String[] terms = line.split("(?=[+-])");
     if (terms.length >= 1 && terms[0].charAt(0) != '-')
    	 terms[0] = "+" + terms[0];
     String[] term;
     int len = terms.length;
     int[] exp = new int[len];
     double[] coe = new double[len];
     for (int i = 0; i < len; i++) {
    	 if (terms[i].charAt(1) == 'x') {
    		 term = terms[i].split("x");
    		 if (terms[i].charAt(0) == '+') {
    			 coe[i] = 1;
    			 if (term.length == 1) exp[i] = 1;
    			 else exp[i] = Integer.valueOf(term[1]);
    		 }
    		 else {
    			 coe[i] = -1;
    			 if (term.length == 1) exp[i] = 1;
    			 else exp[i] = Integer.valueOf(term[1]);
    		 }
    	 }
    	 else if (terms[i].contains("x")) {
    		 term = terms[i].split("x");
    		 if (term.length == 1) exp[i] = 1;
    		 else exp[i] = Integer.valueOf(term[1]);
    		 coe[i] = Double.valueOf(term[0]);
    		 }
    	 else {
    		 exp[i] = 0;
    		 coe[i] = Integer.valueOf(terms[i]);
    	 }
     }
     Polynomial p = (new Polynomial(coe, exp)).removeRedundant();
     this.coefficients = p.coefficients;
     this.exponents = p.exponents;
 }
 
public Polynomial add(Polynomial p) {
	int len1 = this.exponents.length;
	int len2 = p.exponents.length;
	int len = len1 + len2;
	int[] exp = new int[len];
	double[] coe = new double[len];
	for(int i = 0; i < len1; i++) {
		exp[i] = this.exponents[i];
		coe[i] = this.coefficients[i];
	}
	for(int i = len1; i < len; i++) {
		exp[i] = p.exponents[i-len1];
		coe[i] = p.coefficients[i-len1];
	} 
	Polynomial res = new Polynomial(coe, exp);
	return res.removeRedundant();
 }
 
 public double evaluate(double x) {
  double res = 0;
  int len = this.coefficients.length;
  for(int i = 0; i<len;i++) {
   res += this.coefficients[i] * Math.pow(x, this.exponents[i]);
  }
  return res;
 }
 
 public boolean hasRoot(double d) {
  if (this.evaluate(d) == 0) return true;
  return false;
 }
 

public Polynomial multiply(Polynomial p) {
	int[] exp1 = this.exponents;
	double[] coe1 = this.coefficients;
	int[] exp2 = p.exponents;
	double[] coe2 = p.coefficients;
	int len1 = exp1.length;
	int len2 = exp2.length;
	int len = len1 * len2;
	int[] r_exp = new int[len];
	double[] r_coe = new double[len];
	int a = 0;
	for (int i = 0; i < len1; i++) {
		for (int j = 0; j < len2; j++) {
			r_exp[a] = exp1[i] + exp2[j];
			r_coe[a] = coe1[i] * coe2[j];
			a++;
		}
	}
	return (new Polynomial(r_coe, r_exp)).removeRedundant();
}

public static String convert_str(double d, int i) {
	int d1 = (int)d;
	if (d == 1) {
		if (i == 0)
			return "1";
		else if (i == 1)
			return "x";
		else return "x" + Integer.toString(i);
	}
	else if (d == -1) {
		if (i == 0)
			return "-1";
		else if (i == 1)
			return "-x";
		else return "-x" + Integer.toString(i);
	}
	else if (d1 == d) {
		if (i == 0)
			return Integer.toString(d1);
		else if (i == 1)
			return Integer.toString(d1) + "x";
		else return Integer.toString(d1) + "x" + Integer.toString(i);
	}
	else {
		if (i == 0)
			return Double.toString(d);
		else if (i == 1)
			return Double.toString(d) + "x";
		else return Double.toString(d) + "x" + Integer.toString(i);
	}
}

public void saveToFile(String filename) throws IOException {
	File file = new File(filename);
	FileWriter writer = new FileWriter(file);
	int[] exp = this.exponents;
	double[] coe = this.coefficients;
	int len = exp.length;
	String line = "";
	for (int i = 0; i<len; i++) {
		if (i == 0)
			line += convert_str(coe[i], exp[i]);
		else {
			if (coe[i] > 0) line += "+" + convert_str(coe[i], exp[i]);
			else line += convert_str(coe[i], exp[i]);
			}
		}
	writer.write(line);
	writer.close();
	}

}
