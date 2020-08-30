package io.powerfulspring.utils;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

public class MethodUtils {
	private MethodUtils() {

	}

	public static void pageModel(Model model, Page<?> pages) {
		int current = pages.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 5, pages.getTotalPages());

		model.addAttribute("end", end);
		model.addAttribute("begin", begin);
		model.addAttribute("current", current);
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
	}

	public static String convertString(String text) {
		String formattedText = "";
		for (Character character : text.toCharArray()) {
			if (Character.isUpperCase(character)) {
				formattedText = formattedText + " " + character;
			} else {
				formattedText = formattedText + character;
			}
			formattedText = formattedText.substring(0, 1).toUpperCase()
					+ formattedText.substring(1, formattedText.length());
		}
		return formattedText.trim();
	}

	public static User findLoggedInUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static List<String> addressTypes() {
		return Arrays.asList("Permanent Address", "Office Address", "Temporary Address");
	}

	public static String prepareErrorJSON(HttpStatus status, Exception ex) {
		JSONObject errorJSON = new JSONObject();
		try {
			errorJSON.put("status", status.value());
			errorJSON.put("error", status.getReasonPhrase());
			errorJSON.put("message", ex.getMessage().split(":")[0]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return errorJSON.toString();
	}
}
