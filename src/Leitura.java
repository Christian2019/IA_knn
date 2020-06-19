import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Leitura {
public static void load() {
	
	String path ="/iris.txt";
		
		int cont_line = 1;
		InputStream is = Leitura.class.getResourceAsStream(path);
		
			String singleLine = null;
			try {
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(is));
				try {
					while ((singleLine = reader.readLine()) != null) {
						
						if (cont_line > 1) {

						String[] dados = singleLine.split(",");
						double sepal_length = Double.parseDouble(dados[0]);
						double sepal_width = Double.parseDouble(dados[1]);
							double petal_length = Double.parseDouble(dados[2]);
							 double petal_width=Double.parseDouble(dados[3]);
							 String petal_class=dados[4];
							 Petala petala = new Petala(sepal_length,sepal_width,petal_length,petal_width,petal_class);
							Game.petalas.add(petala);
							
						}
						
						cont_line++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		
	}
}
