import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    public static void head(String path){
        DataFrame df = new DataFrame();
        df.readCsv(path);
        df.head();
    }
    public static void help(){


        String[][] comandos = {
                {"Comando","Argumentos Necessários","O que faz"},
                {"dataframe -new","Nome do Dataframe; Formato do Arquivo(csv,...); Caminho para o Dataframe","Cria um novo Dataframe que ficará salvo"}
        };

        for(String[] comando: comandos){
            System.out.printf("%-30s",comando[0]);
            System.out.printf("%-80s",comando[1]);
            System.out.printf("%-60s",comando[2]);
            System.out.println();
        }
    }
    public static void main(String[] args) {
        if(args[0].equals("help")) {
            help();
        }

    }
}