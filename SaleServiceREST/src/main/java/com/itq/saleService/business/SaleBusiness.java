package com.itq.saleService.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itq.saleService.dao.SaleDao;
import com.itq.saleService.dto.Sale;
import com.itq.saleService.service.CustomSaleException;

@Service
public class SaleBusiness {
	 private static final Logger LOGGER = LoggerFactory.getLogger(SaleBusiness.class);
	 @Autowired
	 private SaleDao saleDao;
	 public boolean createSale(Sale sale) {
		 return saleDao.createSale(sale);
	 }
	 
	 public Sale getSaleById(int saleId) {
        return saleDao.getSaleById(saleId);
     }
     public List<Sale> getAllSales() {
	 	return (List<Sale>) saleDao.getAllSales();
	 }
     public List<Sale> getSalesByCategory(String category) {
	 	return (List<Sale>) saleDao.getSaleByCategory(category);
	 }
     public List<Sale> getSalesByClient(int client) {
 	 	return (List<Sale>) saleDao.getSaleByClient(client);
 	 }
     public List<Sale> getSalesByDate(String date) {
 	 	return (List<Sale>) saleDao.getSaleByDate(date);
 	 }
     public boolean updateSaleStatus(int userId, String newStatus) {
        return saleDao.updateSaleStatus(userId, newStatus);
     }

     public boolean deleteSale(int saleId) {
        return saleDao.deleteSale(saleId);
     }

	public List<Sale> getSalesFiltered(String category, String date, String status, String providerId, String clientId, String productId) {
		List<Sale> sales = saleDao.getSalesFiltered(category, date, status,providerId,clientId,productId);
		if (sales.isEmpty()) {
            String errorMessage = "Error 404. There are no sales.";
            LOGGER.error(errorMessage);
            throw new CustomSaleException(errorMessage);
        }
		return sales;
	}


}
