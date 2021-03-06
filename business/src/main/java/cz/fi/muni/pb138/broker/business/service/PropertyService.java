package cz.fi.muni.pb138.broker.business.service;

import cz.fi.muni.pb138.broker.data.model.Property;

import java.util.List;
import java.util.Set;

/**
 * Interface of property service
 * @author by Martin
 */
public interface PropertyService {

    /**
     * Insert given property.
     *
     * @param property property to be inserted
     */
    void save(Property property);

    /**
     * Insert list of properties.
     *
     * @param properties list of properties to be inserted
     */
    void save(Set<Property> properties);

    /**
     * Delete property by given id.
     *
     * @param id identification of property to be deleted
     */
    void delete(Long id);

    /**
     * Delete all managed entities
     */
    void deleteAll();

    /**
     * Update given property.
     *
     * @param property property to be updated
     */
    void update(Property property);

    /**
     * Get property by given id.
     *
     * @param id identification of property to be found
     * @return found property
     */
    Property findOne(Long id);

    /**
     * Get all properties.
     *
     * @return list of all properties
     */
    List<Property> findAll();

    /**
     * Get all properties on address.district as given parameter.
     *
     * @param text name of district
     * @return return list of properties in given district/address
     */
    List<Property> findByAddressDistrictContainingIgnoreCase(String text);
}
