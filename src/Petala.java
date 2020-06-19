

public class Petala {
public double sepal_length,sepal_width,petal_length,petal_width;
public String petal_class;
public Petala(double sepal_length, double sepal_width, double petal_length, double petal_width, String petal_class) {
	
	this.sepal_length = sepal_length;
	this.sepal_width = sepal_width;
	this.petal_length = petal_length;
	this.petal_width = petal_width;
	this.petal_class = petal_class;
}
@Override
public String toString() {
	return "Petala [sepal_length=" + sepal_length + ", sepal_width=" + sepal_width + ", petal_length=" + petal_length
			+ ", petal_width=" + petal_width + ", petal_class=" + petal_class + "]\n";
}




}
