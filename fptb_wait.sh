#!/bin/bash

GITHUB_RUN_ID=${GITHUB_RUN_ID:-123}

echo "" >> "logs"

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose -p "${GITHUB_RUN_ID}" logs "$name" >> "logs"
    string=$(cat logs)
    counter=0
    echo "Project $GITHUB_RUN_ID"
    echo -n "Starting service $name "
    while [[ "$string" != *"$message"* ]]
    do
      echo -e -n "\e[93m-\e[39m"
      docker-compose -p "${GITHUB_RUN_ID}" logs "$name" >> "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo -e "\e[91mFailed after $counter tries! Cypress tests may fail!!\e[39m"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo -e "\e[92m Succeeded starting $name Service after $counter tries!\e[39m"
}

checkServiceByNameAndMessage from-paris-to-berlin-ws-service 'Tomcat started on port(s): 8081'
checkServiceByNameAndMessage from-paris-to-berlin-ws-service 'Members'
checkServiceByNameAndMessage from-paris-to-berlin-service 'Netty started on port 8080'
checkServiceByNameAndMessage from-paris-to-berlin-service 'Members'
checkServiceByNameAndMessage from-paris-to-berlin-fe 'nginx: configuration file /etc/nginx/nginx.conf test is successful'
