package com.tutorial;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlgoExpert {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map questionMap = mapper.readValue(
				new File(System.getProperty("user.dir") + "\\src\\main\\resources\\algoexpert\\questionList.json"),
				Map.class);
		List<Map> questions = (List<Map>) questionMap.get("questions");
		Map<String, List<Map<String, String>>> filteredQuestion = new HashMap<>();
		questions.stream().forEach(question -> {
			Map<String, String> newQuestion = new ConcurrentHashMap<>();
			newQuestion.put("uid", (String) question.get("uid"));
			newQuestion.put("name", (String) question.get("name"));
			newQuestion.put("difficulty", "" + (int) question.get("difficulty"));

			String category = (String) question.get("category");
			if (!filteredQuestion.containsKey(category)) {
				filteredQuestion.put(category, new ArrayList<>());
			}
			filteredQuestion.get(category).add(newQuestion);
		});
		filteredQuestion.entrySet().parallelStream().forEach(entry -> {
			Collections.sort(entry.getValue(),
					(question1, question2) -> question1.get("difficulty").compareTo(question2.get("difficulty")));
		});
		System.out.print(mapper.writeValueAsString(filteredQuestion));
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "\\src\\main\\resources\\algoexpert\\questions.json"),
				filteredQuestion);
		mapper.writeValue(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\algoexpert\\topics.json"),
				filteredQuestion.keySet());
	}
}
