package com.hangman.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * hangman singleton util class
 * @author lucas.luz
 *
 */
public class HangmanUtilSingleton {

	private static transient volatile HangmanUtilSingleton instance;
	
	private HangmanUtilSingleton() {}
	
	public static HangmanUtilSingleton getInstance() {
		
		if(instance == null) {
			synchronized (HangmanUtilSingleton.class) {
				if(instance == null) {
					instance = new HangmanUtilSingleton();
				}
			}
		}
		
		return instance;
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public synchronized <T> T getObectFromXMl(String path, Class<T> classObj) throws Exception {
		
		File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (T) unmarshaller.unmarshal(file);
	}

}
