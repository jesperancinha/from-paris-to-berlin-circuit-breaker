#!/bin/bash

ip=$(getent hosts from_paris_to_berlin_web | awk '{ print $1 }')

sed -i 's/from_paris_to_berlin_web/'"$ip"'/g' /etc/nginx/conf.d/default.conf

nginx -t

nginx

tail -f /dev/null
