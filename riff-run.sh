FN_NAME=${1:-uppercat}

riff service invoke $FN_NAME  -- --header "Content-type: text/plain"  -d"{}" -v

# --header "Accept: text/plain"
