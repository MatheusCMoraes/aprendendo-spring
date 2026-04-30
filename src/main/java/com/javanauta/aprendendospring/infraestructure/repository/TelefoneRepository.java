package com.javanauta.aprendendospring.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javanauta.aprendendospring.infraestructure.entity.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
