import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class Driver {
	public static void testAdd() {
	    double[] coe1 = {2, 1, 5};// 2 + x + 5x^3
	    int[] exp1 = {0, 1, 3};
	    Polynomial p1 = new Polynomial(coe1, exp1);

	    double[] coe2 = {4, 5, 11, -1}; // 4 + 5x^3 + 11x^2 - x
	    int[] exp2 = {0, 3, 2, 1};
	    Polynomial p2 = new Polynomial(coe2, exp2);

	    Polynomial p = p1.add(p2); // Expected: 6 + 10x^3 + 11x^2

	    System.out.println("---test add 2+x+5x^3 and 4+5x^3+11x^2-x");
	    System.out.println("Supposed to get: ");
	    System.out.println("       Coefficients: [6.0, 10.0, 11.0] Exponents: [0, 3, 2]");
	    System.out.println("Get: ");
	    System.out.print("       Coefficients: " + Arrays.toString(p.coefficients));
	    System.out.println(" Exponents: " + Arrays.toString(p.exponents));
	    }

	public static void testMultiply() {
	    double[] coe1 = {1, -2, 3}; // x^2 - 2 + 3x
	    int[] exp1 = {2, 0, 1};
	    Polynomial p1 = new Polynomial(coe1, exp1);

	    double[] coe2 = {10, 4, 5, 1}; // 10x^3 + 4 + 5x + x^4
	    int[] exp2 = {3, 0, 1, 4};
	    Polynomial p2 = new Polynomial(coe2, exp2);

	    Polynomial p = p1.multiply(p2); // Expected: 8 + 22x + 15x^2

	    System.out.println("---test multiply 2+x+5x^3 and (4+5x^3+11x^2-x");
	    System.out.println("Supposed to get: ");
	    System.out.println("       Coefficients: [13.0, 19.0, -15.0, 1.0, -8.0, 2.0, 28.0] Exponents: [5, 2, 3, 6, 0, 1, 4]");
	    System.out.println("Get: ");
	    System.out.print("       Coefficients: " + Arrays.toString(p.coefficients));
	    System.out.println(" Exponents: " + Arrays.toString(p.exponents));
	}

    public static void testevaluate() {
        double[] coe = {2, 3}; // 2 + 3x
        int[] exp = {0, 1};
        Polynomial p = new Polynomial(coe, exp);

        double res = p.evaluate(2); // Expected: 2 + 6 = 8
        
        System.out.println("---test evaluate 2 + 3x for x = 2");
	    System.out.print("Supposed to get: ");
	    System.out.println("8.0");
	    System.out.print("         Get:    ");
	    System.out.println(res);
    }

    public static void testHasRoot() {
        double[] coe = {1, -3, 2};
        int[] exp = {2, 1, 0};
        Polynomial p = new Polynomial(coe, exp);
        
        System.out.println("---test HasRoot x^2 - 3x + 2");
        System.out.println("Supposed to get: ");
        System.out.println("       hasRoot(1) == true| hasRoot(2) == true| hasRoot(3) == false| hasRoot(0) == false");
        System.out.println("Get: ");
        System.out.print("       hasRoot(1) == " + p.hasRoot(1));
        System.out.print("| hasRoot(2) == " + p.hasRoot(2));
        System.out.print("| hasRoot(3) == " + p.hasRoot(3));
        System.out.println("| hasRoot(0) == " + p.hasRoot(0));
    }

    public static void testfileConstructor() {
        try {
        	File testFile = new File("test_polynomial.txt");
            FileWriter writer = new FileWriter(testFile);
            writer.write("x3+x-x2+1+5x6");
            writer.close();

            Polynomial p = new Polynomial(testFile);
            testFile.delete();
            
            System.out.println("---test fileConstructor x3+x-x2+1+5x6");
            System.out.println("Supposed to get: ");
            System.out.println("       Coefficients: [1.0, 1.0, -1.0, 1.0, 5.0] Exponents: [3, 1, 2, 0, 6]");
            System.out.println("Get: ");
            System.out.print("       Coefficients: " + Arrays.toString(p.coefficients));
            System.out.println(" Exponents: " + Arrays.toString(p.exponents));
            
        } catch (IOException e) {
            System.out.println("fileConstructor Test Failed: " + e.getMessage());
        }
    }
    
    public static void testsaveToFile() {
        try {
         double[] coeffs = {1, -7, -1, 3};
            int[] exps = {1, 0, 2, 3};
            
            Polynomial p = new Polynomial(coeffs, exps);
            
            String s ="test";
            p.saveToFile(s);
            File testfile = new File(s);
            
            Scanner scanner = new Scanner(testfile);
            String line = scanner.nextLine();
            scanner.close();
            testfile.delete();
            
            System.out.println("---test saveToFile x-7-x2+3x3");
            System.out.println("Supposed to get: ");
            System.out.println("       x-7-x2+3x3");
            System.out.println("Get: ");
            System.out.print("       " + line);
            
        } catch (IOException e) {
            System.out.println("saveToFile Test Failed: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) throws IOException {
		testAdd();
		System.out.println("\n");
		testMultiply();
		System.out.println("\n");
		testevaluate();
		System.out.println("\n");
		testHasRoot();
		System.out.println("\n");
		testfileConstructor();
		System.out.println("\n");
		testsaveToFile();
	}
}
