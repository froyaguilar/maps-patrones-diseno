package com.froy.navigator.repository;

import com.froy.navigator.entity.RouteStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Interfaz de repositorio para gestionar entidades {@link RouteStats}.
 * Proporciona operaciones CRUD estándar y consultas nativas para necesidades estadísticas específicas.
 */
@Repository
public interface RouteStatsRepository extends JpaRepository<RouteStats, Long> {

    /**
     * Elimina todas las entradas de estadísticas de rutas anteriores a una fecha específica.
     * Ejemplo de una consulta nativa con una operación de modificación.
     *
     * @param dateTime Fecha y hora límite. Las entradas anteriores serán eliminadas.
     * @return Número de entidades eliminadas.
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM route_stats WHERE calculation_time < :dateTime")
    int deleteOldRouteStats(LocalDateTime dateTime);
}
