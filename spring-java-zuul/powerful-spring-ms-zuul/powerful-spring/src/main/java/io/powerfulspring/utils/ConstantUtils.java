package io.powerfulspring.utils;

public class ConstantUtils {
	private ConstantUtils() {

	}

	// Here i will define some regexp to validate string and digits
	public static final String CHAR_PATTERN = "[a-zA-Z\\s]+";
	public static final String ID_PATTERN = "[a-zA-Z0-9]+";
	public static final String CODE_PATTERN = "[0-9]{6}";
	public static final String MOBILE_PATTERN = "[0-9]{10}";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final Integer PAGINATION_SIZE = 8;

	public static final Integer MAX_CAPTCHA_TRIES = 3;

	public static final String SPRING_REST_URL = "http://localhost:8081/rest/books/";
}
