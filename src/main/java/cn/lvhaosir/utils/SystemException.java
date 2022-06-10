package cn.lvhaosir.utils;

public enum SystemException implements Rule {

    UNKNOWN_USER("0000", "no_user"),

    LOGIN_FAILED("000", "login_failed"),
    LOGIN_OUT_FAILED("001", "login_out_failed"),

    ADD_USER_FAILED("002", "add_user_failed"),
    DELETE_USER_FAILED("003", "delete_user_failed"),
    UPDATE_USER_FAILED("004", "update_user_failed"),

    ADD_DRIVER_FAILED("005", "add_driver_failed"),
    DELETE_DRIVER_FAILED("006", "delete_driver_failed"),
    UPDATE_DRIVER_FAILED("007", "update_driver_failed"),

    ADD_CAR_FAILED("008", "add_car_failed"),
    DELETE_CAR_FAILED("009", "delete_car_failed"),
    UPDATE_CAR_FAILED("010", "update_car_failed"),

    ADD_CAR_REPAIR_FAILED("011", "add_car_repair_failed"),
    DELETE_CAR_REPAIR_FAILED("012", "delete_car_repair_failed"),
    UPDATE_CAR_REPAIR_FAILED("013", "update_car_repair_failed"),

    ADD_MEMBER_FAILED("014", "add_member_failed"),
    DELETE_MEMBER_FAILED("015", "delete_member_failed"),
    UPDATE_MEMBER_FAILED("016", "update_member_failed"),

    TOKEN_AUTH_FAILED("017", "token_auth_failed");


    private final String code;
    private final String message;

    SystemException(String code, String message) {
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
