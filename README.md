# Dispersive Echo Finder

The purpose of the program is to analyse the analysis windows as well as the frequency bands of a spectrogram to plot points that follow the dispersive echos
given by tapping a slinky spring.

As input the program takes in a text file containing all of the data for the spectrogram of the audio file, the starting point for the first 2 echoes of the
dispersive signal, as well as an image to overlay the points on.

The text file is created from the exporting a spectrogram object as a short text file in a audio analysis program named "Praat" 
developed by Paul Boersma and David Weenink from Phonetic Sciences, University of Amsterdam.

Praat Website:
https://www.fon.hum.uva.nl/praat/

Praat Source Code:
https://github.com/praat/praat

The program then overlays the points plotted on the input image, then displays it to the user on the GUI. The points of the 2 echos can each be outputted 
to a comma-separated file, with x being the position of the point in time(s) and the frequency(Hz).

ISSUES:

This program can be unreliable when given data containing lots of noise, as the method for following a dispersive echo consists of finding the analysis 
window with the highest amplitude for a given frequency band.

Due to this, this solution was abandoned in favour of a more efficient method of visually fitting hyperbolas on the dispersive signal.
