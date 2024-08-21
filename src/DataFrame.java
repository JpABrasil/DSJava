import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class DataFrame implements Serializable {
    public ArrayList<Cell> data = new ArrayList<>();
    public ArrayList<Column> columns = new ArrayList<>();
    public int NumberRows = 0;
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
                    if (ch == ',') {
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
                    if (ch == ',') {
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
                        NumberRows++;
                        row = new Row(rowIndex);
                    } else {
                        texto.append(ch);
                    }
                }
                intch = fr.read();
            }
            NumberRows--;
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
        System.out.println();
    }

    public DataFrame getColumns(String[] acolumns){
        DataFrame features = new DataFrame();
        ArrayList<String> columns = new ArrayList<>(Arrays.asList(acolumns));
        features.NumberRows = NumberRows;
        for(Cell cell: data){
            if(columns.contains(cell.Column.Name)){
                features.data.add(cell);
                if(!features.columns.contains(cell.Column)){
                    features.columns.add(cell.Column);
                }
            }
        }
        return features;
    }

    public ArrayList<Cell> getRow(int rowIndex){
        ArrayList<Cell> row = new ArrayList<>();
        for(Cell cell: data){
            if(cell.Row.index == rowIndex){
                row.add(cell);
            }
        }
        return row;
    }

    public int[] getSize(){
        return new int[]{NumberRows, columns.size()};
    }

}
