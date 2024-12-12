package br.com.santander.natalino.sorteador;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;

public class Sorteador {

	public static void main(String[] args) {

		ArrayList<String> nomes = new ArrayList<>();
		nomes.add("Iara");//1
		nomes.add("Gilson");//2
		nomes.add("Erick");//3
		nomes.add("Ligia");//4
		nomes.add("Rafael");//5
		nomes.add("Beatriz");//6
		nomes.add("Cleusa");//7
		nomes.add("Gerson");//8
		nomes.add("Henrique");//9
		nomes.add("Fernanda");//10
		nomes.add("Thiago");//11
		nomes.add("Thayana");//12
		nomes.add("Giva");//12
		
		ArrayList<String> nomesEmbaralhados = embaralhador(nomes);
		int vezesEmbaralhadas = 0;
		boolean embaralhado = false;
		while(!embaralhado) {
			nomesEmbaralhados = embaralhador(nomes);
			int posicao = 0;
			embaralhado=true;
			for (String string : nomes) {
				if(string.equals(nomesEmbaralhados.get(posicao))) {
					embaralhado=false;
					vezesEmbaralhadas++;
				}else if((string.equals("Rafael")) && (nomesEmbaralhados.get(posicao).equals("Giva"))){
					embaralhado=false;
					vezesEmbaralhadas++;
				}else if((string.equals("Beatriz")) && (nomesEmbaralhados.get(posicao).equals("Ligia"))){
					embaralhado=false;
					vezesEmbaralhadas++;
				}else if((string.equals("Gerson")) && (nomesEmbaralhados.get(posicao).equals("Iara"))){
					embaralhado=false;
					vezesEmbaralhadas++;
				}else if((string.equals("Cleusa")) && (nomesEmbaralhados.get(posicao).equals("Iara"))){
					embaralhado=false;
					vezesEmbaralhadas++;
				};
				posicao++;
			}
		}
		
		int posicaoFinal=0;
		for (String nome : nomes) {
			String texto = "Olá, " + nome + ". Seu amigo secreto é : " + nomesEmbaralhados.get(posicaoFinal);
			String textoFinal = Base64.getEncoder().encodeToString(texto.getBytes());
			System.out.println(nome + " tirou " + textoFinal);
			posicaoFinal++;
		}
		
		System.out.println("vezes embaralhadas = " + vezesEmbaralhadas);
	}
	
	public static ArrayList<String> embaralhador(ArrayList<String> nomesLimpos) {
		ArrayList<String> nomesEmbaralhados = new ArrayList<>();
		
		for (String string : nomesLimpos) {
			nomesEmbaralhados.add(string);
		}
		Collections.shuffle(nomesEmbaralhados);
		return nomesEmbaralhados;
	}

}
