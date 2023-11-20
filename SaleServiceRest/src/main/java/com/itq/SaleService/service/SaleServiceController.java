package com.itq.SaleService.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itq.SaleService.business.SaleBusiness;
import com.itq.SaleService.dto.Ack;
import com.itq.SaleService.dto.Sale;

import jakarta.validation.Valid;

@RestController
@Validated
public class SaleServiceController {
	@Autowired
	private SaleBusiness saleBusiness;
	

	@PostMapping(value = "/sale", consumes = "application/json", produces = "application/json")
	public Ack createSale(@Valid @RequestBody Sale sale) {
		Ack ack = new Ack();
		if (saleBusiness.createSale(sale)) {
			ack.setCode(0);
			ack.setDescription("Sale created successfully");
		} else {
			ack.setCode(1);
			ack.setDescription("ERROR: Sale not created");
		}	
		return ack;
	}
	@GetMapping("/sale/{saleId}")
	public Sale getSale(@PathVariable("saleId") int saleId) {
		return saleBusiness.getSaleById(saleId);
	}
	
    @GetMapping(value = "/sales")
    public List<Sale> getAllSales() {
        return (List<Sale>) saleBusiness.getAllSales();
    }
    
    @DeleteMapping(value = "/sale/{saleId}", produces = "application/json")
    public Ack deleteSale(@PathVariable("saleId") int saleId) {
        Ack ack = new Ack();
        if (saleBusiness.deleteSale(saleId)) {
            ack.setCode(200);
            ack.setDescription("Sale deleted succesfully");
        } else {
            ack.setCode(400);
            ack.setDescription("Error deleting Sale");
        }
        return ack;
    }
}
