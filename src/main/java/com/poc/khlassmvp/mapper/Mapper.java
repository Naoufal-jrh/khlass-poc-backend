package com.poc.khlassmvp.mapper;

public interface Mapper<T,L> {
    T toEntity(L dto);
    L toDto(T entity);
}
