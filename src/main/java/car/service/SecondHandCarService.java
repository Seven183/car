package car.service;


import car.entity.SecondHandCar;
import car.paramater.SecondHandCarParameter;
import car.utils.PageData;

import java.text.ParseException;
import java.util.List;


public interface SecondHandCarService {

	public Integer add(SecondHandCarParameter secondHandCarParameter);

	public Integer delete(String secondHandCarId);

	public Integer update(SecondHandCarParameter secondHandCarParameter);

	public SecondHandCarParameter selectSecondHandCarById(Integer secondHandCarId);

	public PageData<SecondHandCar> allSecondHandCar(SecondHandCarParameter secondHandCarParameter) throws ParseException;

	public List<String> selectCarNumbers();

	public List<String> selectCarBrands();

	public List<String> selectBuyerPhones();

	public List<String> selectBuyerUsers();

	public List<String> selectBuyerIdCards();
}
