package slogo.controller;

import java.util.Map;

/**
 * Functional Interface used in the Controller for creating methods used
 * to retrieve data for display
 * @param <K>
 * @param <V>
 */
public interface MapGetter <K, V> {
  Map<K, V> getMap();
}
