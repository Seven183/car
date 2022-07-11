package car.utils;

public enum SystemSuccess implements Rule {

    LOGIN_SUCCESS("100", "login_success"),
    LOGIN_OUT_SUCCESS("101", "login_out_success"),

    ADD_USER_SUCCESS("102", "add_user_success"),
    DELETE_USER_SUCCESS("103", "delete_user_success"),
    UPDATE_USER_SUCCESS("104", "update_user_success"),

    ADD_DRIVER_SUCCESS("105", "add_driver_success"),
    DELETE_DRIVER_SUCCESS("106", "delete_driver_success"),
    UPDATE_DRIVER_SUCCESS("107", "update_driver_success"),

    ADD_CAR_SUCCESS("108", "add_car_success"),
    DELETE_CAR_SUCCESS("109", "delete_car_success"),
    UPDATE_CAR_SUCCESS("110", "update_car_success"),

    ADD_CAR_REPAIR_SUCCESS("111", "add_car_repair_success"),
    DELETE_CAR_REPAIR_SUCCESS("112", "delete_car_repair_success"),
    UPDATE_CAR_REPAIR_SUCCESS("113", "update_car_repair_success"),

    ADD_MEMBER_SUCCESS("114", "add_member_success"),
    DELETE_MEMBER_SUCCESS("115", "delete_member_success"),
    UPDATE_MEMBER_SUCCESS("116", "update_member_success"),

    TOKEN_AUTH_SUCCESS("117", "token_auth_success"),

    ADD_INSURANCE_SUCCESS("118", "add_insurance_success"),
    DELETE_INSURANCE_SUCCESS("119", "delete_insurance_success"),
    UPDATE_INSURANCE_SUCCESS("120", "update_insurance_success"),

    ADD_SECONDHAND_CAR_SUCCESS("121", "add_secondhand_car_success"),
    DELETE_SECONDHAND_CAR_SUCCESS("122", "delete_secondhand_car_success"),
    UPDATE_SECONDHAND_CAR_SUCCESS("123", "update_secondhand_car_success");


    private final String code;
    private final String message;

    SystemSuccess(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
