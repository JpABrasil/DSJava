import java.io.*;
import java.util.Arrays;

public class Main{
    //Comando para ajuda
    public static void help(){
        System.out.print("Para mais ajuda: ");
        System.out.println("https://github.com/JpABrasil/DSJava\n");
        String[][] comandos = {
                {"Comando","Argumentos Necessários","O que faz"},
                {"dataframe -n","Nome do Dataframe; Formato do Arquivo(csv,...); Caminho para o Dataframe","Cria um novo Dataframe que ficará salvo"}
        };

        for(String[] comando: comandos){
            System.out.printf("%-30s",comando[0]);
            System.out.printf("%-80s",comando[1]);
            System.out.printf("%-60s",comando[2]);
            System.out.println();
        }
    }
    //Comando para criar novo DataFrame
    public static void n(String name, String type, String path){
        DataFrame df = new DataFrame();
        File diretorio = new File("DataFrames");
        if(type.equals("csv")){
            df.readCsv(path);
            df.head();
            if (!diretorio.exists()) {
                boolean criado = diretorio.mkdirs();
                if (!criado) {
                    System.out.println("Erro ao criar diretório");
                }
            }
            else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("DataFrames/" + name + ".obj"))) {
                    oos.writeObject(df);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.err.println("Tipo de Arquivo não suportado");
        }
    }
    //Comando para ver o head de um Dataframe
    public static void h(String name){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DataFrames/" + name + ".obj"))) {
            DataFrame df = (DataFrame) ois.readObject();
            df.head();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length != 0){
            if(args[0].equals("help")) {
                help();
            }
            else if(args[0].equals("dataframe")){
                if(args[1].equals("-n")){
                    n(args[2], args[3], args[4]);
                }
                else if (args[1].equals("-h")) {
                    h(args[2]);
                }
            }
        }
        else{
            DataFrame df = new DataFrame();
            df.readCsv("D:\\JOÃO\\JavaProjects\\DataScience\\out\\production\\DataScience\\username.csv");
            System.out.println(Arrays.toString(df.getSize()));
            String[] s = {"Username","Last name"};
            DataFrame X = df.getColumns(s);


        }


    }
}