#!/usr/bin/env bash

export PROJECT_ID=$(gcloud config list --format 'value(core.project)')

mvn -DskipTests=true clean package
cp target/uppercat-0.0.1-SNAPSHOT.jar uppercat-0.0.1-SNAPSHOT.jar
git commit -am up
git push

FN_NAME=${1:-uppercat}
riff service delete ${FN_NAME}
riff function create java $FN_NAME --git-repo https://github.com/joshlong/uppercat.git --artifact "uppercat-0.0.1-SNAPSHOT.jar" --handler "uppercat" --image gcr.io/$PROJECT_ID/$FN_NAME

