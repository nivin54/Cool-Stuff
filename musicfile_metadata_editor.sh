#!/usr/bin/env bash
for file in * *;
   do
       TITLE=$(echo "$file" | cut -d'.' -f1);
       ALBUM=$(echo "$file" | cut -d'_' -f1-2);
       echo $TITLE;
       echo $ALBUM
       id3v2 -t "$TITLE" $file
       id3v2 -A "$ALBUM" $file
   done
