package com.project.app.services.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateConverterTests {
 
    @Test
    public void test_DateConverter() throws Exception {

    	DateConverter dateConverter = new DateConverter();
 
//    	"/Date(1450559160000+0100)/"    --   1450559160
//    	"/Date(1450559220000+0100)/"    --   1450559220
//    	"/Date(1450559280000+0100)/"    --   1450559280
//    	"/Date(1450559340000+0100)/"    --   1450559340

        assertEquals("2015-12-19T21:06:00+00:00", dateConverter.convert("/Date(1450559160000+0100)/"));
        assertEquals("2015-12-19T21:07:00+00:00", dateConverter.convert("/Date(1450559220000+0100)/"));
        assertEquals("2015-12-19T21:08:00+00:00", dateConverter.convert("/Date(1450559280000+0100)/"));
        assertEquals("2015-12-19T21:09:00+00:00", dateConverter.convert("/Date(1450559340000+0100)/"));

    }

  
}

