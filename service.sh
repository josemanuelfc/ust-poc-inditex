#!/bin/sh

SERVICE='http://localhost:8090/service?type=msgpack'
MEDIATYPE='application/json'
#MEDIATYPE='application/x-msgpack'
ASIOCOP='http://localhost:8080/dashboard'

curl -i -H "Accept: ${MEDIATYPE}" \
        -H "Content-Type: ${MEDIATYPE}" \
	-d @service.json \
	-X GET \
	${SERVICE}

