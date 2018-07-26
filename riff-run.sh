FN_NAME=${1:-uppercat}
OUT=uppercat.json
#riff service invoke $FN_NAME  -- --header "Content-type: text/plain"  -d"{}" -v -o $OUT
riff service invoke $FN_NAME  -- --header "Content-type: text/plain"  -d@cat.jpg  -v -o $OUT
rm -rf uppercat.jpg
cat $OUT | jq -r .cat  | base64 -d  > uppercat.jpg

# --header "Accept: text/plain"
