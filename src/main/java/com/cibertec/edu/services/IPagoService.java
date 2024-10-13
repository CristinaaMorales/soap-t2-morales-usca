package com.cibertec.edu.services;

import java.util.List;

import com.cibertec.edu.models.PagoModel;

public interface IPagoService {

    // MÃ©todo para encontrar pagos por rango de fechas
    public List<PagoModel> findPagosByFecha(String fechaInicio, String fechaFin);

    // MÃ©todo para guardar o actualizar un pago
    public PagoModel savePago(PagoModel pago);
}
