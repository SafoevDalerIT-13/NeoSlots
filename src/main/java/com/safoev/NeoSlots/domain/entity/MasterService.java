package com.safoev.NeoSlots.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "master_services", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"master_id", "service_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterService {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_services_seq_gen")
    @SequenceGenerator(name = "master_services_seq_gen", sequenceName = "master_services_id_seq", allocationSize = 1)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;
}
