# Quarkus Debug Project

## Purpose

This is a simple project to help anyone to debug http requests. When using a proxy or any kind of API Management solution, it may help you when you can not call external APIs (https://echo-api.3scale.net:443 or http://postman-echo.com/get for example) and want to check how your payload is reaching the backend.

## How to install

```bash
oc new-project debug
oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/luszczynski/quarkus-debug.git --name=debug -n debug
oc expose svc/debug --path=/debug -n debug
```

## Testing

```bash
# Using httpie
http http://debug-debug.apps.brasilia-69e5.open.redhat.com/debug

HTTP/1.1 200 OK
Cache-control: private
Content-Length: 174
Content-Type: application/json
Set-Cookie: 0583d7385ac1fa0c0483f966682199e4=be6881f09275d76e9ef371c961254aab; path=/; HttpOnly

{
    "body": "",
    "cookieMap": {},
    "count": 5,
    "headers": {
        "empty": false
    },
    "method": "GET",
    "path": "/debug",
    "pathParams": {},
    "queryParams": {},
    "remoteAddress": "10.1.6.1:41328",
    "uri": "/debug"
}

# Using curl
curl http://debug-debug.apps.brasilia-69e5.open.redhat.com/debug
```

### Delay

Injecting delay of 100ms

```bash
http http://debug-debug.apps.brasilia-69e5.open.redhat.com/debug?delay=100
```

### Returned Status Code

```bash
http http://debug-debug.apps.brasilia-69e5.open.redhat.com/debug?statusCode=201
```

### Delay and Status Code

```bash
http http://debug-debug.apps.brasilia-69e5.open.redhat.com/debug?delay=100&statusCode=500
```
