# ![http://oldmapsonline.googlecode.com/svn/trunk/boundingbox/logo55.png](http://oldmapsonline.googlecode.com/svn/trunk/boundingbox/logo55.png) Old Maps Online #

Repository for new software tools and patches of existing open-source tools, which are usable for libraries, archives, museums as well as individuals, who are publishing scanned old maps and other historical documents.

Development is supported by a R&D grant DC08P02OUK006 - Old Maps Online (www.oldmapsonline.org) from Ministry of Culture of the Czech Republic.

### Project objectives: ###

Development of a technology and a pilot system to allow memory institutions to reliably georeference and publish maps and other graphic documents. The system will have the following capabilities: - it will provide online georeferencing tool for large digitised maps - it will facilitate search for digitised maps and graphics linked to a certain geographic location. It will provide user friendly interface using geographic and time information (marking an area on a map, timeline, map ranking) - it will be available in Czech and English and it will be possible to translate it into other languages. Development of methodology for online publishing of (large) old maps and other graphic documents and their georeferencing by memory institutions. Individual software tools developed by the project that could be used independently will be released under some open source license.

### Publications: ###

  * Tiles as an approach to on-line publishing of scanned old maps, vedute and other historical documents (in print). Presentation scheduled at [ICA Commission on the History of Cartography - 3rd International Workshop, Barcelona](http://web.auth.gr/xeee/ICA-Heritage/Commission/3rd_Workshop/Barcelona_1.htm), article is going to be published in [e-Perimetron](http://www.e-perimetron.org/).

### Development plans: ###

**Online Georeference Tool**

  * [OpenLayers: support for Zoomify Tiles](http://oldmapsonline.googlecode.com/svn/trunk/openlayers/examples/zoomify.html) [patch](http://trac.openlayers.org/ticket/1285) structure (_testing_).

  * gcps2kml - tools for generating best transformation (ideal non-proportional bounding box with rotation) for KML from given set of GCPs. Ideal affine transformation for WorldFile. (_under development_).

  * Online Georeference: online tool for specifying GCPs (Ground Control Points) for scanned documents, which are already published as Zoomify tiles/WMS/TMS (implementation: application will host probably on Google App Engine and will use OpenLayers and gcps2kml). [GUI prototype](http://oldmapsonline.googlecode.com/svn/trunk/georeference.html)

**Raster map warping server** - for precise rectification of raster maps based on GCP database

  * Prototype of warping server - warping of raster maps using gdal tools (based partly on Jan Hartmann work).

**Image Server for publication of scanned TIFF master files**

  * IIPImage: patch for Zoomify tiles support - a free image server for serving tiles directly from TIFF master file (_under development_).

**3D Presentation** of already published maps

  * Python KML SuperOverlay server for remote Zoomify/TMS tiles.

**WMS availability** of maps published as tiles

  * MapServer: patch to support of the reprojection for online tiles (TMS/Zoomify) (implemented directly or trough GDAL driver).

**High-quality on-line search interface** for collection of historical maps

  * Web Search Interface for searching of the map collections: An area on the map + range on the time line + fulltext.

  * Search with ranking: Search will use **index and** algorithm.

  * Search Indexer - with support for metadata import trough OAI-PMH, ...

### Possible improvement of existing tools for simplify switch to JPEG2000: ###

  * IIPImage JPEG2000 support - by GDAL/FWTools linking (usage of JPEG2000 Kakadu library).

  * Simple GUI tools for GDAL tools (gdal\_translate, gdalwarp, gdal2tiles).

### Supported on-line publication format of scanned images: ###

Mainly: _Zoomify tiles_ for non-georeferenced images. For georeferenced images: a _WMS service_  or tiled _WMS-C/TMS_.

Possibly also non-georeferenced images trought WMS, and plain JPEG/JPEG2000/TIFF available at given url. Maybe DjVu formats and images available trough MrSID server or ECWD protocol.