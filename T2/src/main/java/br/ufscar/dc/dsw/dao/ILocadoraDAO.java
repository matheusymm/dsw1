package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locadora;

@SuppressWarnings("unchecked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long> {
    Locadora findById(long id);
    Locadora findByCNPJ(String cnpj);
    Locadora findByEmail(String email);
    List<Locadora> findAll();
    List<Locadora> findByCidade(String cidade);
    Locadora save(Locadora locadora);
    void deleteById(Long id);
    @Query("SELECT DISTINCT l.cidade FROM Locadora l")
    List<String> findCidades();
}
