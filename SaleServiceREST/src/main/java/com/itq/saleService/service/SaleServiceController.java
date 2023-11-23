package com.itq.saleService.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itq.saleService.business.SaleBusiness;
import com.itq.saleService.dto.Ack;
import com.itq.saleService.dto.Sale;

@RestController
@Validated
public class SaleServiceController {
	@Autowired
	private SaleBusiness saleBusiness;
	

	@PostMapping(value = "/sale", consumes = "application/json", produces = "application/json")
	public Ack createSale(@Valid @RequestBody Sale sale) {
		Ack ack = new Ack();
		if (saleBusiness.createSale(sale)) {
			ack.setCode(200);
			ack.setDescription("Sale created successfully");
		} else {
			ack.setCode(400);
			ack.setDescription("ERROR: Sale not created");
		}	
		return ack;
	}
	@GetMapping("/sale/{saleId}")
	public Sale getSale(@PathVariable("saleId") int saleId) {
		return saleBusiness.getSaleById(saleId);
	}
	
    /*@GetMapping(value = "/sales")
    public List<Sale> getAllSales() {
        return (List<Sale>) saleBusiness.getAllSales();
    }*/
    @GetMapping(value = "/sales/category/{category}")
    public List<Sale> getSalesByCategory(@PathVariable("category") String category) {
        return (List<Sale>) saleBusiness.getSalesByCategory(category);
    }
    @GetMapping(value = "/sales/date/{date}")
    public List<Sale> getSalesByDate(@PathVariable("date") String date) {
        return (List<Sale>) saleBusiness.getSalesByDate(date);
    }
    @GetMapping(value = "/sales/clientId/{clientId}")
    public List<Sale> getSalesByCient(@PathVariable("clientId") int clientId) {
        return (List<Sale>) saleBusiness.getSalesByClient(clientId);
    }
    @GetMapping("/sales")
    public List<Sale> getSale(
        @RequestParam(value = "category", required = false) String category,
        @RequestParam(value = "date", required = false) String date,
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "providerId", required = false) String providerId,
        @RequestParam(value = "clientId", required = false) String clientId,
        @RequestParam(value = "productId", required = false) String productId) 
    {
        //change null values, avoiding null pointer exception
        if (category == null) {
            category = "";
        }
        if (date == null) {
            date = "";
        }
        if (status == null) {
            status = "";
        }
        if (providerId == null) {
            providerId = "";
        }
        if (clientId == null) {
            clientId = "";
        }
        if (productId == null) {
            productId = "";
        }
    
        return saleBusiness.getSalesFiltered(category, date, status, providerId, clientId, productId);
    }

    @PutMapping(value = "/sale", consumes = "application/json", produces = "application/json")
    public Ack updateSaleStatus(@RequestParam(value = "saleId", required = true) int saleId, @RequestParam(value = "status", required = true) String newStatus) {
        Ack ack = new Ack();
        if (saleBusiness.updateSaleStatus(saleId, newStatus)) {
            ack.setCode(200);
            ack.setDescription("Sale status updated successfully");
        } else {
            ack.setCode(400);
            ack.setDescription("Error updating sale status");
        }
        return ack;
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