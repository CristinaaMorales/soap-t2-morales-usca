package com.cibertec.edu.endpoints;

import com.cibertec.edu.models.PagoModel;
import com.cibertec.edu.pagos.*;
import com.cibertec.edu.services.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class PagosIndicator {

    @Autowired
    private IPagoService pagoService;

    private static final String NAMESPACE = "http://www.edu.cibertec.com/pagos";

    // Crear un pago
    @PayloadRoot(namespace = NAMESPACE, localPart = "CreacionPagoRequest")
    @ResponsePayload
    public CreacionPagoResponse createPago(@RequestPayload CreacionPagoRequest request) {
        PagoModel modelo = new PagoModel();
        modelo.setFechapago(request.getFechaPago() != null && !request.getFechaPago().isEmpty() ? request.getFechaPago() : "");
        modelo.setMontototal(request.getMontoTotal()); // Ya es un double, no requiere verificaciÃ³n de String vacÃ­o
        modelo.setMoneda(request.getMoneda() != null && !request.getMoneda().isEmpty() ? request.getMoneda() : "");

        modelo = this.pagoService.savePago(modelo);

        CreacionPagoResponse response = new CreacionPagoResponse();
        if (modelo != null && modelo.getIdpago() != 0) {
            response.setEstado("Ã‰xito");
            response.setMensaje("Pago registrado correctamente con ID: " + modelo.getIdpago());
        } else {
            response.setEstado("Error");
            response.setMensaje("Hubo un problema al registrar el pago.");
        }
        return response;
    }

    // Listar pagos
    @PayloadRoot(namespace = NAMESPACE, localPart = "ListaPagosRequest")
    @ResponsePayload
    public ListaPagosResponse listaPagos(@RequestPayload ListaPagosRequest request) {
        ListaPagosResponse response = new ListaPagosResponse();
        List<PagoModel> lista = this.pagoService.findPagosByFecha(request.getFechaInicio(), request.getFechaFin());
        List<PagoObject> listaResponse = new ArrayList<>();

        if (!lista.isEmpty()) {
            lista.forEach(obj -> {
                PagoObject pago = new PagoObject();
                pago.setIdPago(obj.getIdpago());
                pago.setFechaPago(obj.getFechapago());
                pago.setMontoTotal(obj.getMontototal());
                pago.setMoneda(obj.getMoneda());
                listaResponse.add(pago);
            });
        }
        response.setListaPagos(listaResponse);
        return response;
    }

}
