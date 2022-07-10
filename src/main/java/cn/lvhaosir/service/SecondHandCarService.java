package cn.lvhaosir.service;


import cn.lvhaosir.entity.SecondHandCar;
import cn.lvhaosir.paramater.SecondHandCarParameter;
import cn.lvhaosir.utils.PageData;

import java.text.ParseException;


public interface SecondHandCarService {

	public Integer add(SecondHandCarParameter secondHandCarParameter);

	public Integer delete(String secondHandCarId);

	public Integer update(SecondHandCarParameter secondHandCarParameter);

	public SecondHandCarParameter selectSecondHandCarById(Integer secondHandCarId);

	public PageData<SecondHandCar> allSecondHandCar(SecondHandCarParameter secondHandCarParameter) throws ParseException;
}
