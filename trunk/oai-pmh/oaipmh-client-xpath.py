#!/usr/bin/env python
"""
Universal OAI-PMH Client written in Python. Reading of the fields is done with XPath expression.
"""

# Simple XML Reader
class XMLReader(object):
    def __call__(self, element):
        return element

# Defining of metadata Readers in the Registry

from oaipmh import metadata

registry = metadata.MetadataRegistry()
registry.registerReader('marc21', XMLReader() )

#### OAI-PMH Client processing 

from oaipmh.client import Client
from lxml import etree

oai = Client('http://snape.mzk.cz/OAI-script', registry)

#recs = oai.listRecords(metadataPrefix='marc21', set='MZK03')

#rec = recs.next()
#for rec in recs:

rec = oai.getRecord(identifier='MZK03-907223', metadataPrefix='marc21')

if rec:
	print rec[0].identifier()
	r = rec[1] # Get XML tree for record 
	print etree.tostring(r,pretty_print=True)

	if r:
		xpath_evaluator = etree.XPathEvaluator(r, namespaces={'marc':'http://www.loc.gov/MARC21/slim'})
		e = xpath_evaluator.evaluate

		print e("//marc:datafield[@tag='856']")
		print e("//marc:datafield[@tag='034']")
