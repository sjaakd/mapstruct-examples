/**
 * Copyright 2012-2014 Gunnar Morling (http://www.gunnarmorling.de/) and/or other contributors as indicated by the
 *
 * @authors tag. See the copyright.txt file in the distribution for a full listing of all contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.mapstruct.example.mapper.reusable;

import java.util.List;
import javax.xml.bind.JAXBElement;
import net.opengis.gml.v_3_2_1.AbstractMetaDataType;
import net.opengis.gml.v_3_2_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_2_1.AbstractRingType;
import net.opengis.gml.v_3_2_1.AbstractSurfaceType;
import net.opengis.gml.v_3_2_1.AggregationType;
import net.opengis.gml.v_3_2_1.CodeType;
import net.opengis.gml.v_3_2_1.CodeWithAuthorityType;
import net.opengis.gml.v_3_2_1.CompositeSurfaceType;
import net.opengis.gml.v_3_2_1.CurvePropertyType;
import net.opengis.gml.v_3_2_1.CurveType;
import net.opengis.gml.v_3_2_1.DirectPositionListType;
import net.opengis.gml.v_3_2_1.DirectPositionType;
import net.opengis.gml.v_3_2_1.LinearRingType;
import net.opengis.gml.v_3_2_1.MetaDataPropertyType;
import net.opengis.gml.v_3_2_1.OrientableSurfaceType;
import net.opengis.gml.v_3_2_1.PolygonType;
import net.opengis.gml.v_3_2_1.ReferenceType;
import net.opengis.gml.v_3_2_1.RingType;
import net.opengis.gml.v_3_2_1.StringOrRefType;
import net.opengis.gml.v_3_2_1.SurfacePropertyType;
import net.opengis.gml.v_3_2_1.SurfaceType;
import net.opengis.gml.v_3_2_1.TinType;
import org.hisrc.w3c.xlink.v_1_0.TypeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class GeoToolkitMapper {

    GeoToolkitMapper MAPPER = Mappers.getMapper( GeoToolkitMapper.class );

    // multipolygon
    // surfaces

//    protected  org.geotoolkit.gml.xml.v321.SurfacePropertyType toSurfacePropertyType(SurfacePropertyType s) {
//        org.geotoolkit.gml.xml.v321.SurfacePropertyType surfacePropertyTypeMs = toSurfacePropertyTypeMs( s );
//        // the next does not work yet with nested: source = "s.abstractSurface.value" .. vague error message.
//        surfacePropertyTypeMs.setAbstractSurface( toAbstractSurfaceType( s.getAbstractSurface().getValue() ) );
//        return surfacePropertyTypeMs;
//    }


    @Mappings({
        @Mapping(target = "abstractSurface", ignore = true ),
        @Mapping(target = "type", source = "s.TYPE") // jaxb setter quirck
    })
    protected abstract org.geotoolkit.gml.xml.v321.SurfacePropertyType toSurfacePropertyType(SurfacePropertyType s);

    protected org.geotoolkit.gml.xml.v321.AbstractSurfaceType toAbstractSurfaceType(AbstractSurfaceType s) {
        if ( s instanceof TinType ) {
            return toTinType( (TinType) s );
        }
        else if ( s instanceof CompositeSurfaceType ) {
            return toCompositeSurface( (CompositeSurfaceType) s );
        }
        else if ( s instanceof OrientableSurfaceType ) {
            return toOrientableSurfaceType( (OrientableSurfaceType) s );
        }
        else if ( s instanceof PolygonType ) {
            return toPolygonType( (PolygonType) s );
        }
        else {
            return toSurfaceType( (SurfaceType) s );
        }
    }

    @Mappings({
        @Mapping(target = "alias", ignore = true),
        @Mapping(target = "complexes", ignore = true),
        @Mapping(target = "containedPrimitives", ignore = true),
        @Mapping(target = "containingPrimitives", ignore = true),
        @Mapping(target = "names", ignore = true),
        @Mapping(target = "identifiers", ignore = true),
        @Mapping(target = "identifierMap", ignore = true),
        @Mapping(target = "maximalComplex", ignore = true),
        @Mapping(target = "precision", ignore = true)
    })
    protected abstract org.geotoolkit.gml.xml.v321.CompositeSurfaceType toCompositeSurface(CompositeSurfaceType s);

    protected abstract org.geotoolkit.gml.xml.v321.OrientableSurfaceType toOrientableSurfaceType(OrientableSurfaceType s);

    protected abstract org.geotoolkit.gml.xml.v321.PolygonType toPolygonType(PolygonType s);

    protected abstract org.geotoolkit.gml.xml.v321.SurfaceType toSurfaceType(SurfaceType s);

    protected abstract org.geotoolkit.gml.xml.v321.TinType toTinType(TinType s);

    @Mappings({
        @Mapping(target = "abstractMetaData", ignore = true),
        @Mapping(target = "type", source = "s.TYPE") // jaxb setter quirck
    })
    protected abstract org.geotoolkit.gml.xml.v321.MetaDataPropertyType toMetaDataPropertyType(MetaDataPropertyType s);

    protected abstract org.geotoolkit.gml.xml.v321.AggregationType toCodeWithAuthorityType(AggregationType s);

    // ring types

    protected abstract org.geotoolkit.gml.xml.v321.AbstractRingPropertyType toAbstractRingPropertyType(AbstractRingPropertyType s);

    protected org.geotoolkit.gml.xml.v321.AbstractRingType toAbstractRingType(AbstractRingType s) {
        if ( s instanceof LinearRingType ) {
            return toLinearRingType( (LinearRingType) s );
        }
        else {
            return toRingType( (RingType) s );

        }
    }

    protected abstract org.geotoolkit.gml.xml.v321.LinearRingType toLinearRingType(LinearRingType s);

    protected abstract org.geotoolkit.gml.xml.v321.RingType toRingType(RingType s);

    protected abstract org.geotoolkit.gml.xml.v321.CurveType toRingType(CurveType s);

    protected abstract org.geotoolkit.gml.xml.v321.DirectPositionListType toDirectPositionListType(DirectPositionListType s);

    protected abstract org.geotoolkit.gml.xml.v321.DirectPositionType toDirectPositionType(DirectPositionType s);

    protected abstract org.geotoolkit.gml.xml.v321.CodeWithAuthorityType toCodeWithAuthorityType(CodeWithAuthorityType s);

    @Mapping(target = "type", source = "TYPE") // jaxb setter quirck in used library
    protected abstract org.geotoolkit.gml.xml.v321.ReferenceType toReferenceType(ReferenceType s);

    protected CodeType toCodeType(List<CodeType> in) {
        return in != null && !in.isEmpty() ? in.iterator().next() : null;
    }

    protected String toString(CodeType in) {
        return in != null ? in.getValue() : null;
    }

    protected String toString(StringOrRefType in) {
        return in != null ? in.getValue() : null;
    }

    protected String toType(TypeType in) {
        return in != null ? in.value() : null;
    }

}
