import java.io.Serializable;
import java.util.ArrayList;

public class Column implements Serializable {
    public String Name;
    public Class<?> Type;
    public ArrayList<?> values;
    public Column(String str){
        this.Name = str;
    }


}
