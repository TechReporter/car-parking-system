
package com.hexad.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.hexad.constants.ParkingLotConstants;
import com.hexad.model.Car;
import com.hexad.model.ParkingSlot;

/**
 * @author Tanmoy Dasgupta
 */
public class ParkingLotApplication {

	public static void main(String[] args) {

		Map<Integer, String> carParkingData = loadParkingLotData();
		issueTicketForCarParking(carParkingData);
	}
	
	public static void issueTicketForCarParking(Map<Integer, String> carParkingData) {
		Map<Integer, ParkingSlot> allocateCarParking = new TreeMap<Integer, ParkingSlot>();
		int slotSize=0;
		int temp=0;
		int emptySlot=0;
		
		for(Map.Entry<Integer, String> entry : carParkingData.entrySet()) {
			if(entry.getKey()==1) {
				slotSize = Integer.parseInt(entry.getValue().substring(19, 20));
				allocateParkingSlot(slotSize);
			}
			else if(entry.getKey()==1) {
				slotSize = Integer.parseInt(entry.getValue().substring(19, 20));
				allocateParkingSlot(slotSize);
			}
			else if(entry.getKey()>=2 && entry.getKey()<=7) {
				if(temp<slotSize) {
					String[] str = entry.getValue().split(" ");
					allocateCarParking.put(entry.getKey()-1, new ParkingSlot(temp, new Car(str[1], str[2])));
				}
				temp++;
				allocateSlotToCar(temp);
			}
			else if(entry.getKey()==8) {
				emptySlot = Integer.parseInt(entry.getValue().substring(6,7));
				removeCarFromParking(allocateCarParking,emptySlot);
			}
			else if(entry.getKey()==9) {
				getParkinglotStatus(allocateCarParking);
			}
			else if(entry.getKey()>=10 && entry.getKey()<=11) {
				parkNewCar(entry.getValue(),allocateCarParking,emptySlot,slotSize);
			}
			else if(entry.getKey()==12) {
				getAllRegistrationNumberWithColor(allocateCarParking,"white");
			}
			else if(entry.getKey()==13) {
				getAllSlotWithColor(allocateCarParking,"white");
			}
			else {
				String[] str = entry.getValue().split(" ");
				findSlotWithCarRegistrationNumber(allocateCarParking,str[1]);
			}
		}
	}

	public static Map<Integer, String> loadParkingLotData() {
		Map<Integer, String> carParkingData = new TreeMap<>();

		File file = new File(ParkingLotConstants.FILEPATH);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			int lines = 1;
			while(sc.hasNextLine()) {
				carParkingData.put(lines, sc.nextLine());
				lines++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return carParkingData;
	}
	
	public static void allocateParkingSlot(int parkingSlotSize) {
		System.out.println("Created Parking lot with "+parkingSlotSize+" slots");
	}
	
	public static void allocateSlotToCar(int allocateSlot) {
		System.out.println("Allocated slot number: "+allocateSlot);
	}
	
	public static void removeCarFromParking(Map<Integer,ParkingSlot> allocateCarParking, int emptySlot) {
		allocateCarParking.remove(emptySlot);
		System.out.println("slot number "+emptySlot+" is free");
	}
	
	public static void getParkinglotStatus(Map<Integer,ParkingSlot> allocateCarParking) {
		allocateCarParking.forEach((k,v) -> {
			System.out.println(k+"  "+v.getAllotedCar().getRegistrationNumber()+"  "+v.getAllotedCar().getColor());
		});
	}
	
	public static void parkNewCar(String newCar, Map<Integer, ParkingSlot> allocateCarParking, int emptySlot, int slotSize) {
		if(allocateCarParking.size()<slotSize) {
			String[] str = newCar.split(" ");
			allocateCarParking.put(emptySlot, new ParkingSlot(emptySlot, new Car(str[1], str[2])));
			System.out.println("Allocated slot number: "+emptySlot);
		}
		else {
			System.out.println(ParkingLotConstants.PARKING_FULL_MESSAGE);
		}
	}
	
	public static void getAllRegistrationNumberWithColor(Map<Integer, ParkingSlot> carMap, String color) {
		carMap.entrySet()
		.stream().filter(car -> car.getValue().getAllotedCar().getColor().equalsIgnoreCase(color)) 
		.forEach(e-> System.out.println(e.getValue().getAllotedCar().getRegistrationNumber()));
	}
	
	public static void getAllSlotWithColor(Map<Integer, ParkingSlot> carMap, String color) {
		carMap.entrySet()
		.stream().filter(car -> car.getValue().getAllotedCar().getColor().equalsIgnoreCase(color)) 
		.forEach(e-> System.out.println(e.getKey()));
	}
	
	public static void findSlotWithCarRegistrationNumber(Map<Integer, ParkingSlot> carMap, String regNum) {
		Set<Integer> keys = carMap.entrySet().stream()
							.filter(car -> car.getValue()
									.getAllotedCar().getRegistrationNumber().equalsIgnoreCase(regNum))
							.map(Map.Entry::getKey)
							.collect(Collectors.toCollection(HashSet::new));
							
		if(keys.size()>0) {
			System.out.println(keys);
		} else {
			System.out.println(ParkingLotConstants.CAR_NOT_AVAILABLE_MESSAGE);
		}
	}
	
	
	
	
	
	
	
	
	
	
}
