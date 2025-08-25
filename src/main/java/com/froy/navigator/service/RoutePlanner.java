package com.froy.navigator.service;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.entity.RouteStats;
import com.froy.navigator.exception.BusinessException;
import com.froy.navigator.model.TransportMode;
import com.froy.navigator.repository.RouteStatsRepository;
import com.froy.navigator.strategy.RouteStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Servicio responsable de planificar rutas utilizando diferentes estrategias.
 * Implementa la parte de Contexto del patrón Strategy, seleccionando dinámicamente
 * la RouteStrategy adecuada según el modo solicitado.
 */
@Service
public class RoutePlanner {

    private final ApplicationContext applicationContext;
    private final RouteStatsRepository routeStatsRepository;
    private Map<TransportMode, RouteStrategy> strategies;

    /**
     * Construye un RoutePlanner con el ApplicationContext de Spring.
     *
     * @param applicationContext Contexto de Spring utilizado para obtener todos los beans de RouteStrategy.
     * @param routeStatsRepository Repositorio para persistir las estadísticas de las rutas.
     */
    @Autowired
    public RoutePlanner(ApplicationContext applicationContext, RouteStatsRepository routeStatsRepository) {
        this.applicationContext = applicationContext;
        this.routeStatsRepository = routeStatsRepository;
    }

    /**
     * Inicializa el mapa de estrategias después de la creación del bean.
     * Recolecta todos los beans que implementan RouteStrategy y los mapea por su modo.
     * Esto evita utilizar sentencias if/else o switch para seleccionar la estrategia.
     */
    @PostConstruct
    public void init() {
        // Este bloque de código es el corazón del patrón Strategy en Spring.
        // 1. applicationContext.getBeansOfType(RouteStrategy.class):
        //    Le pedimos a Spring: "Dame todos los beans (componentes) que hayas creado
        //    que implementen la interfaz RouteStrategy".
        // 2. .values(): De los beans encontrados, solo nos interesan los objetos, no sus nombres.
        // 3. .stream(): Convertimos la lista de objetos en un Stream para procesarlos fácilmente.
        // 4. .collect(Collectors.toMap(...)): Creamos un nuevo Mapa a partir del Stream.
        //    - La LLAVE del mapa será el resultado de llamar a `getMode()` en cada estrategia (ej. TransportMode.CAR).
        //    - El VALOR del mapa será la propia estrategia (ej. la instancia de CarRouteStrategy).
        strategies = applicationContext.getBeansOfType(RouteStrategy.class)
                .values()
                .stream()
                .collect(Collectors.toMap(RouteStrategy::getMode, Function.identity()));
    }

    /**
     * Planifica una ruta basada en la solicitud proporcionada y el modo de transporte seleccionado.
     *
     * @param request Objeto RouteRequest que contiene origen, destino y modo deseado.
     * @return RouteResponse con los detalles de la ruta calculada.
     * @throws BusinessException si el modo de transporte solicitado no está soportado.
     */
    @Cacheable(value = "routes", key = "#request")
    public RouteResponse planRoute(RouteRequest request) {
        // 1. Seleccionar la estrategia adecuada según el modo de transporte.
        RouteStrategy strategy = strategies.get(request.mode());
        if (strategy == null) {
            throw new BusinessException("Modo de transporte no soportado: " + request.mode());
        }

        // 2. Ejecutar la lógica principal: calcular la ruta.
        RouteResponse response = strategy.compute(request);

        // 3. Implementación del patrón "Write-Through":
        //    Después de la operación principal (calcular la ruta), escribimos el resultado
        //    directamente en la base de datos de estadísticas.
        //    Esto asegura que el almacén de datos persistente siempre esté actualizado.
        saveRouteStatistics(request, response);

        // 4. Devolver la respuesta al cliente.
        return response;
    }

    /**
     * Guarda las estadísticas de una ruta calculada en la base de datos.
     *
     * @param request La solicitud original de la ruta.
     * @param response La respuesta calculada.
     */
    private void saveRouteStatistics(RouteRequest request, RouteResponse response) {
        RouteStats stats = new RouteStats(
                request.origin().lat(), request.origin().lon(),
                request.destination().lat(), request.destination().lon(),
                response.mode(), response.distanceKm(), response.durationMinutes()
        );
        routeStatsRepository.save(stats);
    }
}
