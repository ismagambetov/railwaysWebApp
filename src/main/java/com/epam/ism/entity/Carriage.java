package com.epam.ism.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Carriage extends IdEntity {

    private List<Object[][]> sections = new ArrayList<>();
    private static final int SECTION_NUMBER = 9;
    private CarriageType type;
    private int placeNumber;

    public Carriage() {
        //This block creates places of the Carriage. Each carriage has
        //9 sections, and each section has 4 places. The number of place can be either odd or even.
        int k = 0;
        for (int i = 0; i < SECTION_NUMBER; i++) {
            Place[][] section = new Place[2][2];
            section[1][0] = new Place(++k,false); //odd
            section[0][0] = new Place(++k,false); //even
            section[1][1] = new Place(++k,false); //odd
            section[0][1] = new Place(++k,false); //even
            sections.add(section);
        }

    }

    public List<Object[][]> getSections() {
        return sections;
    }

    public void setSections(List<Object[][]> sections) {
        this.sections = sections;
    }

    public static int getSectionNumber() {
        return SECTION_NUMBER;
    }

    public CarriageType getType() {
        return type;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getFreePlace(Carriage carriage) {
        return 0;
    }

    public boolean takePlace(int place) {
        for (Object[][] section : sections) {
            for (int i = 0; i < section.length; i++) {
                for (int j = 0; j < section.length; j++) {
                    String sPlace = (String) section[i][j];
                    String[] split = sPlace.split("\\d");

                    if (split.length > 0) {
                        return false; // this place is already taken.
                    } else { //the current place is free yet.

                        // the place is taken now.
                        section[i][j] = sPlace+"+";
                        return true;
                    }


                }
            }
        }


        return false;
    }

    public class Place {
        private int placeNum;
        private boolean booked;

        public Place(int placeNum, boolean booked) {
            this.placeNum = placeNum;
            this.booked = booked;
        }

        public int getPlaceNum() {
            return placeNum;
        }

        public void setPlaceNum(int placeNum) {
            this.placeNum = placeNum;
        }

        public boolean isBooked() {
            return booked;
        }

        public void setBooked(boolean booked) {
            this.booked = booked;
        }
    }

    /**
     * Returns String representation of the Carriage's sections with its places.
     * @return String value.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Object[][] section : sections) {
            sb.append("Section №").append(i);
            sb.append("\n");
            sb.append("{");
            sb.append(section[0][0]);
            sb.append("}");
            sb.append("{");
            sb.append(section[0][1]);
            sb.append("}");
            sb.append("\n");
            sb.append("--------");
            sb.append(System.lineSeparator());
            sb.append("{");
            sb.append(section[1][0]);
            sb.append("}");
            sb.append("{");
            sb.append(section[1][1]);
            sb.append("}");
            sb.append("\n\n");
            i++;
        }

        return sb.toString();
    }
}
