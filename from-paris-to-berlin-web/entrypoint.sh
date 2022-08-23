#!/bin/bash

#ip=$(getent hosts from_paris_to_berlin_service | awk '{ print $1 }')

ip=${FPTB_BACK_END_WEB_IP}
echo "Using $ip to connect to the back end"
sed -i -e 's/from_paris_to_berlin_service/'"$ip"'/g' /etc/nginx/conf.d/default.conf

ip=${FPTB_BACK_END_WS_WEB_IP}
echo "Using $ip to connect to the back end"
sed -i -e 's/from_paris_to_berlin_ws_service/'"$ip"'/g' /etc/nginx/conf.d/default.conf

nginx -t

nginx

tail -f /dev/null
