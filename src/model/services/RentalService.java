package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	
	private Double pricePerHour;
	private Double pricePerDay;
	
	private BrasilTaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, BrasilTaxService taxservice) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxservice;
	}
	
	public void processInvoice(CarRental carRental) {
		
		double minuts = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minuts/60.0;
		
		double basicPayment ;
		
		if(hours <=12  ) {
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours/24.0);
		}
		
		double tax = taxService.tax(basicPayment); 
		
		carRental.setInvoice(new Invoice(basicPayment,tax));
	}
	
	
	
	
	
	
	
}
