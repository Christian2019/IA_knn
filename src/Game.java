import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Game {
	public static ArrayList<Petala> petalas;
	
	static ArrayList<Petala> treino;
	static ArrayList<Petala> teste_resposta;
	static ArrayList<Petala> teste;
	static ArrayList<ArrayList<Type_Distance>> distancias;
	static int k=5;

	public static void main(String[] args) {
		petalas = new ArrayList<Petala>();
		treino = new ArrayList<Petala>();
		teste_resposta = new ArrayList<Petala>();
		teste = new ArrayList<Petala>();
		distancias = new ArrayList<ArrayList<Type_Distance>>();
		Random random = new Random();
		Leitura.load();
		// System.out.println(petalas);
		for (int i = 0; i < 40; i++) {
			int v = random.nextInt(petalas.size());
			treino.add(petalas.get(v));
			petalas.remove(v);

		}
		// System.out.println(treino);
		for (int i = 0; i < 30; i++) {
			int v = random.nextInt(petalas.size());
			Petala p = new Petala(petalas.get(i).sepal_length,petalas.get(i).sepal_width , petalas.get(i).petal_length, petalas.get(i).petal_width, petalas.get(i).petal_class);
			Petala p2= new Petala(petalas.get(i).sepal_length,petalas.get(i).sepal_width , petalas.get(i).petal_length, petalas.get(i).petal_width, petalas.get(i).petal_class);
			teste_resposta.add(p);
			teste.add(p2);
			petalas.remove(v);

		}
		// System.out.println(teste);
		for (int i = 0; i < teste.size(); i++) {
			teste.get(i).petal_class = null;
		}
		// System.out.println(teste);

		// k-NN
		for (int i = 0; i < teste.size(); i++) {
			ArrayList<Type_Distance> d = new ArrayList<Type_Distance>();
			for (int j = 0; j < treino.size(); j++) {
				// 1-teste 2-treino

				double v1 = teste.get(i).sepal_length;
				double v2 = treino.get(j).sepal_length;
				double x1 = teste.get(i).sepal_width;
				double x2 = treino.get(j).sepal_width;
				double y1 = teste.get(i).petal_length;
				double y2 = treino.get(j).petal_length;
				double z1 = teste.get(i).petal_width;
				double z2 = treino.get(j).petal_width;

				double cateto1 = v1 - v2;
				double cateto2 = x1 - x2;
				double cateto3 = y1 - y2;
				double cateto4 = z1 - z2;

				double distancia = Math
						.sqrt((cateto1 * cateto1) + (cateto2 * cateto2) + (cateto3 * cateto3) + (cateto4 * cateto4));

				Type_Distance td = new Type_Distance(treino.get(j).petal_class,distancia);
				d.add(td);
			}
			distancias.add(d);

		}
		for (int i = 0; i < distancias.size(); i++) {

			depth(distancias.get(i));

		}
/*
		for (int i = 0; i < distancias.size(); i++) {
			System.out.println("Teste "+i);
			for (int j=0;j<distancias.get(i).size();j++) {
				System.out.println(distancias.get(i).get(j));
			}

		}
*/
		ArrayList<String> resultado = new ArrayList<String>();
		for (int i=0;i<distancias.size();i++) {
		int setosa=0;
		int versicolor=0;
		int virginica=0;
		String resposta="empate";
		
		for (int j=0;j<k;j++) {
			if (distancias.get(i).get(j).type.equals("Iris-versicolor")) {
				versicolor++;
			}
			if (distancias.get(i).get(j).type.equals("Iris-virginica")) {
				virginica++;
			}
			if (distancias.get(i).get(j).type.equals("Iris-setosa")) {
				setosa++;
			}
			
		}
		if (setosa>=versicolor&&setosa>=virginica) {
			resposta = "Iris-setosa";
		}else if (versicolor>=setosa&&versicolor>=virginica) {
			resposta = "Iris-versicolor";
		}else if (virginica>=setosa&&virginica>=versicolor) {
			resposta = "Iris-virginica";
		}
		resultado.add(resposta);
		}
		int acertos=0;
		for (int i=0;i<teste_resposta.size();i++) {
			
			System.out.println("Teste "+i+" Tipo: "+teste_resposta.get(i).petal_class+" Resultado: "+resultado.get(i));
			if (teste_resposta.get(i).petal_class.equals(resultado.get(i))) {
			System.out.println("Correto");
			acertos++;
			}else {
				System.out.println("Errado");
			}
		}
		System.out.println("Total de acertos: "+acertos+"/"+teste_resposta.size());
		
		
	}

	public static void depth(ArrayList<Type_Distance> a) {
		// Crescente
	//	Collections.sort(a, listSorter);

		// Decrescente
		 Collections.sort(a, listSorter.reversed());

	}

	public static Comparator<Type_Distance> listSorter = new Comparator<Type_Distance>() {

		@Override
		public int compare(Type_Distance n0, Type_Distance n1) {
			if (n1.distance < n0.distance) {
				return -1;
			}
			if (n1.distance > n0.distance) {
				return +1;
			}
			return 0;
		}

	};
}
