package car.service;


import car.entity.SecondHandCar;
import car.paramater.SecondHandCarParameter;
import car.utils.PageData;

import java.text.ParseException;


public interface SecondHandCarService {

	public Integer add(SecondHandCarParameter secondHandCarParameter);

	public Integer delete(String secondHandCarId);

	public Integer update(SecondHandCarParameter secondHandCarParameter);

	public SecondHandCarParameter selectSecondHandCarById(Integer secondHandCarId);

	public PageData<SecondHandCar> allSecondHandCar(SecondHandCarParameter secondHandCarParameter) throws ParseException;
}
