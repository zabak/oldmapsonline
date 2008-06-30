#!/usr/bin/env python
"""
Python OAI-PMH MARC Client
"""

from oaipmh.client import Client

# MarcXML reader - parsing done by pymarc

from cStringIO import StringIO
from lxml.etree import tostring
from pymarc import marcxml

class MARCXMLReader(object):
	"""Returns the PyMARC record from the OAI structure for MARC XML"""
	def __call__(self, element):
		print element[0][1].text
		handler = marcxml.XmlHandler()
		marcxml.parse_xml( StringIO( tostring(element[0]) ), handler)
		return handler.records[0]

marcxml_reader = MARCXMLReader()

# Defining of metadata Readers in the Registry

from oaipmh import metadata

registry = metadata.MetadataRegistry()
registry.registerReader('marc21', marcxml_reader)

#### OAI-PMH Client processing 

oai = Client('http://snape.mzk.cz/OAI-script', registry)

recs = oai.listRecords(metadataPrefix='marc21', set='MZK03')

for rec in recs:
	print rec[0].identifier()
	r = rec[1] # Get pyMARC representation
	print r['856']
	print r['034']
	print r['008']
	print
