package com.reactor.arc.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FileService {

	private String currentDir = System.getProperty("user.dir");
	protected ObjectMapper mapper = new ObjectMapper();

	public abstract Object initializeFromFile(String path, Class<?> className, String fileName)
			throws FileNotFoundException, IOException;

	public Object initializeFromFile(String path, Class<?> className, String fileName, Object defaultValue) {
		try {
			return initializeFromFile(path, className, fileName);
		} catch (IOException e) {
			return defaultValue;
		}
	}

	protected String getCurrentDirectory() {
		return currentDir;
	}

	protected BufferedReader getBufferedReader(String path, String fileName) throws FileNotFoundException {
		Path filePath = Paths.get(getCurrentDirectory(), path, fileName);
		return new BufferedReader(new FileReader(filePath.toString()));
	}

}
