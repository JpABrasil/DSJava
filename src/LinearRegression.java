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
            Cell zeroCell = new Cell(0,tetaValues,currentRow);
            teta.data.add(zeroCell);
        }
    }

    public double MSE(){
        return 0.0;
    }
}
