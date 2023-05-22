package edu.miu.cs.cs544.service;

import org.mapstruct.MappingTarget;

public interface Converter<E, D> {
    D toDto(E entity);

    E fromDto(D dto);

    E fromDto(D dto, @MappingTarget E entity);
}

