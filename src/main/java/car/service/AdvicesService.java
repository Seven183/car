package car.service;


import car.entity.Advices;
import car.paramater.AdvicesParameter;
import car.utils.PageData;

public interface AdvicesService {

    public Advices selectAdvicesByCarsRepairNumber(String carsRepairNumber);

    public PageData<Advices> allAdvices(AdvicesParameter advicesParameter);
}
