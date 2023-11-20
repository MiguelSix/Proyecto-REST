package com.itq.SaleService.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itq.SaleService.dao.SaleDao;
import com.itq.SaleService.dto.Sale;

@Service
public class SaleBusiness {
	 @Autowired
	 private SaleDao saleDao;
	 public boolean createSale(Sale sale) {
		 return saleDao.createSale(sale);
	 }
	 
	 public Sale getSaleById(int saleId) {
        return saleDao.getSaleById(saleId);
     }

     public boolean updateSaleStatus(int userId, String newStatus) {
        return saleDao.updateSaleStatus(userId, newStatus);
     }

     public boolean deleteSale(int saleId) {
        return saleDao.deleteSale(saleId);
     }

     public List<Sale> getAllSales() {
	 	return (List<Sale>) saleDao.getAllSales();
	 }
}
