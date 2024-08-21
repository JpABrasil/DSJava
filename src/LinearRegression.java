import java.util.ArrayList;

public class LinearRegression {
    public DataFrame X;
    public DataFrame Y;
    public DataFrame teta;
    public LinearRegression(DataFrame X, DataFrame Y) {
        this.X = X;
        this.Y = Y;
        this.teta = new DataFrame();
        Column tetaValues = new Column("Teta Values");
        int columnsX = X.getSize()[1];
        teta.columns = new ArrayList<>();
        teta.columns.add(tetaValues);
        for(int i =0;i < columnsX;i++){
            Row currentRow = new Row(i+1);
            Cell zeroCell = new Cell(0.0,tetaValues,currentRow);
            teta.data.add(zeroCell);
        }
    }
    public void train(double learningRate, int batchs){
        int k = 0;
        Column tetaValues = new Column("Teta Values");
        while(k < batchs){
            double errors = 0.0;
            for (int i = 1; i < X.NumberRows + 1; i++) {
                double predictY = trainPredict(i);
                double error = trainError(predictY, i);
                errors += Math.abs(error);
                for(int j = 0; j < X.getRow(i).size();j++){
                   double xij = Double.parseDouble((String)X.getRow(i).get(j).value);
                   Cell tetaj = teta.data.get(j);
                   double gradient = error * xij;
                   double updatedTetaj =(double) tetaj.value - learningRate * gradient;
                   tetaj.value = updatedTetaj;
                }
                for(Cell cell:this.teta.data){
                    System.out.print(cell.value + " ");
                }
                System.out.println();
            }
            //System.out.println("Batch: " + (k+1) + " Erro Médio: " + errors/X.NumberRows);
            k = k + 1;
        }
    }
    public double trainPredict(int rowIndex){
        ArrayList<Cell> row = X.getRow(rowIndex);
        ArrayList<Cell> tetaValues = teta.data;
        double y = 0;
        for(int i = 0;i < row.size();i++){
            y += Double.parseDouble((String) row.get(i).value)  * (double) tetaValues.get(i).value;
        }
        return y;
    }
    public double trainError(double prediction,int rowIndex){
        ArrayList<Cell> row = Y.getRow(rowIndex);
        return prediction - Double.parseDouble((String) row.getFirst().value);
    }

}
