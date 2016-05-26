package de.mlo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.model.GameSize;

public interface GameSizeRepository extends JpaRepository<GameSize, Integer>{

}
