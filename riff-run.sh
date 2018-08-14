#!/bin/bash
riff service invoke uppercat -- --header "Content-type: text/plain"  -d@cat.jpg  -v -o uppercat.json
cat uppercat.json | jq -r .cat  | base64 > uppercat.jpg
