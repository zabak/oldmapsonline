#!/usr/bin/env python
"""
Coordinates.py
--------------
Simple utility with functionality we need to wrap in AJAX. You need installed GDAL (it is FWTools)
"""

try:
	from osgeo import ogr
	from osgeo import osr
except:
	import ogr
	import osr

# http://code.google.com/p/webprocessingserver/

import simplejson

#print simplejson.dumps( [['first','second'],[1,2],[3,4]] )

# s_srs = source spatial reference - default EPSG:4326
# t_srs = target spatial reference - default EPSG:4326
# It accepts: 
#  - Well Known Text definition - passed on to importFromWkt().
#  - "EPSG:n" - number passed on to importFromEPSG().
#  - Link to a website like SpetialReference.org
#  - "EPSGA:n" - number passed on to importFromEPSGA().
#  - "AUTO:proj_id,unit_id,lon0,lat0" - WMS auto projections.
#  - "urn:ogc:def:crs:EPSG::n" - ogc urns
#  - PROJ.4 definitions - passed on to importFromProj4().
#  - well known name accepted by SetWellKnownGeogCS(), such as NAD27, NAD83, WGS84 or WGS72.
#  - WKT (directly or in a file) in ESRI format should be prefixed with ESRI:: to trigger an automatic morphFromESRI().

# json = if set the output is JSON array
# input = string with input, two coordinates separated by space per line

point = 'POINT (14.42977 50.07977)'

pt = ogr.CreateGeometryFromWkt(point)

outproj4 = '+proj=tmerc +ellps=WGS84 +datum=WGS84 +to_meter=0.03125 +k=0.9996 +lon_0=15E +x_0=4200000 +y_0=-1300000'

inref = osr.SpatialReference()
inref.ImportFromEPSG(4326)

outref = osr.SpatialReference()
outref.SetFromUserInput("http://www.spatialreference.org/ref/epsg/2065/")

#outref.ImportFromProj4(outproj4)
#print outref.ExportToPrettyWkt()
#print

pt.AssignSpatialReference(inref)

pt.TransformTo(outref)
print pt.GetX(0), pt.GetY(0)
