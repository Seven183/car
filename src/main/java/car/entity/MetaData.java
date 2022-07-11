package car.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "metadata")
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String code;
    private String parent;
    private String value;
    private Integer sort;
    private Integer multiple;
}
