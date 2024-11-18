package br.com.api_order.domain.entity;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class DomainEntity {

    private UUID id;
}
