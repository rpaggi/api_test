package br.com.alelo.api.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface IClientService<R, D> {
    public List<D> findAll();
    public D save(D filter) throws ParseException;
    public D update(D dto) throws ParseException;
    public D findById(Long id);
    void delete(Long id);
}
