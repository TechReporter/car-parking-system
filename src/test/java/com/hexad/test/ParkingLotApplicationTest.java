package com.hexad.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.hexad.constants.ParkingLotConstants;
import com.hexad.main.ParkingLotApplication;
import com.hexad.model.Car;
import com.hexad.model.ParkingSlot;

/**
 * @author Tanmoy Dasgupta
 */
public class ParkingLotApplicationTest {

	private File file = null;
	private Scanner sc = null;
	Map<Integer, ParkingSlot> allocateCarParking = new TreeMap<Integer, ParkingSlot>();
	Map<Integer, String> carParkingData = new TreeMap<>();


	@Before
	public void setUp() throws Exception {

		File file = new File(ParkingLotConstants.FILEPATH);
		sc = new Scanner(file);
		int lines = 1;
		while(sc.hasNextLine()) {
			carParkingData.put(lines, sc.nextLine());
			lines++;
		}
		for(int i=0; i<6; i++) {
			allocateCarParking.put(i, new ParkingSlot(i, new Car("test_registration"+i, "test_color"+i)));
		}
	}

	@Test
	public void loadParkingLotDataTest() throws IOException {

		try {
			assertEquals("test data load from file", ParkingLotApplication.loadParkingLotData(),carParkingData);
		} finally {
			if(sc !=null) {
				sc.close();
			}
		}	
	}


	@Test
	public void removeCarParkingTest() {
		ParkingLotApplication.removeCarFromParking(allocateCarParking, 4);
		assertEquals("test allocated slot size after a car exit", allocateCarParking.size(),5);
	}

}
