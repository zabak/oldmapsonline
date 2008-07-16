TransTools
==========

This is source code distribution of TransTools utility. This utility let 
you to calculate world file coefficients from set of GCPs.

How to compile
===============

Currently there is just possibility to build the project inside Eclipse IDE.
To do that just do in Eclipse 'File/Import/Existing Project Into workspace' 
end specify the TransTool folder. Then you should be able to browse the code 
and run TransGen.main() method.

How to export 
=============

To export the package to new runable jar you can export it inside Eclipse 
(right click on project/Export/Java/Jar), choose TransTool and its src folder.
Than choose to use Existing Manifest File from workspace - Manifest.txt.

To be able to run the jar you have add to the directory the  lib folder 
(located in TransTools/lib) so that the new jar can find all other jars. 
