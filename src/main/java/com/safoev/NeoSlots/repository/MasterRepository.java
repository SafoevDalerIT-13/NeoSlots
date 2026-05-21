package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MasterRepository extends JpaRepository<Master, UUID> {
    Optional<Master> findByUserId(UUID userId);
    List<Master> findByCompanyId(UUID companyId);
    List<Master> findByCompanyIdAndIsActiveTrue(UUID companyId);
}
