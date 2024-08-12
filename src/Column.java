import java.io.Serializable;
public class Column implements Serializable {
    public String Name;
    public Class<?> Type;
    public Column(String str){
        this.Name = str;
    }

}
