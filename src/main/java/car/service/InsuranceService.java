package car.service;


import car.entity.Insurance;
import car.paramater.InsuranceParameter;
import car.utils.PageData;

import java.text.ParseException;


public interface InsuranceService {

	public Integer add(InsuranceParameter insuranceParameter);

	public Integer delete(String InsuranceCode);

	public Integer update(InsuranceParameter insuranceParameter);

	public InsuranceParameter selectInsuranceByInsuranceCode(String InsuranceCode);

	public PageData<Insurance> allInsurance(InsuranceParameter insuranceParameter) throws ParseException;
}
