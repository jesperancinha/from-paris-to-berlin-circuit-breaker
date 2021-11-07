#!/bin/bash

#ip=$(getent hosts from_paris_to_berlin_web | awk '{ print $1 }')
ip=${FPTB_BACK_END_WEB_IP}

echo "Using $ip to connect to the back end"

sed -i 's/from_paris_to_berlin_web/'"$ip"'/g' /etc/nginx/conf.d/default.conf

nginx -t

nginx

tail -f /dev/null
