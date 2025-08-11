package com.froy.navigator.repository;

import com.froy.navigator.entity.RouteStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Repository interface for managing {@link RouteStats} entities.
 * Provides standard CRUD operations and custom native queries for specific statistical needs.
 */
@Repository
public interface RouteStatsRepository extends JpaRepository<RouteStats, Long> {

    /**
     * Deletes all route statistics entries older than a specified date.
     * This is an example of a native query with a modifying operation.
     *
     * @param dateTime The threshold date and time. Entries older than this will be deleted.
     * @return The number of deleted entities.
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM route_stats WHERE calculation_time < :dateTime")
    int deleteOldRouteStats(LocalDateTime dateTime);
}
