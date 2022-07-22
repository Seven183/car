package car.service;


import car.entity.Advices;
import car.paramater.AdvicesParameter;
import car.utils.PageData;

import java.util.List;

public interface AdvicesService {

    public Advices selectAdvicesByCarsRepairNumber(String carsRepairNumber);

    public PageData<Advices> allAdvices(AdvicesParameter advicesParameter);

    public List<String> selectCarNumbers();

    public List<String> selectAdvicesType();

    public List<String> selectAdvicesName();
}
