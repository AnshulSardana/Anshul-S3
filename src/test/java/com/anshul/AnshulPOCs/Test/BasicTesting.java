package com.anshul.AnshulPOCs.Test;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BasicTesting {

	@Mock
	HashMap<String, Integer> map;

	@BeforeAll
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test() {

		map.put("a", 1);

		Assertions.assertEquals(1, map.size());
	}
}
