package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.cibertec.edu.models.PagoModel;

@Repository
public interface IPagosDao extends JpaRepository<PagoModel, Integer> {
    List<PagoModel> findPagosByFechapagoBetween(String fechaInicio, String fechaFin);
}
