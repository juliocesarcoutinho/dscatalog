package br.com.topsystem.dscatalog.repositories;

import br.com.topsystem.dscatalog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}
