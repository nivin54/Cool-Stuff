#!/usr/bin/env bash
for file in * *;
   do
       ALBUM=$(echo "$file" | cut -d' ' -f1,2,3 | tr -s ' ' '_' );
       echo $ALBUM
       mv "$file" "$ALBUM"
   done
