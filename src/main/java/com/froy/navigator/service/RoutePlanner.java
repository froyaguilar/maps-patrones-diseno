package com.froy.navigator.service;

import com.froy.navigator.dto.RouteRequest;
import com.froy.navigator.dto.RouteResponse;
import com.froy.navigator.exception.BusinessException;
import com.froy.navigator.strategy.RouteStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private Map<String, RouteStrategy> strategies;

    /**
     * Construye un RoutePlanner con el ApplicationContext de Spring.
     *
     * @param applicationContext Contexto de Spring utilizado para obtener todos los beans de RouteStrategy.
     */
    @Autowired
    public RoutePlanner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Inicializa el mapa de estrategias después de la creación del bean.
     * Recolecta todos los beans que implementan RouteStrategy y los mapea por su modo.
     * Esto evita utilizar sentencias if/else o switch para seleccionar la estrategia.
     */
    @PostConstruct
    public void init() {
        strategies = applicationContext.getBeansOfType(RouteStrategy.class)
                .values()
                .stream()
                .collect(Collectors.toMap(RouteStrategy::mode, Function.identity()));
    }

    /**
     * Planifica una ruta basada en la solicitud proporcionada y el modo de transporte seleccionado.
     *
     * @param request Objeto RouteRequest que contiene origen, destino y modo deseado.
     * @return RouteResponse con los detalles de la ruta calculada.
     * @throws BusinessException si el modo de transporte solicitado no está soportado.
     */
    public RouteResponse planRoute(RouteRequest request) {
        RouteStrategy strategy = strategies.get(request.mode().toUpperCase());
        if (strategy == null) {
            throw new BusinessException("Modo de transporte no soportado: " + request.mode());
        }
        return strategy.compute(request);
    }
}
