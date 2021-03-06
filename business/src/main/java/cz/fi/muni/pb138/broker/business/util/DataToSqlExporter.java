package cz.fi.muni.pb138.broker.business.util;

import cz.fi.muni.pb138.broker.data.model.Property;

import java.io.*;
import java.util.Set;

/**
 * Class contains methods to create file, which contains sql insert statement of each property
 * @author Viki
 */
public class DataToSqlExporter {

    /**
     * Export properties into file, which contains sql insert statement of each property
     * @param propertyList
     * @throws Exception could not save property data into file
     */
    public void importData(Set<Property> propertyList) throws Exception {

        addCoordinatesToProperties(propertyList);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("data/src/main/resources/META-INF/sql/sql-insert.sql"), "utf-8"))) {
            for (Property property : propertyList) {
                String insertStatement = prepareInsert(property);
                writer.write(insertStatement);
                writer.write("\n");
            }
        } catch (IOException ex) {
            throw new IOException("Unable to write to file" + ex);
        }
    }

    /**
     * Create sql inset statement of property
     * @param property property
     * @return string sql insert statement of property
     */
    private String prepareInsert(Property property) {

        String area = property.getArea().toString();
        String price = property.getPrice().toString();

        String street = property.getAddress().getStreet();
        if (street == null) {
            street = "null";
        } else {
            street = "'" + street + "'";
        }

        String district = "'" + property.getAddress().getDistrict() + "'";
        String city = "'" + property.getAddress().getCity() + "'";

        String type;
        if (property.getType() == null) {
            type = "null";
        } else {
            type = "'" + property.getType().getText() + "'";
        }

        String streetXCoord = Double.toString(property.getCoords().getX());
        String streetYCoord = Double.toString(property.getCoords().getY());


        String insertStatement = "INSERT INTO Property (area, price, street, district, city, type, x, y) VALUES (" + area
                + ", " + price + ", " + street
                + ", " + district + ", " + city + ", "
                + type + ", " + streetXCoord + ", " + streetYCoord + ");";

        return insertStatement;
    }

    /**
     * Add coords to properties
     * @param properties set of properteis
     */
    private void addCoordinatesToProperties(Set<Property> properties) {
        CoordsEnquirer enquirer = new CoordsEnquirer();
        enquirer.addCoords(properties);
    }


}
