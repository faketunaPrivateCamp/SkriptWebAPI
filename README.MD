# SkriptWebAPI - SKeb

[日本語のREADMEあります](/README_JA.MD)

- [SkriptWebAPI - SKeb](#skriptwebapi---skeb)
  - [About](#about)
  - [Features](#features)
  - [Documentation](#documentation)
    - [Important](#important)
    - [Effects](#effects)
    - [Events](#events)
    - [Event values in all receive request event](#event-values-in-all-receive-request-event)
    - [Expressions](#expressions)
- [Example](#example)
  - [Todo](#todo)


## About

Allow to use API server feature, Web Request feature easily on Minecraft servers with Skript.

## Features

- Allow to build HTTP API Server
- Can send Web Request

## Install

1. Download `SkriptWebAPI-<Version>.jar` from [here](https://github.com/faketunaPrivateCamp/SkriptWebAPI/releases). Lager number mean recent.
2. Put your downloaded jar to server plugins folder
3. Run and ready to use

## Documentation

Not completed

### Important

If you use reply effect in if statement, you need `exit` after reply for avoid error
```sk
if (something):
    reply %httpexchange% with %string% and response code 200
    exit
```

Also you need `reply %httpexchange% as not found` in last line of event
```sk
on receive get request:
    set {_request} to event-request

    if (something):
        reply {_request} with %string% and response code 200
        exit
    
    reply {_request} as not found
```



### Effects
```sk
0.0.1+ [SKeb] reply %httpexchange% as (not found|404)
0.0.1+ [SKeb] reply %httpexchange% [to [the] request sender] [(with|and) body %-string%] [(with|and) response code %-integer%]
0.0.1+ [SKeb] set [the] %httpexchange%['s] response header %string% to %string%
0.0.1+ [SKeb] start http [api] server [in port %-integer%]
0.0.1+ [SKeb] stop http [api] server
```

### Events
```sk
0.0.1+ [SKeb] [server] receive get request
```

### Event values in all receive request event
```sk
0.0.1+ [SKeb] event-body
0.0.1+ [SKeb] event-header
0.0.1+ [SKeb] event-useragent
0.0.1+ [SKeb] event-agent
0.0.1+ [SKeb] event-senderaddress
0.0.1+ [SKeb] event-address
0.0.1+ [SKeb] event-contextpath
0.0.1+ [SKeb] event-path
```

### Expressions
```sk
0.0.1+ [SKeb] %contextpath% parsed as text
```


# Example
```sk
on receive get request:
    set {_request} to event-request
    set {_path} to event-path parsed as text
    set {_body} to ""

    if ({_path} is "/test"):
        set {_body} to "{""test"":""done""}"
        set {_request}'s response header "Content-Type" to "application/json"
        reply {_request} with body {_body} and response code 200
        exit

    if ({_path} contains "/sendmessage/"):
        set {_context} to {_path}
        replace "/sendmessage/" in {_context} with ""
        set {_context::*} to split {_context} at "/"

        set {_player} to {_context::1} parsed as offline player

        if ({_player} is online):
            send {_context::2} to {_player}
            set {_body} to "{""sent"":true}"
        else:
            set {_body} to "{""sent"":false}"
            
        set {_request}'s response header "Content-Type" to "application/json"
        reply {_request} with body {_body} and response code 200
        exit

    reply {_request} as not found
```

## Todo

- Add PostRequestEvent
- Add DeleteRequestEvent
- Add PutRequestEvent
- Allow to send POST web request
- Allow to send PUT web request
- Allow to send DELETE web request
- Test and allow to load in 1.12.2 ~ 1.19.2