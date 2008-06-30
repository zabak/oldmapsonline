from oaipmh.client import Client
from pprint import pprint

# Simple XML Reader
class XMLReader(object):
	"""Returns simply the XML element for the record structure"""
    def __call__(self, element):
        return element

xml_reader = XMLReader()


# MarcXML reader - parsing done by pymarc
from cStringIO import StringIO
from lxml.etree import tostring
from pymarc import marcxml

class MARCXMLReader(object):
	"""Returns the PyMARC record from the OAI structure for MARC XML"""
	def __call__(self, element):
		handler = marcxml.XmlHandler()
		#marcxml.parse_xml( StringIO( """<?xml version="1.0" encoding="UTF-8"?>\n""" + tostring(element[0]) ), handler)
		marcxml.parse_xml( StringIO( tostring(element[0]) ), handler)
		return handler.records[0]

marcxml_reader = MARCXMLReader()

# Defining of metadata Readers in the Registry

from oaipmh import metadata

registry = metadata.MetadataRegistry()
registry.registerReader('oai_dc', metadata.oai_dc_reader)
registry.registerReader('marc21', marcxml_reader)


#### OAI-PMH Client processing 

oai = Client('http://snape.mzk.cz/OAI-script', registry)

id = oai.identify()
print id.repositoryName()
print id.adminEmails()
print id.baseURL()

formats = oai.listMetadataFormats()
pprint formats

# 'marc21'

sets = oai.listSets()
for s in sets:
	print s

# 'MZK03'

recids = oai.listIdentifiers(metadataPrefix='marc21', set='MZK03') # from_='2003-01-01T00:00:00Z', until=''

# for example: 'MZK03-907223' is in the list of maps
# or 356050 *not a map

# 238208 problematic
r = oai.getRecord(identifier='MZK03-1479', metadataPrefix='marc21')

# from lxml import etree
# print etree.tostring(r[1],pretty_print=True)

# xpath_evaluator = etree.XPathEvaluator(r[1][0], namespaces={'marc21':'http://www.loc.gov/MARC21/slim'})
# e = xpath_evaluator.evaluate

#s = etree.tostring(r[1][0],pretty_print=True)

rec = r[1] # this returns parsed MARC record

# Processing of the MARC record:

# link is in
rec['856']
# bounding box, scale of a map
rec['034']
# year - .data[7:11], or .data[11:15]
rec['008']