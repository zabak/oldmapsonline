#!/usr/bin/env python
# encoding: utf-8
"""
RMS - Root Mean Squere
Counting of Root Mean squere for given points
"""

import sys, math

w = []
for line in sys.stdin:
    if line.strip():
        w.append(float(line))

destSet = []
sourceSet = []
for line in open("gcps.txt", 'r'):
    destSet.append( map( float, line.split()[:2] ) )
    sourceSet.append( map( float, line.split()[2:4] ) )

projSet = []
for x,y in sourceSet:
    p = []
    p.append( w[4] + w[0]*x + w[1]*y )
    p.append( w[5] + w[2]*x + w[3]*y )
    projSet.append( p )


errs = []

#print w
#print '--'
for i in range(len(destSet)):
    x,y = destSet[i]
    px,py = projSet[i]
    minx = min( x, px)
    maxx = max( x, px)
    miny = min( y, py)
    maxy = max( y, py)
    sx = maxx - minx
    sy = maxy - miny
    err = math.sqrt( sx*sx + sy*sy )
    errs.append(err)
    print x,y,'-',px,py,'==',err

pow2 = lambda x: x*x

print "RMS:", math.sqrt( sum( map( pow2, errs )) / len(errs) )

