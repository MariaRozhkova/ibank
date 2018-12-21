package com.gpsolutions.ibank.repository;

import com.gpsolutions.ibank.dbo.RoleDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDbo, Long> {
}