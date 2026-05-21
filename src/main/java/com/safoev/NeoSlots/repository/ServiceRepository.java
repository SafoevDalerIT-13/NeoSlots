package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
    List<Service> findByCompanyId(UUID companyId);
    List<Service> findByCompanyIdAndIsActiveTrue(UUID companyId);
}
