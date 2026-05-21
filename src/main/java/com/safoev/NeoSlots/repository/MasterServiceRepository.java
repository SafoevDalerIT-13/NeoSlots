package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.MasterService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterServiceRepository extends JpaRepository<MasterService, UUID> {
    List<MasterService> findByMasterId(UUID masterId);
    List<MasterService> findByServiceId(UUID serviceId);
    Optional<MasterService> findByMasterIdAndServiceId(UUID masterId, UUID serviceId);
}
