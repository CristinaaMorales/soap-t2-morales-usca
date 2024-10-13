package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.models.PagoModel;
import com.cibertec.edu.repositories.IPagosDao;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private IPagosDao pagoDao;

    @Override
    public List<PagoModel> findPagosByFecha(String fechaInicio, String fechaFin) {
        try {
            return this.pagoDao.findPagosByFechapagoBetween(fechaInicio, fechaFin);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PagoModel savePago(PagoModel pago) {
        try {
            if (pago != null) {
                return this.pagoDao.save(pago);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
