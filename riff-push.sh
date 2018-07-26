export PROJECT_ID=$(gcloud config list --format 'value(core.project)')
FN_NAME=${1:-uppercat}
riff function create java $FN_NAME --git-repo https://github.com/joshlong/uppercat.git --artifact "uppercat-0.0.1-SNAPSHOT.jar" --handler "uppercat" --image gcr.io/$PROJECT_ID/$FN_NAME
