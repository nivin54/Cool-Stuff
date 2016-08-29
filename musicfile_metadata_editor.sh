#!/usr/bin/env bash
for file in * *;
   do
       TITLE=$(echo "$file" | cut -d'.' -f1);
       ALBUM=$(echo "$file" | cut -d'_'  -f1-2);
       PT=$(echo  "$TITLE" | perl -ne '/(.*_)0*(.*)/ && print $1,$2');
       echo $ALBUM
       id3v2 -t "$PT" $file
       id3v2 --TRCK "1" $file
       id3v2 -A "$ALBUM" $file
   done
   #!"http://id3.org/id3v2.3.0 , above scripts changes teh track, title and album of a mp3 songs.
