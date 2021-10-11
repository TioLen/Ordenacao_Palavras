package ordenacao_palavras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
    Entrega do Trabalho 2 - Algoritmos e Programação II - [BJD 3º SEMESTRE]

    Eu,
    Gabriel da Silva Barros
    declaro que
    todas as respostas são fruto de meu próprio trabalho,
    não copiei respostas de colegas externos à equipe,
    não disponibilizei minhas respostas para colegas externos ao grupo e
    não realizei quaisquer outras atividades desonestas para me beneficiar ou
    prejudicar outros.
*/

public class Ordenacao_Palavras {
    
    public static int passos=0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        
        String firstNumber = "";
        
        FileReader lerArquivo = new FileReader ("file.txt");
        BufferedReader lerBufferizado = new BufferedReader (lerArquivo);
        
        while(true){
            String linha = lerBufferizado.readLine();
            if (linha == null)
            {
                break;
            }


            String [] vetorStrings = linha.split(" ");

            if (firstNumber == ""){
                firstNumber = vetorStrings[0];
            }
            
            else{
                mergesort(vetorStrings, 0, vetorStrings.length);
                
                for (int i = 0; i < vetorStrings.length; i++) {
                    System.out.print(vetorStrings[i].toLowerCase()+ " ");
                }
                System.out.println();
            }
        }
        System.out.println(passos + " passos");
    }
    
    //                                         | INICIO / FIM |
    public static void mergesort( String[] v,    int inicio, int fim ) {
        
        if (inicio < fim-1){
            int meio = (inicio+fim)/2;
            mergesort(v, inicio,meio);
            mergesort(v, meio,fim);
            intercalacao(v, inicio,meio,fim);
        }
    }
    
    public static void intercalacao(String[] v, int inicio, int meio, int fim) {
        String vFinal[] = new String[fim-inicio];
        
        int i=inicio; // NOVO INICIO -----> I
        int j=meio; // NOVO MEIO -----> J
        int k=0; // INDICE ---> acompanha o vFinal (vetor)
        
        while(i<meio && j<fim){
            passos++;
            
            // CRESCENTE - só inverter os < > para ficar descrescente
            
            if (v[i].length() < v[j].length())  // se [I] INICIO for maior q [J] MEIO
                vFinal[k++] = v[i++];
            
            else if (v[i].length() > v[j].length())
                vFinal[k++] = v[j++];
            
            
            else{    // se length das palavras sao iguais
                
                if (v[i].compareToIgnoreCase(v[j])<=0) // se i < j no alfabeto
                    vFinal[k++] = v[i++];
                else                                   // se j < i no alfabeto
                    vFinal[k++] = v[j++];
            }
        }      
        
        while (i<meio){
            passos++;
            vFinal[k++] = v[i++];
        }
        
        while (j<fim){
            passos++;
             vFinal[k++] = v[j++];
        }
        for (k=0, i=inicio; k < vFinal.length; k++, i++){
            v[i]=vFinal[k];
        }
        
    }
    

}