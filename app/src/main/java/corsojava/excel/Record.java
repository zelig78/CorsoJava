package corsojava.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private String tprNumPrev = "";
    private String veiTarga = "";
    private String tprTotImp = "";
    private String tprIDOff = "";
    private String offRASCL = "";
    private String offCATM1 = "";
    private String staDescr = "";
    private Cell tprDtPrev;
    private String fltDescrizione = "";
    private String SM = "";
    private String newState = "";
}
