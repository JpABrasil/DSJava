
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;

public class DataFrame {
    public ArrayList<Cell> data = new ArrayList<>();
    public ArrayList<Column> columns = new ArrayList<>();
    public DataFrame(){

    }
    public void readCsv(String caminhoArquivo){
        File arquivo = new File(caminhoArquivo);
        try( FileReader fr = new FileReader(arquivo)){
            int intch = fr.read();
            int rowIndex = 1;
            int columnIndex = 0;
            StringBuilder texto = new StringBuilder();
            Row row = new Row(rowIndex);
            boolean firstline = true;
            while(intch != -1) {
                char ch = (char) intch;
                if(firstline){
                    if (ch == ';') {
                        Column column = new Column(texto.toString());
                        columns.add(column);
                        texto = new StringBuilder();

                    } else if (ch == '\n') {
                        Column column = new Column(texto.toString());
                        columns.add(column);
                        texto = new StringBuilder();
                        firstline = false;
                    } else {
                        texto.append(ch);
                    }
                }else{
                    if (ch == ';') {
                        Cell cell = new Cell(texto.toString(), columns.get(columnIndex),row);
                        data.add(cell);
                        columnIndex++;
                        texto = new StringBuilder();

                    } else if (ch == '\n') {
                        Cell cell = new Cell(texto.toString(), columns.get(columnIndex),row);
                        data.add(cell);
                        texto = new StringBuilder();
                        columnIndex = 0;
                        rowIndex++;
                        row = new Row(rowIndex);
                    } else {
                        texto.append(ch);
                    }
                }
                intch = fr.read();
            }
            data.removeLast();
        }catch(IOException e){
            System.err.println("Arquivo não encontrado: " + caminhoArquivo);
        }

    }

    public void head() {
        System.out.printf("%-10s"," ");
        for (Column column : columns) {
            System.out.printf("%-15s", column.Name);
        }
        System.out.println();
        boolean indexPrinted = false;
        int cellPosition = 1;
        for (Cell cell : data) {
            if (!indexPrinted) {
                System.out.printf("%-10d", cell.Row.index);
                indexPrinted = true;
            }
            if (cellPosition < columns.size()) {
                System.out.printf("%-15s", cell.value);
                cellPosition++;
            } else if (cellPosition == columns.size()) {
                System.out.printf("%-15s", cell.value);
                cellPosition = 1;
                System.out.println();
                indexPrinted = false;
            }
        }
    }
}
