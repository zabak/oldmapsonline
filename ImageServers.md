#Image servers used over the world for publishing old maps

# List of Image Servers used for publishing maps + examples of protocols #

# Zoomify Static Tiles #

http://www.zoomify.com/

http://www.staremapy.cz/zoomify-analyza/zoomify.py.html http://code.google.com/p/oldmapsonline/source/browse/trunk/openlayers/lib/OpenLayers/Layer/Zoomify.js

Examples:

http://www.staremapy.cz/mapy/1919-jancuv-plan-velkeho-brna/
http://www.staremapy.cz/mapy/1919-jancuv-plan-velkeho-brna/ImageProperties.xml
http://www.staremapy.cz/mapy/1919-jancuv-plan-velkeho-brna/TileGroup0/
http://www.staremapy.cz/mapy/1919-jancuv-plan-velkeho-brna/TileGroup1/

# OpenSource: IIPImage (TIFF) #

Internet Imaging Protocol: http://iipimage.sourceforge.net/documentation/protocol/
NEW - Protocol compatible with Zoomify tiles

Examples:

http://www.oldmapsonline.org/fcgi-bin/iipsrv.fcgi?FIF=/moll-imageserver/mapa1_300.deflate.pyr.tif&jtl=3,37
http://www.oldmapsonline.org/publish/iipmooviewer/iipmooviewer.html

# OpenSource: J2K TileRenderer (JPEG2000) #

http://dltj.org/article/introducing-j2ktilerenderer/

Source code:
https://drc-dev.ohiolink.edu/svn/j2kTileRenderer/trunk/

Protocol compatible with Zoomify tles.

# OpenSource: aDORe djatoka (JPEG2000) #

http://african.lanl.gov/aDORe/projects/djatoka/

http://sourceforge.net/projects/djatoka

http://www.dlib.org/dlib/september08/chute/09chute.html

Protocol: OCLS OpenURL.

Examples:

http://african.lanl.gov/adore-djatoka/

# Commercial: LizardTech Express Server (MrSID, JPEG2000) #

http://www.lizardtech.com/products/exp/

onload="initViewerOps(7072,5648,'http://www.nls.uk:80/lizardtech/iserv')">

7072x5648 is the size of raster? Is there a better way how to ask the Image Server...

Examples of the protocol:

http://www.nls.uk:80/lizardtech/iserv/getimage?cat=Maps&item=00000293.sid&cp=0.5,0.5&lev=4&wid=700&hei=500&

http://www.nla.gov.au/lizardtech/iserv/getimage?cat=NLAObjects&img=/nla.map/f/005/94/nla.map-f00594-sd.sid&oif=jpeg&rgn=0.1226415094,-0.0180995475,0.8773584906,1.0161603103&cmd=&wid=400&hei=400

Documentation:

http://www.lizardtech.es/support/sup_expperl.txt

# Commercial: OCLC ContentDM (JPEG2000?) #

(I hope it is this product)

http://www.oclc.org/contentdm/

Examples:

Maps in the Library of Congress. http://lcweb2.loc.gov/ammem/gmdhtml/gmdhome.html

http://lcweb2.loc.gov/cgi-bin/map_image.pl?data=/home/www/data/gmd/gmd3/g3400/g3400/ct000680r.jp2&x=4318&y=2864&res=2&width=640&height=480&lastres=4&jpegLevel=80

# Commercial: Exlibris DigiTool (JPEG2000) #

http://www.exlibrisgroup.com/category/DigiToolOverview

Examples:

https://socrates.leidenuniv.nl/R?func=collections-result&collection_id=1475

https://socrates.leidenuniv.nl/ImageServer/imageserver?res=3&viewwidth=512&viewheight=512&x=969&y=753&imgClickX=1993&imgClickY=1777&rotation=0&requestId=4&filename=%2Fexlibris%2FstorageDefault%2F2007%2F10%2F23%2Ffile_195%2F545217&display_plugin=false&thumbnailActionSelect=thumbclick&imageActionSelect=imageclick&collectionName=

# Commercial: Luna Imaging (MrSID, JPEG2000) #

http://www.lunaimaging.com/

Examples:

David Rumsey Map Collection

http://rumseysid.lunaimaging.com/mrsid/bin/image_jpeg.pl?client=Rumsey&image=SIDS/D0115/4638017.sid&x=120&y=120&width=256&height=256&level=0

# Commercial: XLimage: image server #

http://www.xlimage.it/?interf=flash&lang=ENG

Examples:

http://mara.kbr.be/kbrImage/CM/1052417.imgf