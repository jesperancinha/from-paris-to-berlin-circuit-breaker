FROM nginx:1.28.0

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN mv /etc/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf.disabled

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY dist/from-paris-to-berlin-web /usr/share/nginx/html

COPY entrypoint.sh /usr/local/bin/

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 9000
