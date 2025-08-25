package com.froy.navigator.controller;

/**
 * Constantes para las rutas de la API.
 * Centraliza los paths para facilitar su mantenimiento y reutilización.
 */
public final class ApiConstants {

    private ApiConstants() {
        // Clase de utilidad, no instanciable
    }

    public static final String ROUTES_BASE_PATH = "/api/v1/routes";
    public static final String GREETING_BASE_PATH = "/api/v1/greeting";
    public static final String PLAN_ROUTE_PATH = "/plan";
    public static final String MSJ_PATH = "/msj";
}