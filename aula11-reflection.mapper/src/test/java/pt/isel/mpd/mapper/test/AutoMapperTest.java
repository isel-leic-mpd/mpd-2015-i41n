package pt.isel.mpd.mapper.test;

/*
 * Copyright (C) 2015 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.mpd.mapper.AutoMapper;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class AutoMapperTest extends TestCase {
    
    public void test_mapping_properties_to_fields() throws Exception {
        Student s1 = new Student(76523, "Ze Batata", "Ferrugem das Varas");
        StudentDto s2 = AutoMapper.map(s1, StudentDto.class);
        
        Assert.assertEquals(s1.getNr(), s2.nr);
        Assert.assertEquals(s1.getName(), s2.name);
        Assert.assertEquals(s1.getCourse(), s2.course);
    }
}
