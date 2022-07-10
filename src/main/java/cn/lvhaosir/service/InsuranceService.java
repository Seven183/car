package cn.lvhaosir.service;


import cn.lvhaosir.entity.Insurance;
import cn.lvhaosir.paramater.InsuranceParameter;
import cn.lvhaosir.utils.PageData;

import java.text.ParseException;


public interface InsuranceService {

	public Integer add(InsuranceParameter insuranceParameter);

	public Integer delete(String InsuranceCode);

	public Integer update(InsuranceParameter insuranceParameter);

	public InsuranceParameter selectInsuranceByInsuranceCode(String InsuranceCode);

	public PageData<Insurance> allInsurance(InsuranceParameter insuranceParameter) throws ParseException;
}
