public class Polynomial {
	double [] coefficients;
	
	public Polynomial() {
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] arr) {
		coefficients  = arr;
	}
	
	public Polynomial add(Polynomial p) {
		int len1 = this.coefficients.length;
		int len2 = p.coefficients.length;
		int len = Math.max(len1, len2);
		Polynomial res = new Polynomial(new double[len]);
		for(int i = 0; i<len; i++) {
			if (i < len1) res.coefficients[i] += this.coefficients[i];
			if (i < len2) res.coefficients[i] += p.coefficients[i];
		}
		return res;
	}
	
	public double evaluate(double x) {
		double res = 0;
		int len = this.coefficients.length;
		for(int i = 0; i<len;i++) {
			res += this.coefficients[i] * Math.pow(x, i);
		}
		return res;
	}
	
	public boolean hasRoot(double d) {
		if (this.evaluate(d) == 0) return true;
		return false;
	}
	
}
