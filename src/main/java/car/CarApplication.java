package car;

import car.utils.PropertyUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarApplication {

    public static void main(String[] args) {
        PropertyUtils.init(args);
        SpringApplication.run(CarApplication.class, args);
    }
}
