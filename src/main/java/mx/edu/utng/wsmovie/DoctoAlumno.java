package mx.edu.utng.wsmovie;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Alumno on 30/03/2017.
 */

public class DoctoAlumno implements KvmSerializable {
    private int id;
    private String dateTest;
    private String dateDevolution;
    private String note;
    private String activo;
    private String document;


    public DoctoAlumno(){}

    @Override
    public Object getProperty(int i) {

        switch (i){
            case 0:
                return  id;
          case 1:
                return  dateTest;
            case 2:
                return  dateDevolution;
            case 3:
                return  note;
            case 4:
                return  activo;
            case 5:
                return  document;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id=Integer.parseInt(o.toString());
                break;
            case 1:
                dateTest= o.toString();
                break;
            case 2:
            dateDevolution=o.toString();
                break;
            case 3:
                note=o.toString();
                break;
            case 4:
                activo=o.toString();
                break;
            case 5:
           document=o.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="id";
                break;
            case 1:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="dateTest";


                break;
            case 2:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="dateDevolution";

                break;
            case 3:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="note";


                break;
            case 4:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="activo";

                break;
            case 5:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="document";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "DoctoAlumno{" +
                "id=" + id +
                ", dateTest='" + dateTest + '\'' +
                ", dateDevolution='" + dateDevolution + '\'' +
                ", note='" + note + '\'' +
                ", activo='" + activo + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
