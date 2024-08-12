public class Cell {
    public Class<?> type;
    public Object value;
    public Column Column;
    public Row Row;
    public Cell(Object valor,Column column,Row row){
        this.value = valor;
        this.type = valor.getClass();
        this.Column = column;
        this.Row = row;
    }
}
