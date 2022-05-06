# tracking-webserver
A small small webserver (in Go, Python, or Java). Feel free to use a framework, if desired.
/ping - returns response code 200 and string OK when file /tmp/ok is present, if file is not present returns 503 service unavailable
/img - returns a 1x1 gif image, and log the request.
