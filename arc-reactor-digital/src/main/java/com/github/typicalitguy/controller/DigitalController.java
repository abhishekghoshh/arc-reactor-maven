package com.github.typicalitguy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DigitalController {
	@Value("${arc.reactor.space.owner.name}")
	private String ownerName;
	@Autowired
	private ServerProperties serverProperties;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/hello")
	public ResponseEntity<Map> hello() {
		Map map = new HashMap<>();
		map.put("msg", "Hello " + ownerName);
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		map.put("time", getCurrentIndianTime());
		map.put("port", serverProperties.getPort());
		return ResponseEntity.ok(map);
	}
	
	public static String getCurrentIndianTime() {
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return gmtDateFormat.format(new Date());
	}
}
