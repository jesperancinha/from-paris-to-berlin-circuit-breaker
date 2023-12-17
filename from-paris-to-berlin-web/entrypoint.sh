#!/bin/bash

#ip=$(getent hosts from-paris-to-berlin-service | awk '{ print $1 }')

#ip=${FPTB_BACK_END_WEB_IP}
#echo "Using $ip to connect to the back end"
#sed -i -e 's/from-paris-to-berlin-service/'"$ip"'/g' /etc/nginx/conf.d/default.conf
#
#ip=${FPTB_BACK_END_WS_WEB_IP}
#echo "Using $ip to connect to the back end"
#sed -i -e 's/from-paris-to-berlin-ws-service/'"$ip"'/g' /etc/nginx/conf.d/default.conf

nginx -t

nginx

tail -f /dev/null
