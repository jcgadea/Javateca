package app;

public class pr2 {

	public static void main(String[] args) {

		double lado1=22;
		double lado2=35;
		double lado3=24;
		
		double perimetro = lado1 + lado2 + lado3;
		double p = perimetro / 2;
		double area = Math.sqrt(p*(p-lado1)*(p-lado2)*(p-lado3));
		
		System.out.println();
		System.out.println("p: "+p);
		System.out.println("Area: "+area);
		System.out.println("Perimetro: "+perimetro);
		
	}

}
