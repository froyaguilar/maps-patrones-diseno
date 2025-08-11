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
 * Service responsible for planning routes using different strategies.
 * Implements the Context part of the Strategy pattern, dynamically selecting
 * the appropriate RouteStrategy based on the requested mode.
 */
@Service
public class RoutePlanner {

    private final ApplicationContext applicationContext;
    private Map<String, RouteStrategy> strategies;

    /**
     * Constructs a RoutePlanner with the Spring ApplicationContext.
     *
     * @param applicationContext The Spring ApplicationContext, used to retrieve all RouteStrategy beans.
     */
    @Autowired
    public RoutePlanner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Initializes the map of strategies after the bean has been constructed.
     * It collects all beans that implement RouteStrategy and maps them by their mode.
     * This avoids explicit if/else or switch statements for strategy selection.
     */
    @PostConstruct
    public void init() {
        strategies = applicationContext.getBeansOfType(RouteStrategy.class)
                .values()
                .stream()
                .collect(Collectors.toMap(RouteStrategy::mode, Function.identity()));
    }

    /**
     * Plans a route based on the provided request and selected transport mode.
     *
     * @param request The RouteRequest containing origin, destination, and desired mode.
     * @return A RouteResponse with the calculated route details.
     * @throws BusinessException if the requested transport mode is not supported.
     */
    public RouteResponse planRoute(RouteRequest request) {
        RouteStrategy strategy = strategies.get(request.mode().toUpperCase());
        if (strategy == null) {
            throw new BusinessException("Unsupported transport mode: " + request.mode());
        }
        return strategy.compute(request);
    }
}
