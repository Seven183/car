package car.utils;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PageParam implements Serializable {

    private static final long serialVersionUID = -5451178248526839929L;
    private int pageNum = 1;
    private int pageSize = 10;
}
